package cn.edu.nju.software.common;

import cn.edu.nju.software.TestApplication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 丁峰
 * @date 2016/10/29 21:51
 * @see DaoTest
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= TestApplication.class)
//@ActiveProfiles("scratch")
@DataJpaTest
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class DaoTest {
    @Autowired
    protected TestEntityManager entityManager;
}
