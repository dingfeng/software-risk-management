package cn.edu.nju.software.dao;

import cn.edu.nju.software.entity.Risk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by FD on 2016/11/6.
 */
@Repository
public interface RiskDao extends JpaRepository<Risk,Long> {
}
