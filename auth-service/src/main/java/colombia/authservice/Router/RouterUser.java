package colombia.authservice.Router;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import colombia.authservice.Mapping.Public.RequestDto;

import java.util.List;
import java.util.regex.Pattern;

@Component
@ConfigurationProperties(prefix = "user-paths")
public class RouterUser {

    private List<RequestDto> paths;

    public List<RequestDto> getPaths() {
        return paths;
    }

    public void setPaths(List<RequestDto> paths) {
        this.paths = paths;
    }

    public boolean isPath(RequestDto dto) {
        return paths.stream().anyMatch(p ->
                Pattern.matches(p.getUri(), dto.getUri()) && p.getMethod().equals(dto.getMethod()));
    }
}
