package com.appintimedia.apifootball.model.app.date;

import com.appintimedia.apifootball.model.base.Teams;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeagueApp {
    private String name;
    private Teams teams;
}
