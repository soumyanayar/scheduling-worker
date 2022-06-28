package com.example.schedulingtasks;

import org.slf4j.Logger;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class LongTimeConsumingTask {

    public static void performTask(Logger log, SimpleDateFormat dateFormat) {
        log.info("Invoking ThreadId {}", Thread.currentThread());
        BigInteger veryBigNextPrime = new BigInteger(4000, new Random()).nextProbablePrime();
        log.info("Next Big Prime Number is generated from Task {} : The time is now {}", Thread.currentThread(), dateFormat.format(new Date()));
    }
}
