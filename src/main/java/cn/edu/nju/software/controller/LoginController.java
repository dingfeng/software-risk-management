package cn.edu.nju.software.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 邹玉鑫 on 2016/11/7.
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    @ResponseBody
    public boolean verifyUser(@RequestParam String username, @RequestParam String password) {
        System.out.println(username + " " + password);
        return true;
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
