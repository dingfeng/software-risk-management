package cn.edu.nju.software.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author 丁峰
 * @date 2016/11/6 12:10
 * @see Project
 */
@Data
@EqualsAndHashCode(exclude = {"collaborators","risks"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = {"collaborators","risks"})
@Table(name="project")
public class Project {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;  //主键

    @Column(nullable = false)
    private String name; //项目名称

    private String description;  //描述

    private Date created_at;  //创建时间

    private Date updated_at; //更新时间

    @ManyToOne
    @JoinColumn(name="author_id")
    private User author; //创建者

    @ManyToMany(targetEntity = User.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<User> collaborators;//协作者

    @OneToMany(mappedBy = "project")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Risk> risks;
}
