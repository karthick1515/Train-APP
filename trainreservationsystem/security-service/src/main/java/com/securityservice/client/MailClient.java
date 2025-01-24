package com.securityservice.client;

import com.securityservice.exception.CustomErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "MAIL-SERVICE",url = "http://localhost:9095")
public interface MailClient {
    @PostMapping("/api/sendmail")
    public String sendEmail(@RequestParam String toEmail);
}
