package app.controller.prac;

import ch.qos.logback.core.util.Loader;
import jdk.jfr.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class MappingController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /*
    Request Mapping -> GET,POST,PUT,DELETE 전부 가능
    만약 필요하다면 method = RequestMethod.GET 이런 enum 값을 넣어주면 됨.
     */
    @RequestMapping("/hello-basic")
    public String helloBasic(){
        log.info("hello world");
        return "ok";
    }

    @RequestMapping(value = "/hello-get", method = RequestMethod.GET)
    public String helloGet(){
        log.info("hello get");
        return "ok";
    }

    @RequestMapping("/helloPath/{user}")
    public String helloPath(@PathVariable String user ){
        log.info("hello {}", user);
        return "ok " + user;
    }

    @RequestMapping("mapping/user/{user}/date/{date}")
    public String mapping2(@PathVariable String user, @PathVariable String date){
        log.info("hello {} & date: {} ", user, date);
        return "ok";
    }

    @RequestMapping(value = "headerModeDebug", headers = "mode=debug")
    public String modeDebug(){
        log.info("mode debug");
        return "ok";
    }

    @RequestMapping(value = "consumeJson", consumes = "application/json")
    public String onlyJson(){
        log.info("mode debug");
        return "ok";
    }
}
