package com.appintimedia.apifootball.service;

import com.appintimedia.apifootball.model.app.date.LeagueListApp;
import com.appintimedia.apifootball.model.base.RawDateScore;
import com.appintimedia.apifootball.model.base.RawLiveScore;
import okhttp3.Response;

import java.util.List;


public interface IFootballApi {
    Response timezone(String endpoint) throws Exception;
    RawDateScore rawDateScore(String endpoint, String date) throws Exception;
    RawLiveScore rawLiveScore(String endpoint, String live) throws Exception;
    List<LeagueListApp> scoreByDate(String endpoint, String date) throws Exception;
}
