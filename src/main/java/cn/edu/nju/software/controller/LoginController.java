package cn.edu.nju.software.controller;

import cn.edu.nju.software.entity.User;
import cn.edu.nju.software.enums.UserRole;
import cn.edu.nju.software.service.UserService;
import cn.edu.nju.software.util.ResultDTO;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
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

    @RequestMapping(value = "/verify")
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

            String pwd = Hashing.md5().newHasher().putString(password, Charsets.UTF_8).hash().toString();

            if(pwd.equals(user.getPassword())){
                jsonObject.put("isSuccess", true);
                jsonObject.put("data" , user );
                httpSession.setAttribute("userId",String.valueOf(user.getId()));
                httpSession.setAttribute("userName",user.getAccount());
                switch (user.getRole()) {
                    case SYSTEM_MANAGER: {
                        httpSession.setAttribute("userRole","系统管理员");
                        break;
                    }

                    case DIRECTOR: {
                        httpSession.setAttribute("userRole","主管");
                        break;
                    }

                    case NORMAL: {
                        httpSession.setAttribute("userRole","普通用户");
                        break;
                    }

                    default: {
                        httpSession.setAttribute("userRole","角色异常");
                    }
                }
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

    @RequestMapping(value = "/role")
    @ResponseBody
    public String getRole(HttpSession httpSession) {
        return "admin";
    }

    @RequestMapping(value = "/username", method = RequestMethod.POST)
    @ResponseBody
    public String getUsername(HttpSession httpSession) {
       try{
           return (String)httpSession.getAttribute("userName");
       }catch (Exception e){
           log.error("exception in login_getUsername ",e);
           return "用户名异常";
       }
    }
}
