package es.us.idea.dmn4dq.bpm;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableProcessApplication
@SpringBootApplication
public class WebappBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebappBootApplication.class, args);
    }
}
