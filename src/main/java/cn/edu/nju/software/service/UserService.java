package cn.edu.nju.software.service;

import cn.edu.nju.software.entity.User;
import cn.edu.nju.software.util.ResultDTO;

import java.util.List;

/**
 * Created by zy118686 on 2016/11/6.
 */
public interface UserService {

    public ResultDTO<User> saveorUpdateUser(User user);

    public ResultDTO<User> deleteUser(User user);

    public ResultDTO<User> queryUserById(String Id);

    public ResultDTO<User> queryUserByName(String userName);

    //查询接口的参数有待商榷,可能要开多个接口
    public ResultDTO<List<User>> queryUser();
}
