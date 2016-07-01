package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.UUID;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableResourceServer
public class ResourceApplication {

    @RequestMapping("/")
    public Message home(Principal principal) {
        return new Message("Hello World " + principal.getName());
    }

    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication.class, args);
    }

}

class Message {
    private String id = UUID.randomUUID().toString();
    private String content;

    Message() {
    }

    public Message(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
