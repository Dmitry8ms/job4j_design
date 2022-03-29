package ru.job4j.tdd;

import java.util.Calendar;

public interface Session {

    public boolean setTime(Calendar time);

    public Calendar getTime();
}
