package cn.edu.nju.software.serviceimpl;

import cn.edu.nju.software.dao.UserDao;
import cn.edu.nju.software.entity.User;
import cn.edu.nju.software.service.UserService;
import cn.edu.nju.software.util.ResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zy118686 on 2016/11/6.
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Resource
    UserDao userDao ;

    @Override
    public ResultDTO<User> saveorUpdateUser(User user) {
        ResultDTO<User> resultDTO = new ResultDTO<User>();

        try {
            User u = userDao.save(user);
            if(u == null){
                resultDTO.setSuccess(false);
                resultDTO.setData(null);
                resultDTO.setErrorMsg("Unknown error user is null ");
                log.error("Unknown error user is null");
                return resultDTO;
            }
            resultDTO.setData(u);
            resultDTO.setSuccess(true);
        }catch(Exception e){
            log.error("error in save or update user ",e);
            resultDTO.setErrorMsg(e.getMessage());
            resultDTO.setSuccess(false);
            resultDTO.setData(null);
        }
        return resultDTO;
    }

    @Override
    public ResultDTO<User> deleteUser(Long id ) {
        ResultDTO<User> resultDTO = new ResultDTO<User>();
        try {
            userDao.delete(id);
            resultDTO.setData(null);
            resultDTO.setSuccess(true);
        }catch(Exception e){
            log.error("error in delete user ",e);
            resultDTO.setErrorMsg(e.getMessage());
            resultDTO.setSuccess(false);
            resultDTO.setData(null);
        }
        return resultDTO;
    }

    @Override
    public ResultDTO<User> queryUserById(String Id) {
        ResultDTO<User> resultDTO = new ResultDTO<User>();

        try {
            User u =userDao.findOne(Long.parseLong(Id));
            if(u==null){
                log.warn("no such User the Id is "+Id);
                resultDTO.setSuccess(false);
                resultDTO.setData(u);
                resultDTO.setErrorMsg(null);
                return resultDTO;
            }
            resultDTO.setData(u);
            resultDTO.setSuccess(true);
        }catch(Exception e){
            log.error("error in query User by Id the Id is "+Id,e);
            resultDTO.setErrorMsg(e.getMessage());
            resultDTO.setSuccess(false);
            resultDTO.setData(null);
        }
        return resultDTO;
    }

    @Override
    public ResultDTO<User> queryUserByName(String userName) {
        ResultDTO<User> resultDTO = new ResultDTO<User>();

        try {
            User u =userDao.findByAccount(userName);
            if(u==null){
                log.warn("no such User the username is " + userName);
                resultDTO.setSuccess(false);
                resultDTO.setData(u);
                resultDTO.setErrorMsg("no such User");
                return resultDTO;
            }
            resultDTO.setData(u);
            resultDTO.setSuccess(true);
        }catch(Exception e){
            log.error("error in query User by username the username is "+userName,e);
            resultDTO.setErrorMsg(e.getMessage());
            resultDTO.setSuccess(false);
            resultDTO.setData(null);
        }
        return resultDTO;
    }

    @Override
    public ResultDTO<List<User>> queryUser() {
        ResultDTO<List<User>> resultDTO = new ResultDTO<>();
        try {
            List<User> list =  userDao.findAll();
            resultDTO.setData(list);
            resultDTO.setSuccess(true);
        }catch(Exception e){
            log.error("no users ");
            resultDTO.setErrorMsg(e.getMessage());
            resultDTO.setSuccess(false);
            resultDTO.setData(null);
        }
        return resultDTO;
    }
}
