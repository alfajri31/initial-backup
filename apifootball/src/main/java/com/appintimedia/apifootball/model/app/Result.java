package com.appintimedia.apifootball.model.app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    private Fixture fixture;
    private League league;
    private Teams teams;
    private Score score;
    private Goals goals;
}
