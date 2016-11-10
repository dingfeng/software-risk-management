package cn.edu.nju.software.controller;

import cn.edu.nju.software.entity.Project;
import cn.edu.nju.software.entity.User;
import cn.edu.nju.software.service.ProjectService;
import cn.edu.nju.software.service.UserService;
import cn.edu.nju.software.util.ResultDTO;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by zy118686 on 2016/11/7.
 */
@RestController
@RequestMapping("/project")
@Slf4j
public class ProjectController {

    @Resource
    ProjectService projectService;


    @Resource
    UserService userService ;


    @RequestMapping("/createProject")
    public String createProject(@RequestParam(value="projectName", defaultValue="") String projectName , @RequestParam(value="description", defaultValue="") String description, HttpSession httpSession) {

        JSONObject jsonObject = new JSONObject();

        try {

            Long userId = Long.parseLong((String)httpSession.getAttribute("userId"));

            Project project = new Project();

            project.setName(projectName);

            project.setDescription(description);

            User user = new User();

            user.setId(userId);

            project.setAuthor(user);

            project.setCreatedAt(new Date());

            project.setUpdatedAt(new Date());

            ResultDTO<Project> projectResultDTO = new ResultDTO<>();

            projectResultDTO = projectService.saveorUpdateProject(project);

            if(!projectResultDTO.isSuccess()){
                jsonObject.put("isSuccess", false);
                jsonObject.put("errMsg", "服务器异常");
                return jsonObject.toJSONString();
            }

            jsonObject.put("isSuccess", true);
            jsonObject.put("data",project);
            return jsonObject.toJSONString();

        }catch (Exception e){
            log.error("Exception in project_createProject ",e);
            jsonObject.put("isSuccess", false);
            jsonObject.put("errMsg", "服务器异常");
            return jsonObject.toJSONString();
        }
    }

    @RequestMapping("/updateProject")
    public String updateProject(@RequestParam(value="projectName", defaultValue="") String projectName , @RequestParam(value="description", defaultValue="") String description , @RequestParam(value="projectId", defaultValue="") String projectId) {

        JSONObject jsonObject = new JSONObject();

        try{
            String queryJsonString = queryProjectById(projectId);

            JSONObject jsonObject1 = JSONObject.parseObject(queryJsonString);

            if(jsonObject1.get("isSuccess").equals(true) || jsonObject1.get("isSuccess").equals("true")){

                Project project = (Project)jsonObject1.get("data");

                project.setDescription(description);

                project.setName(projectName);

                project.setUpdatedAt(new Date());

                ResultDTO<Project> projectResultDTO  = projectService.saveorUpdateProject(project);

                if(!projectResultDTO.isSuccess()){
                    jsonObject.put("isSuccess", false);
                    jsonObject.put("errMsg", "服务器异常");
                    return jsonObject.toJSONString();
                }

                jsonObject.put("isSuccess", true);
                jsonObject.put("data", project);
                return jsonObject.toJSONString();

            }else{
                return queryJsonString;
            }



        }catch (Exception e ){
            log.error("Exception in project_createProject ",e);
            jsonObject.put("isSuccess", false);
            jsonObject.put("errMsg", "服务器异常");
            return jsonObject.toJSONString();
        }
    }


    @RequestMapping("/invite")
    public String invite(@RequestParam(value="projectName", defaultValue="") String projectName , @RequestParam(value="userId", defaultValue="") String userId) {

        JSONObject jsonObject = new JSONObject();

        try {

            ResultDTO<Project> queryResultDTO = new ResultDTO<>();

            if(!queryResultDTO.isSuccess()){
                if(queryResultDTO.getErrorMsg() == null ){
                    jsonObject.put("isSuccess", false);
                    jsonObject.put("errMsg", "项目不存在");
                    return jsonObject.toJSONString();
                }else{
                    jsonObject.put("isSuccess", false);
                    jsonObject.put("errMsg", "服务器异常");
                    return jsonObject.toJSONString();
                }
            }

            User user = new User();

            user.setId(Long.parseLong(userId));

            Project project = queryResultDTO.getData();

            List<User> users = project.getCollaborators();

            users.add(user);

            ResultDTO<Project> projectResultDTO = new ResultDTO<>();

            projectResultDTO = projectService.saveorUpdateProject(project);

            if(!projectResultDTO.isSuccess()){
                jsonObject.put("isSuccess", false);
                jsonObject.put("errMsg", "服务器异常");
                return jsonObject.toJSONString();
            }

            jsonObject.put("isSuccess", true);
            jsonObject.put("data",project);
            return jsonObject.toJSONString();

        }catch (Exception e){
            log.error("Exception in project_createProject ",e);
            jsonObject.put("isSuccess", false);
            jsonObject.put("errMsg", "服务器异常");
            return jsonObject.toJSONString();
        }
    }

    private String queryProjectById(String projectId ){

        JSONObject jsonObject = new JSONObject();

        try{
            ResultDTO<Project> queryResultDTO = new ResultDTO<>();

            queryResultDTO = projectService.queryProjectById(projectId);

            if(!queryResultDTO.isSuccess()){
                if(queryResultDTO.getErrorMsg() == null ){
                    jsonObject.put("isSuccess", false);
                    jsonObject.put("errMsg", "项目不存在");
                    return jsonObject.toJSONString();
                }else{
                    jsonObject.put("isSuccess", false);
                    jsonObject.put("errMsg", "服务器异常");
                    return jsonObject.toJSONString();
                }
            }

            jsonObject.put("isSuccess",true);
            jsonObject.put("data",queryResultDTO.getData());
            return jsonObject.toJSONString();
        }catch(Exception e){
            log.error("exception in project_queryProjectById",e);
            jsonObject.put("isSuccess", false);
            jsonObject.put("errMsg", "服务器异常");
            return jsonObject.toJSONString();
        }

    }



}
