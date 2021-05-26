package com.appintimedia.apifootball.model.app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class League {
    private Integer id;
    private String name;
    private String country;
    private String logo;
    private String flag;
    private Integer season;
    private String round;
}
