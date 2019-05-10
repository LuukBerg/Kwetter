package fontys.com.kwetterdoa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "fontys.com.kwetterdoa.Repository")
@SpringBootApplication
public class KwetterDoaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KwetterDoaApplication.class, args);
    }


}
