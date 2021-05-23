package com.appintimedia.apifootball.model.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Data
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Fixture {
    private Integer id;
    private String referee;
    private String timezone;
    private String date;
    private Timestamp timestamp;
}
