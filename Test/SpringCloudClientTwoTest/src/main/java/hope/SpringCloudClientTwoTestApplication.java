package hope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudClientTwoTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudClientTwoTestApplication.class, args);
	}
}
