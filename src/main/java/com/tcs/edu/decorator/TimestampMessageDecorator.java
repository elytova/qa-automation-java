package com.tcs.edu.decorator;

import java.time.Instant;

public class TimestampMessageDecorator {
    public static String currentTime = Instant.now().toString();
}
