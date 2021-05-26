package com.appintimedia.apifootball.model.app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleDateResponse {
    private List<Result> response;
}
