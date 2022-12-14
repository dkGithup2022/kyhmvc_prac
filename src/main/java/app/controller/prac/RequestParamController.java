package app.controller.prac;

import app.model.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@Slf4j
public class RequestParamController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String user = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));
        log.info( "user =  {}  & age = {}", user, age );
        res.getWriter().write("ok");
    }

    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String username,
            @RequestParam("age") int age
    ){
        log.info("v2 username= {}, age={}" , username, age);
        return "ok";
    }

   // @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username, @RequestParam int age){
        log.info("v3 username = {}, age = {}", username, age);
        return "ok";
    }
    // @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age){
        log.info("v4 username = {}, age = {}", username, age);
        return "ok";
    }

    //@ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(

            @RequestParam(required = true) String username,
            @RequestParam(required = true) int age){
        log.info("request param requird  username = {}, age = {}", username, age);
        return "ok";
    }

    //@ResponseBody
    @RequestMapping("/request-param-required-v2")
    public String requestParamRequiredV2(
            @RequestParam(required = true) String username,
            @RequestParam(required = false, defaultValue = "-1") int age){
        log.info("request param requird  username = {}, age = {}", username, age);
        return "ok";
    }
    // ?????? ??????????????? null ?????? ???????????? int ???????????? ?????? ?????? ?????? ????????? Integer ??????

    //@ResponseBody
    @RequestMapping("/reqeust-param-map")
    public String requestParamMap(
            @RequestParam Map<String, String > paramMap
            ){
        log.info("map  username = {}, age = {}", paramMap.get("username"),paramMap.get("age"));
        return "ok";
    }


    /*
    ?????? ??????????????? ??????
    ?????? ?????? ??? ????????? ?????? ???, ???????????? setter ??? ????????????. ?????? ???????????? param ???????????? ???????????????.
     */
    //@ResponseBody
    @RequestMapping("/request-param-model-attribute1")
    public String reqeuestModelAttribute(@ModelAttribute HelloData helloData){
        log.info("hello data: {} ",helloData);
        return helloData.toString();
    }


    //@ResponseBody
    @RequestMapping("/request-param-model-attribute2")
    public String reqeuestModelAttribute2( HelloData helloData){
        log.info("hello data: {} ",helloData);
        return helloData.toString();
    }
}

