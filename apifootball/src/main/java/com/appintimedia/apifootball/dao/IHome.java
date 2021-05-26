package com.appintimedia.apifootball.dao;

import com.appintimedia.apifootball.orm.HomeOrm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IHome extends JpaRepository<HomeOrm, String>, JpaSpecificationExecutor<HomeOrm> {
}
