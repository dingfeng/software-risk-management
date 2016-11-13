package cn.edu.nju.software.controller;

import cn.edu.nju.software.VO.RiskVO;
import cn.edu.nju.software.entity.Project;
import cn.edu.nju.software.entity.Risk;
import cn.edu.nju.software.entity.User;
import cn.edu.nju.software.enums.RiskInfluence;
import cn.edu.nju.software.enums.RiskPossibility;
import cn.edu.nju.software.enums.RiskStatus;
import cn.edu.nju.software.enums.UserRole;
import cn.edu.nju.software.service.ProjectService;
import cn.edu.nju.software.service.RiskService;
import cn.edu.nju.software.service.UserService;
import cn.edu.nju.software.util.ResultDTO;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zy118686 on 2016/11/7.
 */
@RestController
@RequestMapping("/risk")
@Slf4j
public class RiskController {

    @Resource
    RiskService riskService;

    @Resource
    ProjectService  projectService;

    @Resource
    UserService userService;

    @RequestMapping("/checkHandler")
    public String checkHandler( @RequestParam(value="handlerName", defaultValue="") String handlerName){
        JSONObject jsonObject = new JSONObject();

        try{
            ResultDTO<User> riskResultDTO = new ResultDTO<>();

            riskResultDTO = userService.queryUserByName(handlerName);

            if(!riskResultDTO.isSuccess()){
                if(riskResultDTO.getErrorMsg() == null){
                    jsonObject.put("isSuccess", false);
                    jsonObject.put("errMsg", "用户不存在");
                    return jsonObject.toJSONString();
                }else{
                    jsonObject.put("isSuccess", false);
                    jsonObject.put("errMsg", "服务器异常");
                    return jsonObject.toJSONString();
                }
            }


            jsonObject.put("data",riskResultDTO.getData());
            jsonObject.put("isSuccess",true);
            return jsonObject.toJSONString();


        }catch (Exception e){
            log.error("Exception in risk_checkHandler ",e);
            jsonObject.put("isSuccess", false);
            jsonObject.put("errMsg", "服务器异常");
            return jsonObject.toJSONString();
        }
    }

    @RequestMapping("/createRisk")
    public String createRisk(@RequestParam(value="title", defaultValue="") String title ,
                             @RequestParam(value="possibility", defaultValue="") String possibility,
                             @RequestParam(value="influence", defaultValue="") String influence,
                             @RequestParam(value="trigger", defaultValue="") String trigger,
                             @RequestParam(value="description", defaultValue="") String description,
                             @RequestParam(value="handler", defaultValue="") String handlerId,
                             @RequestParam(value="projectId", defaultValue="") String projectId,
                             HttpSession httpSession) {


        JSONObject jsonObject = new JSONObject();

        try{

            ResultDTO<Risk> riskResultDTO = new ResultDTO<>();

            ResultDTO<User> userResultDTO = new ResultDTO<>();

            userResultDTO = userService.queryUserByName(handlerId);

            if(!userResultDTO.isSuccess()){
                jsonObject.put("isSuccess", false);
                jsonObject.put("errMsg", "处理者信息错误");
                return jsonObject.toJSONString();
            }

            Risk risk = new Risk();

            Long userId = null ;

            try {
                 userId = Long.parseLong((String) httpSession.getAttribute("userId"));
            }catch (Exception e){
                log.error("Exception in risk_create ",e);
                jsonObject.put("isSuccess", false);
                jsonObject.put("errMsg", "创建者信息错误");
                return jsonObject.toJSONString();
            }

            User user = new User();

            user.setId(userId);

            risk.setAuthor(user);

            Project project = new Project();

            Long projectIdLong = null;

            try {
                projectIdLong = Long.parseLong(projectId);
            }catch (Exception e){
                log.error("Exception in risk_create ",e);
                jsonObject.put("isSuccess", false);
                jsonObject.put("errMsg", "项目信息错误");
                return jsonObject.toJSONString();
            }

            project.setId(projectIdLong);

            risk.setProject(project);

            Long handlerIdLong  = null;

            User handler = userResultDTO.getData();

            risk.setHandler(handler);

            risk.setTitle(title);

            risk.setTrigger(trigger);

            risk.setDescription(description);

            risk.setStatus(RiskStatus.DOING);

            risk.setUpdatedAt(new Date());

            risk.setCreatedAt(new Date());

            try{
                setInfluence(risk,influence);
            }catch (Exception e){
                log.error("Exception in risk_create ",e);
                jsonObject.put("isSuccess", false);
                jsonObject.put("errMsg", "非法的影响级别");
                return jsonObject.toJSONString();
            }

            try{
                setPossibility(risk,possibility);
            }catch (Exception e){
                log.error("Exception in risk_create ",e);
                jsonObject.put("isSuccess", false);
                jsonObject.put("errMsg", "非法的可能性");
                return jsonObject.toJSONString();
            }

            riskResultDTO = riskService.saveorUpdateRisk(risk);

            if (!riskResultDTO.isSuccess()){
                jsonObject.put("isSuccess",false);
                jsonObject.put("errMsg","服务器异常");
                return jsonObject.toJSONString();
            }

            jsonObject.put("data",riskResultDTO.getData());
            jsonObject.put("isSuccess",true);
            return jsonObject.toJSONString();

        }catch (Exception e){
            log.error("Exception in risk_create ",e);
            jsonObject.put("isSuccess", false);
            jsonObject.put("errMsg", "服务器异常");
            return jsonObject.toJSONString();
        }

    }

