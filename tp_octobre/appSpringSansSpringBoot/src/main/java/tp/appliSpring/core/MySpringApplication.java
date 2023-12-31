package tp.appliSpring.core;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "tp.appliSpring.core"})
//NB : Tous les sous packages de tp.appliSpring.core seront scrutés pour y découvrir
//@Component… et aussi pour y découvir d'autres classes avec @Configuration
public class MySpringApplication {

	public static void main(String[] args) {
		//System.setProperty("spring.profiles.active", "p1");
		AnnotationConfigApplicationContext springContext = new
		     AnnotationConfigApplicationContext(MySpringApplication.class) ;
		//...
		springContext.close();
	}

}
