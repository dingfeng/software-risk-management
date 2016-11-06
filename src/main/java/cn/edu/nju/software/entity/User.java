package cn.edu.nju.software.entity;

import cn.edu.nju.software.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private String account; //账号
    private String password; //密码
    private String email;  //邮箱
    @Enumerated(EnumType.ORDINAL)
    private UserRole role;  //角色
    private List<Project> join_projects;//参与的项目
    private List<Project> own_projects; //创建的项目
    private List<Risk>  handle_risks; //处理的风险条目
    private List<Risk>  own_risks; //创建的风险条目
}