    @RequestMapping("/getRiskById")
    public String getRiskById( @RequestParam(value="riskId", defaultValue="") String riskId){
        JSONObject jsonObject = new JSONObject();

        try{
            ResultDTO<Risk> queryRiskDTO = riskService.queryRiskById(riskId);

            RiskVO  riskVO = new RiskVO();

            if(!queryRiskDTO.isSuccess()){
                if(queryRiskDTO.getErrorMsg() == null){
                    jsonObject.put("isSuccess", false);
                    jsonObject.put("errMsg", "risk不存在");
                    return jsonObject.toJSONString();
                }else{
                    jsonObject.put("isSuccess", false);
                    jsonObject.put("errMsg", "服务器异常");
                    return jsonObject.toJSONString();
                }
            }

            DateFormat fmt =new
                    SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Risk risk = queryRiskDTO.getData();

            riskVO.setId(risk.getId());
            riskVO.setUpdatedAt(fmt.format(risk.getUpdatedAt()));
            riskVO.setDescription(risk.getDescription());
            riskVO.setCreatedBy(risk.getAuthor().getAccount());
            riskVO.setCreatedAt(fmt.format(risk.getCreatedAt()));
            riskVO.setHandler(risk.getHandler().getAccount());
            riskVO.setProject(risk.getProject().getName());
            riskVO.setTrigger(risk.getTrigger());
            riskVO.setTitle(risk.getTitle());
            riskVO.setInfluence(risk.getInfluence().getDescription());
            riskVO.setPossibility(risk.getPossibility().getDescription());
            riskVO.setStatus(risk.getStatus().getDescription());

            jsonObject.put("data", riskVO);
            jsonObject.put("isSuccess", true);
            return jsonObject.toJSONString();

        }catch (Exception e){
            log.error("Exception in risk_getRiskById ",e);
            jsonObject.put("isSuccess", false);
            jsonObject.put("errMsg", "服务器异常");
            return jsonObject.toJSONString();
        }
    }


