package com.appintimedia.apifootball.model.app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Data
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Periods {
    private Timestamp first;
    private Timestamp second;
}
