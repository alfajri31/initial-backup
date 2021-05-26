package com.appintimedia.apifootball.orm;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="away")
@Data
public class AwayOrm {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_match_id")
    private TeamMatchOrm teamMatchOrm;

    private String name;
    @Column(name="logo_url")
    private String logoUrl;
    private int goals;
    private boolean winner;
    private String formation;
    private String coach;

}
