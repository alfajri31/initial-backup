package com.appintimedia.apifootball.model.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Score {
    private Halftime halftime;
    private Fulltime fulltime;
    private Extratime extratime;
    private Penalty penalty;
}
