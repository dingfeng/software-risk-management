package cn.edu.nju.software.service;

import cn.edu.nju.software.entity.Project;
import cn.edu.nju.software.util.ResultDTO;

import java.util.List;

/**
 * Created by zy118686 on 2016/11/6.
 */
public interface ProjectService {

    //update的时候，传入的Project请务必有Id
    public ResultDTO<Project>  saveorUpdateProject(Project project);

    public ResultDTO<Project> deleteProject(Project project);

    public ResultDTO<Project> queryProjectById(String Id);

    //查询接口的参数有待商榷,可能要开多个接口
    public ResultDTO<List<Project>> queryProject();
}
