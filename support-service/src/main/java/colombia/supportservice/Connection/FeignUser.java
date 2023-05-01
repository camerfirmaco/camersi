package colombia.supportservice.Connection;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import colombia.supportservice.Mapping.DtoAgenteSupport;

@FeignClient(name = "auth-service")
@RequestMapping("/users")
public interface FeignUser {
    @GetMapping("/support/{id}")
    DtoAgenteSupport getAgentSupport(@PathVariable("id") String id);
}
