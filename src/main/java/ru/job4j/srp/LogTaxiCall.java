package ru.job4j.srp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Помимо двух методов, вызывающих разнородные действия метод logCall определяет каким образом
 * он будет осуществлять логгирование, что накладывает дополнительную ответственность, а также
 * нарушает принцип закрытости к модификации - если потребуется изменить методы логгирования
 * придется переписывать код метода.
 */
public class LogTaxiCall implements Call {
    @Override
    public void callTaxi() {

    }

    @Override
    public void logCall(String info) {
        File file = new File("callLog.txt");
        try {
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.println(info);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
