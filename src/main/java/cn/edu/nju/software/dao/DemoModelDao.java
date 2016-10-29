package cn.edu.nju.software.dao;

import cn.edu.nju.software.entity.DemoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 丁峰
 * @date 2016/10/29 21:23
 * @see DemoModelDao
 */
@Repository
public interface DemoModelDao extends JpaRepository<DemoModel,Long> {
     @Query("select t from DemoModel t where t.value=:value")
     DemoModel findByValue(@Param("value") String value);
}
