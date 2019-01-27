package com.pahlsoft.example.elasticapm.hello;

import java.util.concurrent.ThreadLocalRandom;

public class WastedTime {
    public static void randomTimeWaster() throws Exception {
        Thread.sleep(getRandomNumber());
    }

    private static int getRandomNumber() throws Exception {
        return ThreadLocalRandom.current().nextInt(1, 1000 + 1);
    }
}
