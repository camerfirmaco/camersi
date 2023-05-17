package colombia.supportservice.Connection;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import colombia.supportservice.Mapping.DtoAgenteSupport;

@FeignClient(name = "auth-service")
public interface FeignUser {
    @GetMapping("/users/support/{id}")
    DtoAgenteSupport getAgentSupport(@PathVariable("id") String id);
}
