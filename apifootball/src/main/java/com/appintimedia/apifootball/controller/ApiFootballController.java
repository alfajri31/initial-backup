package com.appintimedia.apifootball.controller;


import com.appintimedia.apifootball.model.app.TeamMatch;
import com.appintimedia.apifootball.service.IFootballApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiFootballController {
    @Autowired
    private IFootballApi iAuthRequest;

    @RequestMapping(value="/app/match/single-date",method=RequestMethod.GET)
    public List<TeamMatch> singleDate(String date)  throws Exception
    {
        List<TeamMatch> response = iAuthRequest.singleDate("v3/fixtures",date);
        return response;
    }

}
