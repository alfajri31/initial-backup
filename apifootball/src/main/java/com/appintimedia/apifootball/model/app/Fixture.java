package com.appintimedia.apifootball.model.app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Fixture {
    private String referee;
    private Periods periods;
    private Status status;
    private Venue venue;
}