    @RequestMapping("/updateRisk")
    public String updateRisk(@RequestParam(value="status", defaultValue="") String status,
                             @RequestParam(value="description", defaultValue="") String description,
                             @RequestParam(value="riskId", defaultValue="") String riskId){

        JSONObject jsonObject = new JSONObject();

        try{

            ResultDTO<Risk> riskResultDTO = new ResultDTO<>();

            ResultDTO<Risk> queryRiskDTO = riskService.queryRiskById(riskId);

            if(!queryRiskDTO.isSuccess()){
                if(queryRiskDTO.getErrorMsg() == null){
                    jsonObject.put("isSuccess", false);
                    jsonObject.put("errMsg", "risk不存在");
                    return jsonObject.toJSONString();
                }else{
                    jsonObject.put("isSuccess", false);
                    jsonObject.put("errMsg", "服务器异常");
                    return jsonObject.toJSONString();
                }
            }

            Risk risk = queryRiskDTO.getData();

            Long handlerIdLong  = null;



            risk.setDescription(description);

            risk.setUpdatedAt(new Date());


            if("2".equals(status)){
                risk.setStatus(RiskStatus.DONE);
            }else{
                risk.setStatus(RiskStatus.DOING);
            }


            riskResultDTO = riskService.saveorUpdateRisk(risk);

            if (!riskResultDTO.isSuccess()){
                jsonObject.put("isSuccess",false);
                jsonObject.put("errMsg","服务器异常");
                return jsonObject.toJSONString();
            }

            jsonObject.put("data",riskResultDTO.getData());
            jsonObject.put("isSuccess",true);
            return jsonObject.toJSONString();

        }catch (Exception e){
            log.error("Exception in risk_create ",e);
            jsonObject.put("isSuccess", false);
            jsonObject.put("errMsg", "服务器异常");
            return jsonObject.toJSONString();
        }

    }


    //根据projectId 得到 该项目的所有Risk
    @RequestMapping("/getRiskByProject")
    public String getRiskByProject( @RequestParam(value="projectId", defaultValue="") String projectId){

        JSONObject jsonObject = new JSONObject();

        try{

            ResultDTO<Project> resultDTO = projectService.queryProjectById(projectId);

            if(!resultDTO.isSuccess()){
                if(resultDTO.getErrorMsg() == null ){
                    jsonObject.put("isSuccess", false);
                    jsonObject.put("errMsg", "项目Id出错");
                    return jsonObject.toJSONString();
                }else{
                    jsonObject.put("isSuccess", false);
                    jsonObject.put("errMsg", "服务器异常");
                    return jsonObject.toJSONString();
                }
            }

            jsonObject.put("data",resultDTO.getData().getRisks());
            jsonObject.put("isSuccess",true);
            return jsonObject.toJSONString();

        }catch (Exception e){
            log.error("Exception in risk_getRisk ",e);
            jsonObject.put("isSuccess", false);
            jsonObject.put("errMsg", "服务器异常");
            return jsonObject.toJSONString();
        }
    }

    @RequestMapping("/accepptRisk")
    public String accepptRisk( @RequestParam(value="riskId", defaultValue="") String riskId ,HttpSession httpSession){

        ResultDTO<Risk> queryRiskDTO = riskService.queryRiskById(riskId);

        ResultDTO<Risk> riskResultDTO = new ResultDTO<>();

        JSONObject jsonObject = new JSONObject();

        try {
            if (!queryRiskDTO.isSuccess()) {
                if (queryRiskDTO.getErrorMsg() == null) {
                    jsonObject.put("isSuccess", false);
                    jsonObject.put("errMsg", "risk不存在");
                    return jsonObject.toJSONString();
                } else {
                    jsonObject.put("isSuccess", false);
                    jsonObject.put("errMsg", "服务器异常");
                    return jsonObject.toJSONString();
                }
            }


            Risk risk = queryRiskDTO.getData();

            risk.setStatus(RiskStatus.DOING);

            riskResultDTO = riskService.saveorUpdateRisk(risk);

            if (!riskResultDTO.isSuccess()) {
                jsonObject.put("isSuccess", false);
                jsonObject.put("errMsg", "服务器异常");
                return jsonObject.toJSONString();
            }

            jsonObject.put("data", riskResultDTO.getData());
            jsonObject.put("isSuccess", true);
            return jsonObject.toJSONString();
        }catch (Exception e){
            log.error("exception in risk_acceptRisk",e);
            jsonObject.put("isSuccess", false);
            jsonObject.put("errMsg", "服务器异常");
            return jsonObject.toJSONString();
        }
    }

