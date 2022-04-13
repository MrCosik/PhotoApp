package pl.learning.photoappapidiscoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class PhotoAppApiDiscoveryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotoAppApiDiscoveryServiceApplication.class, args);
    }

}
