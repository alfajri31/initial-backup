package com.appintimedia.apifootball.model.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class RawDateScore {
    private String get;
    private Parameter parameters;
    private List<?> error;
    private Integer results;
    private Paging paging;
    private List<Result> response;
}
