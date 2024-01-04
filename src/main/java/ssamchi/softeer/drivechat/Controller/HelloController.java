package ssamchi.softeer.drivechat.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController {
    @GetMapping("hello")
    public String hello() {
        return "Hello";
    }

    @GetMapping("health/check")
    public ResponseEntity<String> healthChecking() {
        return ResponseEntity.ok("OK");
    }
}
