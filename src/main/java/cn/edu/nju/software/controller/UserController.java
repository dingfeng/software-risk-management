package cn.edu.nju.software.controller;

import cn.edu.nju.software.entity.User;
import cn.edu.nju.software.enums.UserRole;
import cn.edu.nju.software.service.UserService;
import cn.edu.nju.software.util.ResultDTO;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
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

    @RequestMapping("/login")
    public String login(@RequestParam(value="userName", defaultValue="") String userName , @RequestParam(value="password", defaultValue="") String password) {
        JSONObject jsonObject = new JSONObject();
        try{
            ResultDTO<User> userResultDTO  = userService.queryUserByName(userName);

            if(!userResultDTO.isSuccess()){
                if("no such User".equals(userResultDTO.getErrorMsg())){
                    jsonObject.put("isSuccess", false);
                    jsonObject.put("errMsg", "用户名不存在");
                    return jsonObject.toJSONString();
                }else{
                    jsonObject.put("isSuccess", false);
                    jsonObject.put("errMsg", "服务器异常");
                    return jsonObject.toJSONString();
                }
            }

            User user = userResultDTO.getData();


            jsonObject.put("isSuccess", true);
            jsonObject.put("data" , user );
            return jsonObject.toJSONString();



        }catch(Exception e){
            log.error("exception in user_login ", e);
            jsonObject.put("isSuccess", false);
            jsonObject.put("errMsg", "服务器异常");
            return jsonObject.toJSONString();
        }

    }

    @RequestMapping("/register")
    public String register(@RequestParam(value="userName", defaultValue="") String userName , @RequestParam(value="password", defaultValue="") String password, @RequestParam(value="email", defaultValue="") String email ,  @RequestParam(value="role", defaultValue="") String role ){

        //这边默认已经调用过checkUserName的方法了
        JSONObject jsonObject = new JSONObject();
        try {
            User user = new User();

            //界面上role的值 希望是这样的 1 系统管理员 2 主管 3 普通用户

            //设置role
            Integer roleInt = Integer.parseInt(role);

            switch (roleInt) {
                case 1: {
                    user.setRole(UserRole.SYSTEM_MANAGER);
                    break;
                }

                case 2: {
                    user.setRole(UserRole.DIRECTOR);
                    break;
                }

                case 3: {
                    user.setRole(UserRole.NORMAL);
                    break;
                }

                default: {
                    jsonObject.put("isSuccess", false);
                    jsonObject.put("errMsg", "用户角色出错");
                    return jsonObject.toJSONString();
                }

            }

            //设置password
            String pwd = Hashing.md5().newHasher().putString(password, Charsets.UTF_8).hash().toString();

            user.setPassword(pwd);

            //设置username
            user.setAccount(userName);

            //设置邮箱,鉴于正则表达式效率问题,这边就不判断了,js中判断
            user.setEmail(email);

            ResultDTO<User> userResultDTO = userService.saveorUpdateUser(user);

            if(!userResultDTO.isSuccess()){
                jsonObject.put("isSuccess",false);
                jsonObject.put("errMsg","服务器异常");
                return jsonObject.toJSONString();
            }

            jsonObject.put("data",userResultDTO.getData());
            jsonObject.put("isSuccess",true);
            return jsonObject.toJSONString();

        }catch (Exception e){
            log.error("exception in user_register " , e);
            jsonObject.put("isSuccess",false);
            jsonObject.put("errMsg","服务器异常");
            return jsonObject.toJSONString();
        }
    }

    //判断用户名是否存在,应该在用户注册时离开username输入框后被调用
    @RequestMapping("/checkUserName")
    public String checkUserName(@RequestParam(value="userName", defaultValue="") String userName ){

        JSONObject jsonObject = new JSONObject();

        try{
            ResultDTO<User> userResultDTO = userService.queryUserByName(userName);

            if(!userResultDTO.isSuccess() && "no such User".equals(userResultDTO.getErrorMsg())){
                jsonObject.put("isSuccess",true);
            }else if(!userResultDTO.isSuccess() && !"no such User".equals(userResultDTO.getErrorMsg())){
                jsonObject.put("isSuccess",false);
                jsonObject.put("errMsg","服务器异常");
            }else{
                jsonObject.put("isSuccess",false);
                jsonObject.put("errMsg","用户名已经存");
            }

            return jsonObject.toJSONString();

        }catch(Exception e){

            log.error("exception in user_checkUserName ", e);
            jsonObject.put("isSuccess",false);
            jsonObject.put("errMsg","服务器异常");
            return jsonObject.toJSONString();
        }
    }

    @RequestMapping("/index")
    public String index(@RequestParam(value="userId", defaultValue="") String userId){

        JSONObject jsonObject = new JSONObject();

        try{

            ResultDTO<User> userResultDTO = userService.queryUserById(userId);

            if(!userResultDTO.isSuccess()){

                jsonObject.put("isSuccess",false);

                if(userResultDTO.getErrorMsg() == null){
                    jsonObject.put("errMsg","用户不存在");
                }else{
                    jsonObject.put("errMsg","服务器异常");
                }

                return jsonObject.toJSONString();
            }

            jsonObject.put("data",userResultDTO.getData());
            jsonObject.put("isSuccess",true);
            return jsonObject.toJSONString();
        }catch (Exception e){
            log.error("exception in user_index ", e);
            jsonObject.put("isSuccess",false);
            jsonObject.put("errMsg","服务器异常");
            return jsonObject.toJSONString();
        }

    }
}
