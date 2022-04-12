package ru.job4j.design.lsp;

/**
 * при переопределении calcBonus в Executive2 ослабляется постусловие
 */
public class Worker2 {
    public int k;
    public double salary;
    public double bonus;

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double calcBonus(int k, double salary) {
        double bonus = 50000.0;
        if (k * salary < 50000) {
            bonus = k * salary;
        }
        this.bonus = bonus;
        return bonus;
    }

    class Executive2 extends Worker2 {
        @Override
        public double calcBonus(int k, double salary) {
            return k * salary;
        }
    }
}
