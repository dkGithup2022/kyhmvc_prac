package app.controller.prac;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@RestController
@Slf4j
public class RequestHeaderController {
    private final Logger logger =  LoggerFactory.getLogger(getClass());

    @RequestMapping("/headers")
    public String headers(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpMethod httpMethod,
            Locale locale,
            @RequestHeader MultiValueMap<String,String>  headerMap,
            @CookieValue(value = "myCookie", required = false) String cookie
            ){
        log.info("request = {}", request.toString()) ;
        log.info("request = {}",response.toString()) ;
        log.info("request = {}",httpMethod.toString()) ;
        log.info("request = {}",locale) ;
        log.info("request = {}",headerMap) ;
        log.info("request = {}",cookie) ;

        return "ok";

    }
}
