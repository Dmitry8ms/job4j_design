package ru.job4j.design.srp;

import java.util.function.Predicate;

public class AccountingReportEngine implements Report<String> {

    private Store store;
    public static final double RATE = 83.4;

    public AccountingReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;").append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            String salaryFormated = String.format("%.2f", employee.getSalary() / RATE) + "USD";
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(salaryFormated).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
