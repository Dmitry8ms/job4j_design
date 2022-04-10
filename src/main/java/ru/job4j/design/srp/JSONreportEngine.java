package ru.job4j.design.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class JSONreportEngine implements Report<String> {

    private Store store;

    public JSONreportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate filter) {
        List<Employee> employees = store.findBy(filter);
        Gson gson = new GsonBuilder().create();
        return gson.toJson(employees);
    }

    public static void main(String[] args) {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        store.add(worker1);
        var jsonReport = new JSONreportEngine(store);
        System.out.println(jsonReport.generate(e -> true));
    }
}
