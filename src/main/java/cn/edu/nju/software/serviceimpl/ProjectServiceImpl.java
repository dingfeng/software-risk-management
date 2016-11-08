package cn.edu.nju.software.serviceimpl;

import cn.edu.nju.software.dao.ProjectDao;
import cn.edu.nju.software.entity.Project;
import cn.edu.nju.software.service.ProjectService;
import cn.edu.nju.software.util.ResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zy118686 on 2016/11/6.
 */
@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService{

    @Resource
    ProjectDao projectDao;

    @Override
    public ResultDTO<Project> saveorUpdateProject(Project project) {

        ResultDTO<Project> resultDTO = new ResultDTO<Project>();

        try {
            Project pj = projectDao.save(project);
            if(pj == null){
                resultDTO.setSuccess(false);
                resultDTO.setData(null);
                resultDTO.setErrorMsg("Unknown error project is null ");
                log.error("Unknown error project is null");
                return resultDTO;
            }
            resultDTO.setData(pj);
            resultDTO.setSuccess(true);
        }catch(Exception e){
            log.error("error in save project ",e);
            resultDTO.setErrorMsg(e.getMessage());
            resultDTO.setSuccess(false);
            resultDTO.setData(null);
        }
        return resultDTO;
    }

    @Override
    public ResultDTO<Project> deleteProject(Project project) {

        ResultDTO<Project> resultDTO = new ResultDTO<Project>();

        try {
            projectDao.delete(project);
            resultDTO.setData(null);
            resultDTO.setSuccess(true);
        }catch(Exception e){
            log.error("error in delete project ",e);
            resultDTO.setErrorMsg(e.getMessage());
            resultDTO.setSuccess(false);
            resultDTO.setData(null);
        }
        return resultDTO;

    }

    @Override
    public ResultDTO<Project> queryProjectById(String Id) {
        ResultDTO<Project> resultDTO = new ResultDTO<Project>();

        try {
            Project p =projectDao.findOne(Long.parseLong(Id));
            if(p==null){
                log.warn("no such project the Id is "+Id);
                resultDTO.setSuccess(false);
                resultDTO.setData(p);
                resultDTO.setErrorMsg(null);
                return resultDTO;
            }
            resultDTO.setData(p);
            resultDTO.setSuccess(true);
        }catch(Exception e){
            log.error("error in query project by Id the Id is "+Id,e);
            resultDTO.setErrorMsg(e.getMessage());
            resultDTO.setSuccess(false);
            resultDTO.setData(null);
        }
        return resultDTO;
    }

    @Override
    public ResultDTO<List<Project>> queryProject() {
        return null;
    }
}
