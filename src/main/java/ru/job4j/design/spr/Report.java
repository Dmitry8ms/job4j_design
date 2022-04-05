package ru.job4j.design.spr;

import java.util.Comparator;
import java.util.function.Predicate;

public interface Report<T> {
    T generate(Predicate<Employee> filter);
}
