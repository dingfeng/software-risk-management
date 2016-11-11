package cn.edu.nju.software.VO;

import cn.edu.nju.software.entity.User;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

/**
 * Created by zy118686 on 16/11/11.
 */
public class ProjectVO {

    private Long id;  //主键

    private String name; //项目名称

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    private String description;  //描述

    private String createdAt;  //创建时间

    private String updatedAt; //更新时间

    private String createdBy; //创建者

    private List<RiskVO> riskVOs ;

    public List<String> joiners;

    public List<String> getJoiners() {
        return joiners;
    }

    public void setJoiners(List<String> joiners) {
        this.joiners = joiners;
    }

    public List<RiskVO> getRiskVOs() {
        return riskVOs;
    }


    public void setRiskVOs(List<RiskVO> riskVOs) {
        this.riskVOs = riskVOs;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
