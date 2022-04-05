package ru.job4j.design.spr;

import java.util.function.Predicate;

public class HTMLreportEngine implements Report<HTMLreport> {
    Store store;
    public HTMLreportEngine(Store store) {
        this.store = store;
    }

    @Override
    public HTMLreport generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;").append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return new HTMLreport(text.toString());
    }
}
