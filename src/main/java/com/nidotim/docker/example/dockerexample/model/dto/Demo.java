package com.nidotim.docker.example.dockerexample.model.dto;

import java.time.Instant;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Demo {

  private Instant now;

  private String ip;

}
