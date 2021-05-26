package com.appintimedia.apifootball.dao;

import com.appintimedia.apifootball.orm.AwayOrm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IAway extends JpaRepository<AwayOrm, String>, JpaSpecificationExecutor<AwayOrm> {
}
