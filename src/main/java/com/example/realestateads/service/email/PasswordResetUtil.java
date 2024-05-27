package com.example.realestateads.service.email;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PasswordResetUtil {

    public String generateResetCode() {
        Random random = new Random();

        int min = 100000;
        int max = 999999;
        int resetCode = random.nextInt(max - min + 1) + min;

        return String.valueOf(resetCode);
    }
}
