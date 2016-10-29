package cn.edu.nju.software.controller;

import cn.edu.nju.software.dao.DemoModelDao;
import cn.edu.nju.software.entity.DemoModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

/**
 * @author 丁峰
 * @date 2016/10/23 23:25
 * @see WelcomeController
 */
@Controller
@Slf4j
public class WelcomeController {
    @Autowired
    private DemoModelDao demoModelDao;

    @PostConstruct
    public void init()
    {
        DemoModel demoModel = new DemoModel();
        demoModel.setValue("dww");
        demoModelDao.save(demoModel);
        log.error("sdf");
    }
    @RequestMapping("/")
    public String helloWorld(Model model) {
        model.addAttribute("russian", "Добрый день");
        return "hello-world";
    }
}
