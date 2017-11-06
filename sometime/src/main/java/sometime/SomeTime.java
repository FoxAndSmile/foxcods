package sometime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SomeTime {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SomeTime.class, args);
    }
}
