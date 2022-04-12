package ru.job4j.design.lsp;

/**
 * Усиление предусловий в Executive1
 */
public class Worker1 {
    public double salary;

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

}

class Executive1 extends Worker1 {
    @Override
    public double getSalary() {
        if (salary > 1000000) {
            throw new IllegalArgumentException("Информация является приватной");
        }
        return salary;
    }
}