    @RequestMapping("/doneRisk")
    public String doneRisk( @RequestParam(value="riskId", defaultValue="") String riskId){

        ResultDTO<Risk> queryRiskDTO = riskService.queryRiskById(riskId);

        ResultDTO<Risk> riskResultDTO = new ResultDTO<>();

        JSONObject jsonObject = new JSONObject();

        try {
            if (!queryRiskDTO.isSuccess()) {
                if (queryRiskDTO.getErrorMsg() == null) {
                    jsonObject.put("isSuccess", false);
                    jsonObject.put("errMsg", "risk不存在");
                    return jsonObject.toJSONString();
                } else {
                    jsonObject.put("isSuccess", false);
                    jsonObject.put("errMsg", "服务器异常");
                    return jsonObject.toJSONString();
                }
            }

            Risk risk = queryRiskDTO.getData();

            risk.setStatus(RiskStatus.DONE);

            riskResultDTO = riskService.saveorUpdateRisk(risk);

            if (!riskResultDTO.isSuccess()) {
                jsonObject.put("isSuccess", false);
                jsonObject.put("errMsg", "服务器异常");
                return jsonObject.toJSONString();
            }

            jsonObject.put("data", riskResultDTO.getData());
            jsonObject.put("isSuccess", true);
            return jsonObject.toJSONString();
        }catch (Exception e){
            log.error("exception in risk_doneRisk");
            jsonObject.put("isSuccess", false);
            jsonObject.put("errMsg", "服务器异常");
            return jsonObject.toJSONString();
        }

    }

    @RequestMapping("/rejectRisk")
    public String rejectRisk( @RequestParam(value="riskId", defaultValue="") String riskId){
        ResultDTO<Risk> queryRiskDTO = riskService.queryRiskById(riskId);

        ResultDTO<Risk> riskResultDTO = new ResultDTO<>();

        JSONObject jsonObject = new JSONObject();

        try {
            if (!queryRiskDTO.isSuccess()) {
                if (queryRiskDTO.getErrorMsg() == null) {
                    jsonObject.put("isSuccess", false);
                    jsonObject.put("errMsg", "risk不存在");
                    return jsonObject.toJSONString();
                } else {
                    jsonObject.put("isSuccess", false);
                    jsonObject.put("errMsg", "服务器异常");
                    return jsonObject.toJSONString();
                }
            }

            Risk risk = queryRiskDTO.getData();

            risk.setStatus(RiskStatus.REJECTED);

            riskResultDTO = riskService.saveorUpdateRisk(risk);

            if (!riskResultDTO.isSuccess()) {
                jsonObject.put("isSuccess", false);
                jsonObject.put("errMsg", "服务器异常");
                return jsonObject.toJSONString();
            }

            jsonObject.put("data", riskResultDTO.getData());
            jsonObject.put("isSuccess", true);
            return jsonObject.toJSONString();
        }catch (Exception e){
            log.error("exception in risk_rejectRisk");
            jsonObject.put("isSuccess", false);
            jsonObject.put("errMsg", "服务器异常");
            return jsonObject.toJSONString();
        }
    }

        private void setInfluence(Risk risk , String influence ) throws Exception{

        //设置influence 1 HIGH 2 MEDIUM 3 LOW
        Integer influenceInt = Integer.parseInt(influence);

        switch (influenceInt) {
            case 1: {
                risk.setInfluence(RiskInfluence.HIGH);
                break;
            }

            case 2: {
                risk.setInfluence(RiskInfluence.MEDIUM);
                break;
            }

            case 3: {
                risk.setInfluence(RiskInfluence.LOW);
                break;
            }

            default: {
                throw new Exception();
            }

        }
    }

    private void setPossibility(Risk risk , String possibility ) throws Exception{

        //设置possibility 1 HIGH 2 MEDIUM 3 LOW
        Integer possibilityInt = Integer.parseInt(possibility);

        switch (possibilityInt) {
            case 1: {
                risk.setPossibility(RiskPossibility.HIGH);
                break;
            }

            case 2: {
                risk.setPossibility(RiskPossibility.MEDIUM);
                break;
            }

            case 3: {
                risk.setPossibility(RiskPossibility.LOW);
                break;
            }

            default: {
                throw new Exception();
            }

        }
    }
}
