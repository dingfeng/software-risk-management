package cn.edu.nju.software.controller;

import cn.edu.nju.software.entity.User;
import cn.edu.nju.software.service.UserService;
import cn.edu.nju.software.util.ResultDTO;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 邹玉鑫 on 2016/11/7.
 */
@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Resource
    UserService userService;

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    @ResponseBody
    public String verifyUser(@RequestParam String username, @RequestParam String password , HttpSession httpSession) {
        JSONObject jsonObject = new JSONObject();
        try{
            ResultDTO<User> userResultDTO  = userService.queryUserByName(username);

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

            if(password.equals(user.getPassword())){
                jsonObject.put("isSuccess", true);
                jsonObject.put("data" , user );
                httpSession.setAttribute("userId",user.getId());
                httpSession.setAttribute("userName",user.getAccount());
            }else{
                jsonObject.put("isSuccess", false);
                jsonObject.put("errMsg", "密码错误");
            }

            return jsonObject.toJSONString();



        }catch(Exception e){
            log.error("exception in user_login ", e);
            jsonObject.put("isSuccess", false);
            jsonObject.put("errMsg", "服务器异常");
            return jsonObject.toJSONString();
        }
    }

    @RequestMapping(value = "/role", method = RequestMethod.POST)
    @ResponseBody
    public String getRole(@RequestParam String sessionid) {
        System.out.println(sessionid);
        return "admin";
    }

    @RequestMapping(value = "/username", method = RequestMethod.POST)
    @ResponseBody
    public String getUsername(@RequestParam String sessionid) {
        System.out.println(sessionid);
        return "larry.zou";
    }
}
