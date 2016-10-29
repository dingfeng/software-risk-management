package cn.edu.nju.software;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author 丁峰
 * @date 2016/10/23 23:01
 * @see Application
 */


@SpringBootApplication
//@ComponentScan(basePackages = {
//        "cn.edu.nju.software",
//})
//@EnableJpaRepositories("cn.edu.nju.software.dao")
//@EntityScan("cn.edu.nju.software.entity")
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
}