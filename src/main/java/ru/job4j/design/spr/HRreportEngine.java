package ru.job4j.design.spr;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class HRreportEngine implements Report<String> {

    private Store store;

    public HRreportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        List<Employee> list = store.findBy(filter);
        Collections.sort(list, Comparator.comparing(Employee::getSalary).reversed());
        text.append("Name; Hired; Fired; Salary;").append(System.lineSeparator());
        for (Employee employee : list) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
