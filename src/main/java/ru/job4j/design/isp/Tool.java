package ru.job4j.design.isp;

/**
 * Методы выбраны с нарушением принципа ISP так как агреггированы типы операций, которые многие
 * инструменты не поддерживают.
 */
public interface Tool {
    void cut();
    void dig();
    void saw();
    void screw();
}
