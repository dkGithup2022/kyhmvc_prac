package app.controller.prac;

import app.model.HelloData;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@RestController
@Slf4j
public class RequestBodyController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest req, HttpServletResponse res) throws IOException {
        ServletInputStream inputStream = req.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        String body = objectMapper.readValue(messageBody, HelloData.class).toString();
        log.info("body : {}" , body);
        res.getWriter().write("ok");

    }
    @PostMapping("/request-body-string-v2")
    public void requestBodyString(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        String body = objectMapper.readValue(messageBody, HelloData.class).toString();
        log.info("body : {}" , body);
        responseWriter.write(body);
    }

    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyString(HttpEntity<String> httpEntity) throws IOException {
        String messageBody = httpEntity.getBody();
        String body = objectMapper.readValue(messageBody, HelloData.class).toString();
        log.info("body : {}" , body);
        return new HttpEntity<String>(body);
    }

    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String message) throws IOException {
        log.info("body : {}" , message);
        return "ok";
    }

    @PostMapping("/request=body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest req, HttpServletResponse res) throws IOException {
        ServletInputStream inputStream = req.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream,StandardCharsets.UTF_8);

        log.info("message : {}" + messageBody);
        HelloData hd = objectMapper.readValue(messageBody, HelloData.class);
        log.info("ob : {}", hd);
        res.getWriter().write(hd.toString());
    }


    /*
    ?????? ?????? -> http message converter ??? ??????
    -> @RequestBody ?????? ?????? ?????????, ?????? ????????? ?????? ?????? ?????? ??? setter ??? ?????????,
    ?????? ?????? ?????? application/json ?????? ?????? .
 -> @RequestBody ??? ?????????, ???????????? ModelAttribute ??? ??????????????? ??????????????? ?????? ????????? .

     */
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String reqBody) throws IOException {
        log.info("message : {}" + reqBody);
        HelloData hd = objectMapper.readValue(reqBody, HelloData.class);
        log.info("ob : {}", hd);
        return "ok";
    }

    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) throws IOException {
        log.info("ob : {}", helloData);
        return "ok";
    }

    @PostMapping("/response-body-v1")
    public ResponseEntity<String> responseBodyV1(){
        return new ResponseEntity<>("ok", HttpStatus.ACCEPTED); // ?????? ??????
    }

    @PostMapping("/response-body-v2")
    public ResponseEntity<HelloData> responseBodyV2(@RequestBody HelloData helloData){
        log.info("hello : {}", helloData.toString());
        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }
}
