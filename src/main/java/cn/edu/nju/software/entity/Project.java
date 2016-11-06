package cn.edu.nju.software.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * @author 丁峰
 * @date 2016/11/6 12:10
 * @see Project
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="project")
public class Project {
    private Long id;  //主键
    private String name; //项目名称
    private String description;  //描述
    private Date created_at;  //创建时间
    private Date updated_at; //更新时间
    private User author; //创建者
    private List<User> collaborators;//协作者
    private List<Risk> risks;
}
