package ru.job4j.design.lsp;

/**
 * В классе наследнике опущена проверка для базового параметра.
 */
public class Worker3 {
    public double salary;
    public Worker3(double salary) {
        if (salary > 0) {
            this.salary = salary;
        }
    }
    public Worker3() { }

    class Executive3 extends Worker3 {
        public Executive3(double salary) {
            this.salary = salary;
        }
    }
}
