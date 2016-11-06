package cn.edu.nju.software.entity;

import cn.edu.nju.software.enums.RiskInfluence;
import cn.edu.nju.software.enums.RiskPossibility;
import cn.edu.nju.software.enums.RiskStatus;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author 丁峰
 * @date 2016/11/6 11:52
 * @see Risk
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="risk")
public class Risk {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;  //主键

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private RiskStatus status; //状态

    @Column(nullable = false)
    private String title;   //标题

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private RiskPossibility possibility; //可能性

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private RiskInfluence influence;  //影响

    @Column(name="risk_trigger")
    private String trigger; //触发器

    private String description; //文本描述

    @ManyToOne
    @JoinColumn(name="author_id")
    private User author; //创建者

    @ManyToOne
    @JoinColumn(name="handler_id")
    private User handler; //处理者

    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project ;// 所属项目

    private Date created_at; //创建时间

    private Date updated_at; //更新时间
}
