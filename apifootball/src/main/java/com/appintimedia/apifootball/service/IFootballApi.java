package com.appintimedia.apifootball.service;

import com.appintimedia.apifootball.model.app.SingleDateResponse;
import com.appintimedia.apifootball.model.app.TeamMatch;

import java.util.List;


public interface IFootballApi {
    SingleDateResponse singleDateResult(String endpoint, String date) throws Exception;
    List<TeamMatch> singleDate(String endpoint, String date) throws Exception;
}
