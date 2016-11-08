package cn.edu.nju.software.service;

import cn.edu.nju.software.entity.Risk;
import cn.edu.nju.software.util.ResultDTO;

import java.util.List;

/**
 * Created by zy118686 on 2016/11/6.
 */
public interface RiskService {

    public ResultDTO<Risk> saveorUpdateRisk(Risk risk);

    public ResultDTO<Risk> deleteRisk(Risk risk);

    public ResultDTO<Risk> queryRiskById(String Id);

    //查询接口的参数有待商榷,可能要开多个接口
    public ResultDTO<List<Risk>> queryRisks();
}
