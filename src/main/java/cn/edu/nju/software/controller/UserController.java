package cn.edu.nju.software.controller;

import cn.edu.nju.software.VO.ProjectVO;
import cn.edu.nju.software.VO.RiskVO;
import cn.edu.nju.software.entity.Project;
import cn.edu.nju.software.entity.Risk;
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
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zy118686 on 2016/11/7.
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping("/login")
    public String login(@RequestParam(value="userName", defaultValue="") String userName , @RequestParam(value="password", defaultValue="") String password , HttpSession httpSession) {

//        JSONObject jsonObject = new JSONObject();
//        try{
//            ResultDTO<User> userResultDTO  = userService.queryUserByName(userName);
//
//            if(!userResultDTO.isSuccess()){
//                if("no such User".equals(userResultDTO.getErrorMsg())){
//                    jsonObject.put("isSuccess", false);
//                    jsonObject.put("errMsg", "用户名不存在");
//                    return jsonObject.toJSONString();
//                }else{
//                    jsonObject.put("isSuccess", false);
//                    jsonObject.put("errMsg", "服务器异常");
//                    return jsonObject.toJSONString();
//                }
//            }
//
//            User user = userResultDTO.getData();
//
//            if(password.equals(user.getPassword())){
//                jsonObject.put("isSuccess", true);
//                jsonObject.put("data" , user );
//                httpSession.setAttribute("userId",user.getId());
//                httpSession.setAttribute("userName",user.getAccount());
//            }else{
//                jsonObject.put("isSuccess", false);
//                jsonObject.put("errMsg", "密码错误");
//            }
//
//            return jsonObject.toJSONString();
//
//
//
//        }catch(Exception e){
//            log.error("exception in user_login ", e);
//            jsonObject.put("isSuccess", false);
//            jsonObject.put("errMsg", "服务器异常");
//            return jsonObject.toJSONString();
//        }
        return null;
    }

    //这个update参数 还不确定
    @RequestMapping("update")
    public String update(@RequestParam(value="role", defaultValue="") String role , @RequestParam(value="username", defaultValue="") String username ,HttpSession httpSession ){

        JSONObject jsonObject = new JSONObject();
        try{

            Long userId = Long.parseLong((String)httpSession.getAttribute("userId"));

            ResultDTO<User> queryId = userService.queryUserByName(String.valueOf(userId));

            if(!queryId.isSuccess()){
                if(queryId.getErrorMsg() == null){
                    jsonObject.put("isSuccess", false);
                    jsonObject.put("errMsg", "您的权限不够");
                    return jsonObject.toJSONString();
                }else{
                    jsonObject.put("isSuccess", false);
                    jsonObject.put("errMsg", "服务器异常");
                    return jsonObject.toJSONString();
                }
            }

            if(!queryId.getData().getRole().equals(UserRole.SYSTEM_MANAGER)){
                jsonObject.put("isSuccess", false);
                jsonObject.put("errMsg", "您的权限不够");
                return jsonObject.toJSONString();
            }

            ResultDTO<User> query = userService.queryUserByName(username);

            if(!query.isSuccess()){
                if("no such User".equals(query.getErrorMsg())){
                    jsonObject.put("isSuccess", false);
                    jsonObject.put("errMsg", "用户名不存在");
                    return jsonObject.toJSONString();
                }else{
                    jsonObject.put("isSuccess", false);
                    jsonObject.put("errMsg", "服务器异常");
                    return jsonObject.toJSONString();
                }
            }

            User user = query.getData();

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

            ResultDTO<User> update = userService.saveorUpdateUser(user);

            if(update.isSuccess()){
                if(queryId.getErrorMsg() == null){
                    jsonObject.put("isSuccess", false);
                    jsonObject.put("errMsg", "用户不存在");
                    return jsonObject.toJSONString();
                }else{
                    jsonObject.put("isSuccess", false);
                    jsonObject.put("errMsg", "服务器异常");
                    return jsonObject.toJSONString();
                }
            }
            jsonObject.put("isSuccess", true);
            jsonObject.put("data", update.getData());
            return jsonObject.toJSONString();


        }catch (Exception e){
            log.error("exception in user_update " , e);
            jsonObject.put("isSuccess",false);
            jsonObject.put("errMsg","服务器异常");
            return jsonObject.toJSONString();
        }
    }

    @RequestMapping("/register")
    public String register(@RequestParam(value="username", defaultValue="") String userName , @RequestParam(value="password", defaultValue="") String password, @RequestParam(value="email", defaultValue="") String email ,  @RequestParam(value="role", defaultValue="") String role ){

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

    @RequestMapping("/getRisksCreate")
    public String getRisksCreate(HttpSession httpSession){

        JSONObject jsonObject = new JSONObject();

        try{
            Long userId = Long.parseLong((String)httpSession.getAttribute("userId"));

            ResultDTO<User> userResultDTO = userService.queryUserById(String.valueOf(userId));

            List<RiskVO> riskVOs = new ArrayList<>();

            DateFormat fmt =new
                    SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            if(!userResultDTO.isSuccess()){
                if(userResultDTO.getErrorMsg() == null){
                    jsonObject.put("isSuccess",false);
                    jsonObject.put("errMsg","用户不存在");
                    return jsonObject.toJSONString();
                }else {
                    jsonObject.put("isSuccess",false);
                    jsonObject.put("errMsg","服务器异常");
                    return jsonObject.toJSONString();
                }
            }

            for(Risk risk: userResultDTO.getData().getOwnRisks()){
                RiskVO riskVO = new RiskVO();
                riskVO.setId(risk.getId());
                riskVO.setUpdatedAt(fmt.format(risk.getUpdatedAt()));
                riskVO.setDescription(risk.getDescription());
                riskVO.setAuthor(risk.getAuthor().getAccount());
                riskVO.setCreatedAt(fmt.format(risk.getCreatedAt()));
                riskVO.setHandler(risk.getHandler().getAccount());
                riskVO.setProject(risk.getProject().getName());
                riskVO.setTrigger(risk.getTrigger());
                riskVO.setTitle(risk.getTitle());
                riskVO.setInfluence(risk.getInfluence().getDescription());
                riskVO.setPossibility(risk.getPossibility().getDescription());
                riskVO.setStatus(risk.getStatus().getDescription());
                riskVOs.add(riskVO);
            }

            jsonObject.put("isSuccess",true);
            jsonObject.put("data",riskVOs);
            return jsonObject.toJSONString();

        }catch (Exception e){
            log.error("exception in user_ getRisksCreate" , e);
            jsonObject.put("isSuccess",false);
            jsonObject.put("errMsg","服务器异常");
            return jsonObject.toJSONString();
        }
    }

    @RequestMapping("/getRisksHandler")
    public String getRisksHandler(HttpSession httpSession){

        JSONObject jsonObject = new JSONObject();

        try{
            Long userId = Long.parseLong((String)httpSession.getAttribute("userId"));

            ResultDTO<User> userResultDTO = userService.queryUserById(String.valueOf(userId));

            List<RiskVO> riskVOs = new ArrayList<>();

            DateFormat fmt =new
                    SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            if(!userResultDTO.isSuccess()){
                if(userResultDTO.getErrorMsg() == null){
                    jsonObject.put("isSuccess",false);
                    jsonObject.put("errMsg","用户不存在");
                    return jsonObject.toJSONString();
                }else {
                    jsonObject.put("isSuccess",false);
                    jsonObject.put("errMsg","服务器异常");
                    return jsonObject.toJSONString();
                }
            }

            for(Risk risk: userResultDTO.getData().getHandleRisks()){
                RiskVO riskVO = new RiskVO();
                riskVO.setId(risk.getId());
                riskVO.setUpdatedAt(fmt.format(risk.getUpdatedAt()));
                riskVO.setDescription(risk.getDescription());
                riskVO.setAuthor(risk.getAuthor().getAccount());
                riskVO.setCreatedAt(fmt.format(risk.getCreatedAt()));
                riskVO.setHandler(risk.getHandler().getAccount());
                riskVO.setProject(risk.getProject().getName());
                riskVO.setTrigger(risk.getTrigger());
                riskVO.setTitle(risk.getTitle());
                riskVO.setInfluence(risk.getInfluence().getDescription());
                riskVO.setPossibility(risk.getPossibility().getDescription());
                riskVO.setStatus(risk.getStatus().getDescription());
                riskVOs.add(riskVO);
            }

            jsonObject.put("isSuccess",true);
            jsonObject.put("data",riskVOs);
            return jsonObject.toJSONString();

        }catch (Exception e){
            log.error("exception in user_ getRisksHandler" , e);
            jsonObject.put("isSuccess",false);
            jsonObject.put("errMsg","服务器异常");
            return jsonObject.toJSONString();
        }
    }

    @RequestMapping("/getProjectCreate")
    public String getProjectCreate(HttpSession httpSession){

        JSONObject jsonObject = new JSONObject();

        try{
            Long userId = Long.parseLong((String)httpSession.getAttribute("userId"));

            ResultDTO<User> userResultDTO = userService.queryUserById(String.valueOf(userId));

            List<ProjectVO> projectVOs = new ArrayList<>();

            DateFormat fmt =new
                    SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            if(!userResultDTO.isSuccess()){
                if(userResultDTO.getErrorMsg() == null){
                    jsonObject.put("isSuccess",false);
                    jsonObject.put("errMsg","用户不存在");
                    return jsonObject.toJSONString();
                }else {
                    jsonObject.put("isSuccess",false);
                    jsonObject.put("errMsg","服务器异常");
                    return jsonObject.toJSONString();
                }
            }

            for(Project project : userResultDTO.getData().getOwnProjects()){
                 ProjectVO projectVO = new ProjectVO();
                 projectVO.setId(project.getId());
                 projectVO.setUpdatedAt(fmt.format(project.getUpdatedAt()));
                 projectVO.setCreatedAt(fmt.format(project.getCreatedAt()));
                 projectVO.setCreatedBy((String)httpSession.getAttribute("userName"));
                 projectVO.setDescription(project.getDescription());
                 projectVO.setName(project.getName());
                 projectVOs.add(projectVO);
            }

            jsonObject.put("isSuccess",true);
            jsonObject.put("data",projectVOs);
            return jsonObject.toJSONString();

        }catch (Exception e){
            log.error("exception in user_ getProjectCreate" , e);
            jsonObject.put("isSuccess",false);
            jsonObject.put("errMsg","服务器异常");
            return jsonObject.toJSONString();
        }
    }


    @RequestMapping("/getProjectJoin")
    public String getProjectJoin(HttpSession httpSession){

        JSONObject jsonObject = new JSONObject();

        try{
            Long userId = Long.parseLong((String)httpSession.getAttribute("userId"));

            ResultDTO<User> userResultDTO = userService.queryUserById(String.valueOf(userId));

            List<ProjectVO> projectVOs = new ArrayList<>();

            DateFormat fmt =new
                    SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            if(!userResultDTO.isSuccess()){
                if(userResultDTO.getErrorMsg() == null){
                    jsonObject.put("isSuccess",false);
                    jsonObject.put("errMsg","用户不存在");
                    return jsonObject.toJSONString();
                }else {
                    jsonObject.put("isSuccess",false);
                    jsonObject.put("errMsg","服务器异常");
                    return jsonObject.toJSONString();
                }
            }

            for(Project project : userResultDTO.getData().getOwnProjects()){
                ProjectVO projectVO = new ProjectVO();
                projectVO.setId(project.getId());
                projectVO.setUpdatedAt(fmt.format(project.getUpdatedAt()));
                projectVO.setCreatedAt(fmt.format(project.getCreatedAt()));
                projectVO.setCreatedBy(project.getAuthor().getAccount());
                projectVO.setDescription(project.getDescription());
                projectVO.setName(project.getName());
                projectVOs.add(projectVO);
            }

            jsonObject.put("isSuccess",true);
            jsonObject.put("data",projectVOs);
            return jsonObject.toJSONString();

        }catch (Exception e){
            log.error("exception in user_ getProjectJoin" , e);
            jsonObject.put("isSuccess",false);
            jsonObject.put("errMsg","服务器异常");
            return jsonObject.toJSONString();
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession httpSession){

        JSONObject jsonObject = new JSONObject();

        try{
            httpSession.removeAttribute("userId");
            httpSession.removeAttribute("userName");
            jsonObject.put("isSuccess",true);
            return jsonObject.toJSONString();
        }catch (Exception e){
            log.error("exception in user_logout " , e);
            jsonObject.put("isSuccess",false);
            jsonObject.put("errMsg","服务器异常");
            return jsonObject.toJSONString();
        }
    }
}
