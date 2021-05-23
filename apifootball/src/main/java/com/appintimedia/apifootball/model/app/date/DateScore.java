package com.appintimedia.apifootball.model.app.date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class DateScore {
    private String name;
    private List<LeagueApp> leagueMatch;
}
