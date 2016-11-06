package cn.edu.nju.software.dao;

import cn.edu.nju.software.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 丁峰
 * @date 2016/11/6 12:23
 * @see ProjectDao
 */
@Repository
public interface ProjectDao  extends JpaRepository<Project,Long> {
}
