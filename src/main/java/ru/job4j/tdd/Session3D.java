package ru.job4j.tdd;

import java.util.Calendar;

public class Session3D implements Session {
    @Override
    public boolean setTime(Calendar time) {
        return true;
    }

    @Override
    public Calendar getTime() {
        return null;
    }
}
