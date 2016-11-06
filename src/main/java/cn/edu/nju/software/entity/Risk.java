package cn.edu.nju.software.entity;

import cn.edu.nju.software.enums.RiskInfluence;
import cn.edu.nju.software.enums.RiskPossibility;
import cn.edu.nju.software.enums.RiskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author 丁峰
 * @date 2016/11/6 11:52
 * @see Risk
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="risk")
public class Risk {
    private Long id;  //主键
    @Enumerated(EnumType.ORDINAL)
    private RiskStatus status; //状态
    private String title;   //标题
    @Enumerated(EnumType.ORDINAL)
    private RiskPossibility possibility; //可能性
    @Enumerated(EnumType.ORDINAL)
    private RiskInfluence influence;  //影响
    private String trigger; //触发器
    private String description; //文本描述
    private User author; //创建者
    private User handler; //处理者
    private Project project ;// 所属项目
    private Date created_at; //创建时间
    private Date updated_at; //更新时间
}
