package com.appintimedia.apifootball.service;

import com.appintimedia.apifootball.dao.IAway;
import com.appintimedia.apifootball.dao.IHome;
import com.appintimedia.apifootball.dao.ITeamMatch;
import com.appintimedia.apifootball.model.app.Result;
import com.appintimedia.apifootball.model.app.SingleDateResponse;
import com.appintimedia.apifootball.model.app.TeamMatch;
import com.appintimedia.apifootball.orm.AwayOrm;
import com.appintimedia.apifootball.orm.HomeOrm;
import com.appintimedia.apifootball.orm.TeamMatchOrm;
import com.appintimedia.apifootball.utils.HttpUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;


@Service
public class MainService implements IFootballApi {

    @Autowired
    private PropertiesReader propertiesReader;

    @Autowired
    private ITeamMatch iTeamMatch;

    @Autowired
    private IHome Ihome;

    @Autowired
    private IAway Iaway;


    @Override
    public SingleDateResponse singleDateResult(String endpoint, String date) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject result;
        HttpURLConnection conn =  HttpUtils.createURLConnection("https://api-football-v1.p.rapidapi.com/"+endpoint+"?"+"date="+date, "GET");
        conn.setRequestProperty("x-rapidapi-key", propertiesReader.getProperty("api_key"));
        conn.setRequestProperty("x-rapidapi-host",propertiesReader.getProperty("api_host"));

        int statusCode = conn.getResponseCode();

        SingleDateResponse response;
        if (statusCode == HttpStatus.OK.value()) {
            result = HttpUtils.getJsonResponse(conn);
            response = objectMapper
                    .readValue(result.toString(), new TypeReference<SingleDateResponse>(){});
        }
        else {
            result = HttpUtils.getJsonErrorResponse(conn);
            throw new Exception();
        }

        return response;
    }

    @Override
    public List<TeamMatch> singleDate(String endpoint, String date) throws Exception {

        SingleDateResponse singleDateResponse = singleDateResult(endpoint,date);

        List<Result> results = new ArrayList<>();
        singleDateResponse.getResponse().forEach(withCounter((i, statistic) -> {
          results.add(statistic);
        }));

        List<TeamMatch> teamMatches = new ArrayList<>();

        results.forEach(withCounter((i,statistic) -> {

//            TeamMatch teamMatch = new TeamMatch();
//            teamMatch.setId(statistic.getLeague().getId());
//            teamMatch.setName(statistic.getLeague().getName());
//            teamMatch.setTeams(statistic.getTeams());
//
//            teamMatch.getTeams().getHome().setGoal(statistic.getGoals().getHome());
//            teamMatch.getTeams().getAway().setGoal(statistic.getGoals().getAway());
//
//            Status status = new Status();
//
//            teamMatch.setStatus(status);
//            teamMatch.getStatus().setLongMatch(statistic.getFixture().getStatus().getLongMatch());
//            teamMatch.getStatus().setShortMatch(statistic.getFixture().getStatus().getShortMatch());
//            teamMatch.getStatus().setElapsed(statistic.getFixture().getStatus().getElapsed());
//
//            Score score = new Score();
//            Fulltime fulltime = new Fulltime();
//            Halftime halftime = new Halftime();
//            Extratime extratime = new Extratime();
//            Penalty penalty = new Penalty();
//
//            teamMatch.getTeams().getHome().setScore(score);
//            teamMatch.getTeams().getHome().getScore().setFulltime(fulltime);
//            teamMatch.getTeams().getHome().getScore().setHalftime(halftime);
//            teamMatch.getTeams().getHome().getScore().setExtratime(extratime);
//            teamMatch.getTeams().getHome().getScore().setPenalty(penalty);
//            teamMatch.getTeams().getHome().getScore().getFulltime().setHome(statistic.getScore().getFulltime().getHome());
//            teamMatch.getTeams().getHome().getScore().getHalftime().setHome(statistic.getScore().getHalftime().getHome());
//            teamMatch.getTeams().getHome().getScore().getExtratime().setHome(statistic.getScore().getExtratime().getHome());
//            teamMatch.getTeams().getHome().getScore().getPenalty().setHome(statistic.getScore().getPenalty().getHome());
//            teamMatch.getTeams().getAway().setScore(score);
//            teamMatch.getTeams().getAway().getScore().setFulltime(fulltime);
//            teamMatch.getTeams().getAway().getScore().setHalftime(halftime);
//            teamMatch.getTeams().getAway().getScore().setExtratime(extratime);
//            teamMatch.getTeams().getAway().getScore().setPenalty(penalty);
//            teamMatch.getTeams().getAway().getScore().getFulltime().setAway(statistic.getScore().getFulltime().getAway());
//            teamMatch.getTeams().getAway().getScore().getHalftime().setAway(statistic.getScore().getHalftime().getAway());
//            teamMatch.getTeams().getAway().getScore().getExtratime().setAway(statistic.getScore().getExtratime().getAway());
//            teamMatch.getTeams().getAway().getScore().getPenalty().setAway(statistic.getScore().getPenalty().getAway());


            try {
                TeamMatchOrm teamMatchOrm = new TeamMatchOrm();
                teamMatchOrm.setUserId(0);
                teamMatchOrm.setLeagueId(statistic.getLeague().getId());
                teamMatchOrm.setHomeId(statistic.getTeams().getHome().getId());
                teamMatchOrm.setAwayId(statistic.getTeams().getAway().getId());
                teamMatchOrm.setElapsed(statistic.getFixture().getStatus().getElapsed());
                teamMatchOrm.setDate(date);
                teamMatchOrm.setStadium(statistic.getFixture().getVenue().getName());
                teamMatchOrm.setKickStart(statistic.getFixture().getPeriods().getFirst());
                teamMatchOrm.setReferee(statistic.getFixture().getReferee());
                teamMatchOrm.setLive(false);
                iTeamMatch.save(teamMatchOrm);

                HomeOrm homeOrm = new HomeOrm();
                homeOrm.setTeamMatchOrm(teamMatchOrm);
                homeOrm.setName(statistic.getTeams().getHome().getName());
                homeOrm.setLogoUrl(statistic.getTeams().getHome().getLogo());
                homeOrm.setGoals(statistic.getGoals().getHome());
                homeOrm.setWinner(statistic.getTeams().getHome().getWinner());
                homeOrm.setFormation("FORMATION");
                homeOrm.setCoach("COACH");
                Ihome.save(homeOrm);

                AwayOrm awayOrm = new AwayOrm();
                awayOrm.setTeamMatchOrm(teamMatchOrm);
                awayOrm.setName(statistic.getTeams().getAway().getName());
                awayOrm.setLogoUrl(statistic.getTeams().getAway().getLogo());
                awayOrm.setGoals(statistic.getGoals().getAway());
                homeOrm.setWinner(statistic.getTeams().getAway().getWinner());
                awayOrm.setFormation("FORMATION");
                awayOrm.setCoach("COACH");
                Iaway.save(awayOrm);
            }
            catch(NullPointerException e) {

            }
//            teamMatches.add(i,teamMatch);
        }));

//        Comparator<TeamMatch> compareByName = Comparator.comparing(TeamMatch::getName);
//        Collections.sort(teamMatches, compareByName);

        return teamMatches;
    }


    public static <T> Consumer<T> withCounter(BiConsumer<Integer, T> consumer) {
        AtomicInteger counter = new AtomicInteger(0);
        return item -> consumer.accept(counter.getAndIncrement(), item);
    }



}
