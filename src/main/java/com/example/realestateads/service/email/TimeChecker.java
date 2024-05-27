package com.example.realestateads.service.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class TimeChecker {

    @Value("${mail.code.time.valid}")
    private Integer validTime;

    public boolean checkTime(Timestamp storedTime) {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        long interval = currentTime.getTime() - storedTime.getTime();

        return interval < (validTime * 60 * 1000);
    }
}