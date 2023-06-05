package top.hueng.apm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ServerMonitorTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerMonitorTestApplication.class, args);
    }

}
