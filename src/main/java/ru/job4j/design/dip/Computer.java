package ru.job4j.design.dip;

/**
 * Нарушение принципа DIP заключается в жесткой связке двух классов. Подключать надо через
 * интерфейс или абстрактный класс
 */
public class Computer {
    LCDMonitor monitor = new LCDMonitor();
}

class LCDMonitor {

}
