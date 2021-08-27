package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte by = 100;
        short s = 1000;
        char ch = 36;
        int i = 33;
        float f = .9f;
        double d = 2.2;
        long l = 100000L;
        boolean b = true;

        LOG.debug("by : {}, s : {}, ch : {}, i : {}, f : {}, d : {}, l : {}, b : {}", by,
                    s, ch, i, f, d, l, b);
    }
}
