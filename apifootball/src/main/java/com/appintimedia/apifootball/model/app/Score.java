package com.appintimedia.apifootball.model.app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Score {
    private Fulltime fulltime;
    private Halftime halftime;
    private Extratime extratime;
    private Penalty penalty;
}
