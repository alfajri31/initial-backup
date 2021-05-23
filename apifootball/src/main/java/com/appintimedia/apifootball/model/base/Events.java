package com.appintimedia.apifootball.model.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Events {
    private Time time;
    private Team team;
    private Player player;
    private Assist assist;
    private String type;
    private String detail;
    private String comments;
}
