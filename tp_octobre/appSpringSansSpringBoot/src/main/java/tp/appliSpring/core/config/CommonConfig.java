package tp.appliSpring.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@PropertySource("classpath:/application.properties")
@EnableAspectJAutoProxy
@EnableJpaRepositories(basePackages = {"tp.appliSpring.core.dao" })
//pour demander (sans spring boot) à gérer les implémentations des
//DAO/repository en fonction des interfaces héritant de CrudRepository
public class CommonConfig {

}
