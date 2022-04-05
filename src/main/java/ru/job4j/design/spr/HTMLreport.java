package ru.job4j.design.spr;

import java.util.Objects;

/**
 * Предполагаем что в реальной жизни метод getHTMLcontent() вернет строку подходящую для записи
 * в HTML файл.
 */
public class HTMLreport {
    String content;
    public HTMLreport(String content) {
        this.content = content;
    }
    public String getHTMLContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HTMLreport that = (HTMLreport) o;
        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}
