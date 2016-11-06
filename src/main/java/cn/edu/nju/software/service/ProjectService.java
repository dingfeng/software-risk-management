package cn.edu.nju.software.service;

import cn.edu.nju.software.entity.Project;
import cn.edu.nju.software.util.ResultDTO;

import java.util.List;

/**
 * Created by zy118686 on 2016/11/6.
 */
public interface ProjectService {

    public ResultDTO<Project> saveProject(Project project);

    public ResultDTO<Project> updateProject(Project project);

    public ResultDTO<Project> deleteProject(Project project);

    //查询接口的参数有待商榷,可能要开多个接口
    public ResultDTO<List<Project>> queryProject();
}
