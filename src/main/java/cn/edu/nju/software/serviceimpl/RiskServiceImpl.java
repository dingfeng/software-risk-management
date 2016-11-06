package cn.edu.nju.software.serviceimpl;

import cn.edu.nju.software.entity.Risk;
import cn.edu.nju.software.service.RiskService;
import cn.edu.nju.software.util.ResultDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zy118686 on 2016/11/6.
 */
@Service
public class RiskServiceImpl implements RiskService{
    @Override
    public ResultDTO<Risk> saveRisk(Risk risk) {
        return null;
    }

    @Override
    public ResultDTO<Risk> updateRisk(Risk risk) {
        return null;
    }

    @Override
    public ResultDTO<Risk> deleteRisk(Risk risk) {
        return null;
    }

    @Override
    public ResultDTO<List<Risk>> queryRisks() {
        return null;
    }
}
