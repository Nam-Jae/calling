package ecall.external;

import java.util.Date;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "dispatch", url = "${api.url.dispatch}")
public interface DispatchService {
    @RequestMapping(method = RequestMethod.GET, path = "/dispatches/{id}")
    public Dispatch getDispatch(@PathVariable("id") Long id);
    
    
}
