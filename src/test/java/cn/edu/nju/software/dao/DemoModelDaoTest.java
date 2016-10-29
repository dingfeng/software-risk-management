package cn.edu.nju.software.dao;

import cn.edu.nju.software.common.DaoTest;
import cn.edu.nju.software.entity.DemoModel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Java6Assertions.assertThat;


/**
 * @author 丁峰
 * @date 2016/10/29 21:35
 * @see DemoModelDaoTest
 */
public class DemoModelDaoTest  extends DaoTest {
    @Autowired
    private DemoModelDao demoModelDao;

    @Test
    public void testFindById()
    {
        entityManager.persist(new DemoModel(1l,"demo entity"));
        DemoModel demoModel = demoModelDao.findByValue("demo entity");
        assertThat(demoModel.getId()).isEqualTo(1l);
    }
}
