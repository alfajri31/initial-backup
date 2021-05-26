package com.appintimedia.apifootball.dao;

import com.appintimedia.apifootball.orm.TeamMatchOrm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ITeamMatch extends JpaRepository<TeamMatchOrm, String>, JpaSpecificationExecutor<TeamMatchOrm> {

}
