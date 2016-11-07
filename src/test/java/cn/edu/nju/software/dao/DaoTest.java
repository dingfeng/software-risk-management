package cn.edu.nju.software.dao;

import cn.edu.nju.software.common.CommonTest;
import cn.edu.nju.software.entity.Project;
import cn.edu.nju.software.entity.Risk;
import cn.edu.nju.software.entity.User;
import cn.edu.nju.software.enums.RiskInfluence;
import cn.edu.nju.software.enums.RiskPossibility;
import cn.edu.nju.software.enums.RiskStatus;
import cn.edu.nju.software.enums.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 丁峰
 * @date 2016/10/29 21:51
 * @see DaoTest
 */
@DataJpaTest
@Slf4j
public class DaoTest extends CommonTest{
    @Autowired
    private UserDao userDao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private RiskDao riskDao;
    private List<User> userList = new ArrayList<>();
    private List<Risk> riskList = new ArrayList<>();
    private List<Project> projectList = new ArrayList<>();
    public User getOneUser()
    {
        User user = new User();
        user.setAccount("usertest");
        user.setPassword("password");
        user.setRole(UserRole.SYSTEM_MANAGER);
        return user;
    }

    public Project getProject(User user)
    {
        Project project = new Project();
        project.setCreatedAt(new Date());
        project.setUpdatedAt(new Date());
        project.setName("test project");
        project.setDescription("It is a test project");
        project.setAuthor(user);
        return project;
    }

    public Risk getOneRisk(Project project,User author, User handler)
    {
        Risk risk = new Risk();
        risk.setAuthor(author);
        risk.setCreatedAt(new Date());
        risk.setUpdatedAt(new Date());
        risk.setHandler(handler);
        risk.setDescription("It is a risk");
        risk.setInfluence(RiskInfluence.HIGH);
        risk.setProject(project);
        risk.setPossibility(RiskPossibility.HIGH);
        risk.setStatus(RiskStatus.DOING);
        risk.setTitle("first risk");
        risk.setTrigger("a trigger");
        return risk;
    }

    private void showAll()
    {
        List<Risk> allRisks=riskDao.findAll();
        log.info("risk={}",allRisks.toString());
        List<User> allUsers = userDao.findAll();
        log.info("user={} ",allUsers.toString());
        List<Project> projects = projectDao.findAll();
        log.info("project={}",projects.toString());
    }

    @Before
    public void setUp()
    {
      //初始化操作
        //初始化用户数据
        String[] userAcounts = new String[5];
        for(int i=0;i<userAcounts.length;++i)
        {
            User user = getOneUser();
            user.setRole(UserRole.SYSTEM_MANAGER);
            user.setAccount("account"+(i+1));
            userList.add(userDao.saveAndFlush(user));
        }
        //初始化软件项目数据
        for(int i=0;i<5;++i)
        {
            Project project = getProject(userList.get(i));
            project.setName("project"+i);
            projectList.add(projectDao.saveAndFlush(project));
            //初始化风险条目
            for(int j=0; j<6; ++j)
            {
                Risk risk = getOneRisk(project,userList.get(i),userList.get((i+1)%userList.size()));
                risk.setTitle("title"+i);
                riskList.add(riskDao.saveAndFlush(risk));
            }
        }
        showAll();
    }

    @After
    public void setDown()
    {
        showAll();
    }

    @Test
    public void test()
    {
        List<User> userList = userDao.findAll();
        User user  = userList.get(0);
        log.error("project size ={}",user.getOwnProjects().size());
    }

}
