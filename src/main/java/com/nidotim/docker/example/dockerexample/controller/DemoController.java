package com.nidotim.docker.example.dockerexample.controller;

import com.nidotim.docker.example.dockerexample.model.dto.Demo;
import java.time.Instant;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController(value = "/demo")
@Slf4j
public class DemoController {


  @GetMapping()
  public ResponseEntity<?> root() {
    Demo demo = Demo.builder()
        .ip(getIP())
        .now(Instant.now())
        .build();
    return new ResponseEntity<>(demo, HttpStatus.OK);
  }

  private String getIP() {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes()).getRequest();
    String ip = request.getHeader("X-Forwarded-For");
    log.info("get IP.  X-Forwarded-For: " + ip + ", RemoteAddr:" + request.getRemoteAddr());
    if (!StringUtils.isEmpty(ip)) {
      return ip;
    } else {
      return request.getRemoteAddr();
    }
  }
}
