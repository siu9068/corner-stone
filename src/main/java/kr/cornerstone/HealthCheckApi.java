package kr.cornerstone;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HealthCheckApi {

    @GetMapping("/health")
    public ResponseEntity<String> heathCheck(){
        return ResponseEntity.ok("OK");
    }
}
