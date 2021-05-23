package com.appintimedia.apifootball.model.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Away {
    private Integer id;
    private String name;
    private String logo;
    private Boolean winner;
    private Integer goal;
}
