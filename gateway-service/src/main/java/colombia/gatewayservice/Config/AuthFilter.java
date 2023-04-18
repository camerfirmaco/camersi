package colombia.gatewayservice.Config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    private WebClient.Builder webClient;

    public AuthFilter(WebClient.Builder webClient) {
        super(Config.class);
        this.webClient = webClient;
    }
    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            if(tokenValid(getToken(exchange.getRequest().getHeaders())))
                return onError(exchange, HttpStatus.BAD_REQUEST);

            return webClient.build()
                    .post()
                    .uri("http://auth-service/auth/validate")
                    .bodyValue(new RequestDto(exchange.getRequest().getPath().toString(), exchange.getRequest().getMethod().toString()))
                    .header("Authorization", exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0))
                    .retrieve().bodyToMono(ResponseEntity.class)
                    .map(response -> {
                        response.getStatusCode().is2xxSuccessful();
                        return exchange;
                    }).flatMap(chain::filter);
        }));
    }

    private String getToken(HttpHeaders request){
        if(!request.containsKey(HttpHeaders.AUTHORIZATION))
            return null;
        if(request.get(HttpHeaders.AUTHORIZATION).get(0)!=null)
            return new String(request.get(HttpHeaders.AUTHORIZATION).get(0));
        return null;
    }

    private Boolean tokenValid(String token){
        if(token!=null)
            return false;
        return true;
    }
    
    public Mono<Void> onError(ServerWebExchange exchange, HttpStatus status){
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        return response.setComplete();
    }

    public static class Config {}
}
