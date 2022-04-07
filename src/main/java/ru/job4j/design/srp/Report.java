package ru.job4j.design.srp;

import java.util.function.Predicate;

public interface Report<T> {
    T generate(Predicate<Employee> filter);
}
