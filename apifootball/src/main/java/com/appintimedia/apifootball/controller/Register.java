package com.appintimedia.apifootball.controller;


import com.appintimedia.apifootball.service.IFootballApi;
import com.appintimedia.apifootball.model.app.date.LeagueListApp;
import com.appintimedia.apifootball.model.base.RawDateScore;
import com.appintimedia.apifootball.model.base.RawLiveScore;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Register {
    @Autowired
    private IFootballApi iAuthRequest;

    @RequestMapping(method = RequestMethod.GET, value = "/api/javainuse/timezone")
    public Response checkTimezone()  throws Exception {
        System.out.print("Dasdsadas");
        Response response = iAuthRequest.timezone("/v3/timezone");
        return response;
    }

    @RequestMapping(value="/api/javainuse/date-score",method= RequestMethod.GET)
    public RawDateScore rawScoreDate(String date)  throws Exception
    {
        RawDateScore response = iAuthRequest.rawDateScore(   "/v3/fixtures",date);
        return response;
    }

    @RequestMapping(value="/api/javainuse/live-score",method=RequestMethod.GET)
    public RawLiveScore rawLiveScore(String live)  throws Exception
    {
        RawLiveScore response = iAuthRequest.rawLiveScore(   "/v3/fixtures",live);
        return response;
    }

    @RequestMapping(value="/api/javainuse/app/date-score",method=RequestMethod.GET)
    public List<LeagueListApp> dateScore(String date)  throws Exception
    {
        List<LeagueListApp> response = iAuthRequest.scoreByDate("v3/fixtures",date);
        return response;
    }

}
