package cn.edu.nju.software.dao;

import cn.edu.nju.software.entity.Project;
import cn.edu.nju.software.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by FD on 2016/11/6.
 */
@Repository
public interface UserDao extends JpaRepository<User,Long> {
}
