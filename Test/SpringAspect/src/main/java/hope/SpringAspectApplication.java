package hope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAspectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAspectApplication.class, args);
		System.out.println("helloworld!");
	}
}
