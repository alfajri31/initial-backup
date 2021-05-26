package com.appintimedia.apifootball.orm;

import com.appintimedia.apifootball.model.app.Away;
import com.appintimedia.apifootball.model.app.Home;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="team_match ")
@Data
public class TeamMatchOrm  {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "teamMatchOrm", targetEntity = HomeOrm.class)
    private List<Home> home;

    @OneToMany(mappedBy = "teamMatchOrm", targetEntity = AwayOrm.class)
    private List<Away> away;

    @Column(name = "league_id")
    private int leagueId;

    @Column(name="user_id")
    private int userId;

    @Column(name="home_id")
    private int homeId;

    @Column(name="away_id")
    private int awayId;

    private int elapsed;

    private String date;

    @Column(nullable = true)
    private String stadium;

    @Column(name="kick_start")
    private Timestamp kickStart;

    @Column(nullable = true)
    private String referee;

    private Boolean live;



}
