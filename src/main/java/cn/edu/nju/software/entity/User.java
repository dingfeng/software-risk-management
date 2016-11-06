package cn.edu.nju.software.entity;

import cn.edu.nju.software.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * @author 丁峰
 * @date 2016/11/6 11:46
 * @see User
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;     //主键

    @Column(nullable = false)
    private String account; //账号

    @Column(nullable = false)
    private String password; //密码

    private String email;  //邮箱

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private UserRole role;  //角色

    @ManyToMany(targetEntity = Project.class,mappedBy = "collaborators")
    @Cascade(value= CascadeType.ALL)
    private List<Project> join_projects;//参与的项目

    @OneToMany(mappedBy = "author")
    @Cascade(value= CascadeType.ALL)
    private List<Project> own_projects; //创建的项目

    @OneToMany(mappedBy = "handler")
    @Cascade(value= CascadeType.ALL)
    private List<Risk>  handle_risks; //处理的风险条目

    @OneToMany(mappedBy = "author")
    @Cascade(value= CascadeType.ALL)
    private List<Risk>  own_risks; //创建的风险条目
}
