package cn.edu.nju.software.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 丁峰
 * @date 2016/10/23 23:25
 * @see WelcomeController
 */
@Controller
public class WelcomeController {
    @RequestMapping("/")
    public String helloWorld(Model model) {
        model.addAttribute("russian", "Добрый день");
        return "hello-world";
    }
}
