package cn.edu.nju.software.serviceimpl;

import cn.edu.nju.software.dao.RiskDao;
import cn.edu.nju.software.entity.Risk;
import cn.edu.nju.software.service.RiskService;
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
public class RiskServiceImpl implements RiskService{


    @Resource
    RiskDao riskDao ;

    @Override
    public ResultDTO<Risk> saveorUpdateRisk(Risk risk) {
        ResultDTO<Risk> resultDTO = new ResultDTO<Risk>();

        try {
            Risk r = riskDao.save(risk);
            if(r == null){
                resultDTO.setSuccess(false);
                resultDTO.setData(null);
                resultDTO.setErrorMsg("Unknown error risk is null ");
                log.error("Unknown error risk is null");
                return resultDTO;
            }
            resultDTO.setData(r);
            resultDTO.setSuccess(true);
        }catch(Exception e){
            log.error("error in save or update risk ",e);
            resultDTO.setErrorMsg(e.getMessage());
            resultDTO.setSuccess(false);
            resultDTO.setData(null);
        }
        return resultDTO;
    }

    @Override
    public ResultDTO<Risk> deleteRisk(Risk risk) {
        ResultDTO<Risk> resultDTO = new ResultDTO<Risk>();
        try {
            riskDao.delete(risk);
            resultDTO.setData(null);
            resultDTO.setSuccess(true);
        }catch(Exception e){
            log.error("error in delete risk ",e);
            resultDTO.setErrorMsg(e.getMessage());
            resultDTO.setSuccess(false);
            resultDTO.setData(null);
        }
        return resultDTO;
    }

    @Override
    public ResultDTO<Risk> queryRiskById(String Id) {
        ResultDTO<Risk> resultDTO = new ResultDTO<Risk>();

        try {
            Risk r =riskDao.findOne(Long.parseLong(Id));
            if(r==null){
                log.warn("no such Risk the Id is "+Id);
                resultDTO.setSuccess(false);
                resultDTO.setData(r);
                resultDTO.setErrorMsg(null);
                return resultDTO;
            }
            resultDTO.setData(r);
            resultDTO.setSuccess(true);
        }catch(Exception e){
            log.error("error in query Risk by Id the Id is "+Id,e);
            resultDTO.setErrorMsg(e.getMessage());
            resultDTO.setSuccess(false);
            resultDTO.setData(null);
        }
        return resultDTO;
    }


    @Override
    public ResultDTO<List<Risk>> queryRisks() {
        return null;
    }
}
