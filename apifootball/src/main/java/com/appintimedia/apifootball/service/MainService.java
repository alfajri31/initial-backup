package com.appintimedia.apifootball.service;

import com.appintimedia.apifootball.model.app.date.LeagueApp;
import com.appintimedia.apifootball.model.app.date.LeagueListApp;
import com.appintimedia.apifootball.model.base.RawDateScore;
import com.appintimedia.apifootball.model.base.RawLiveScore;
import com.appintimedia.apifootball.model.base.Result;
import com.appintimedia.apifootball.model.base.Teams;
import com.appintimedia.apifootball.utils.HttpUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.stream.Collectors;


@Service
public class MainService implements IFootballApi {

    @Autowired
    private PropertiesReader propertiesReader;

    @Autowired
    private RawDateScore scoreByDate;


    @Override
    public Response timezone(String endpoint) throws Exception {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api-football-v1.p.rapidapi.com/"+endpoint)
                .get()
                .addHeader("x-rapidapi-key", propertiesReader.getProperty("api_key"))
                .addHeader("x-rapidapi-host", propertiesReader.getProperty("api_host"))
                .build();
        Response response = client.newCall(request).execute();

        return response;
    }

    @Override
    public RawDateScore rawDateScore(String endpoint, String date) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject result;
        HttpURLConnection conn =  HttpUtils.createURLConnection("https://api-football-v1.p.rapidapi.com/"+endpoint+"?"+"date="+date, "GET");
        conn.setRequestProperty("x-rapidapi-key", propertiesReader.getProperty("api_key"));
        conn.setRequestProperty("x-rapidapi-host",propertiesReader.getProperty("api_host"));

        int statusCode = conn.getResponseCode();

        RawDateScore response;
        if (statusCode == HttpStatus.OK.value()) {
            result = HttpUtils.getJsonResponse(conn);
            response = objectMapper
                    .readValue(result.toString(), new TypeReference<RawDateScore>(){});
            System.out.print(response);
        }
        else {
            result = HttpUtils.getJsonErrorResponse(conn);
            throw new Exception();
        }

        return response;
    }

    @Override
    public RawLiveScore rawLiveScore(String endpoint, String live) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject result;
        HttpURLConnection conn = HttpUtils.createURLConnection("https://api-football-v1.p.rapidapi.com/"+endpoint+"?"+"date="+live, "GET");
        conn.setRequestProperty("x-rapidapi-key", propertiesReader.getProperty("api_key"));
        conn.setRequestProperty("x-rapidapi-host",propertiesReader.getProperty("api_host"));

        int statusCode = conn.getResponseCode();

        RawLiveScore response;
        if (statusCode == HttpStatus.OK.value()) {
            result = HttpUtils.getJsonResponse(conn);
            response = objectMapper
                    .readValue(result.toString(), new TypeReference<RawLiveScore>(){});
            System.out.print(response);
        }
        else {
            result = HttpUtils.getJsonErrorResponse(conn);
            throw new Exception();
        }

        return response;
    }

    @Override
    public List<LeagueListApp> scoreByDate(String endpoint, String date) throws Exception {
        RawDateScore dateScore = rawDateScore(endpoint,date);

        List<Result> results = new ArrayList<>();
        dateScore.getResponse().forEach(withCounter((i,statistic) -> {
          results.add(statistic);
        }));

        List<LeagueApp> leagueAppList = new ArrayList<>();
        results.forEach(withCounter((i,statistic) -> {
            LeagueApp league = new LeagueApp();
            league.setName(statistic.getLeague().getName());
            league.setTeams(statistic.getTeams());
            league.getTeams().getHome().setGoal(statistic.getGoals().getHome());
            league.getTeams().getAway().setGoal(statistic.getGoals().getAway());
            leagueAppList.add(i,league);
        }));

        Comparator<LeagueApp> compareByName = Comparator.comparing(LeagueApp::getName);
        Collections.sort(leagueAppList, compareByName);

        List<LeagueApp> leagueAppSortList = leagueAppList.stream().collect(Collectors.toList());

        List<LeagueListApp> leagueListApps = new ArrayList<>();
        List<Teams> teams = new ArrayList<>();
        AtomicReference<String> nextPoint= new AtomicReference<>(leagueAppSortList.get(0).getName());
        final int[] j = {0};
        final int[] k = {0};
        leagueAppList.forEach(withCounter((i,statistic) -> {
            String currentPoint = statistic.getName();
            if(currentPoint.equals(nextPoint.toString())) {
                //add teams
                teams.add(statistic.getTeams());
                if(leagueAppList.get(i+1).getName()!=null) {
                    nextPoint.set(leagueAppList.get(k[0]).getName());
                    k[0] = teams.toArray().length + 2;
                }
            }
            else if(!currentPoint.equals(nextPoint.toString())) {
                teams.add(statistic.getTeams());
                List<Teams> getTeam = new ArrayList<>();
                getTeam.addAll(teams);
                LeagueListApp leagueListApp = new LeagueListApp();
                leagueListApp.setName(currentPoint);
                leagueListApp.setTeams(getTeam);
                leagueListApps.add(j[0] ,leagueListApp);
                teams.removeAll(teams);
                j[0] = j[0] + 1;
            }
        }));

        return leagueListApps ;
    }


    public static <T> Consumer<T> withCounter(BiConsumer<Integer, T> consumer) {
        AtomicInteger counter = new AtomicInteger(0);
        return item -> consumer.accept(counter.getAndIncrement(), item);
    }



}
