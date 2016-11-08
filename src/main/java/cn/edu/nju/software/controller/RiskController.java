package cn.edu.nju.software.controller;

import cn.edu.nju.software.service.RiskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by zy118686 on 2016/11/7.
 */
@RestController
@Slf4j
public class RiskController {

    @Resource
    RiskService riskService;
}
