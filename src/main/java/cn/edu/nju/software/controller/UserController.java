package cn.edu.nju.software.controller;

import cn.edu.nju.software.entity.User;
import cn.edu.nju.software.service.UserService;
import cn.edu.nju.software.util.ResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by zy118686 on 2016/11/7.
 */
@RestController
@Slf4j
public class UserController {

    @Resource
    UserService userService;


    /** 方法返回值还会改 **/

    @RequestMapping("/login")
    public User login(@RequestParam(value="userName", defaultValue="") String userName , @RequestParam(value="password", defaultValue="") String password) {
        try{
            ResultDTO<User> userResultDTO  = userService.queryUserByName(userName);

        }catch(Exception e){
            log.error("exception in user_login ", e);
        }
        return null;
    }

    @RequestMapping("/register")
    public User register(@RequestParam(value="userName", defaultValue="") String userName , @RequestParam(value="password", defaultValue="") String password, @RequestParam(value="email", defaultValue="") String email ,  @RequestParam(value="role", defaultValue="") String role ){
            return new User();
    }

    @RequestMapping("/index")
    public User index(@RequestParam(value="userId", defaultValue="") String userId){
        try{
            ResultDTO<User> userResultDTO = userService.queryUserById(userId);
            if(!userResultDTO.isSuccess()){
                if(userResultDTO.getErrorMsg() == null){

                }else{

                }
            }
        }catch (Exception e){
            log.error("exception in user_index ", e);
        }

        return null;
    }
}
