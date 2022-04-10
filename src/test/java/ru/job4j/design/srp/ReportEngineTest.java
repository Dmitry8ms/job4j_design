package ru.job4j.design.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

import java.util.Calendar;
import java.util.Locale;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report<String> engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenGeneratedForProgrammers() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report<String> htmlEngine = new HTMLreportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE HTML>")
                .append(System.lineSeparator())
                .append("<html>").append(System.lineSeparator())
                .append("<head>").append(System.lineSeparator())
                .append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">")
                .append(System.lineSeparator())
                .append("<title>Report</title>").append(System.lineSeparator())
                .append("</head>").append(System.lineSeparator())
                .append("<body>").append(System.lineSeparator())
                .append("<table>").append(System.lineSeparator())
                .append("<tr>").append(System.lineSeparator())
                .append("<th>Name</th><th>Hired</th><th>Fired</th><th>Salary</th>")
                .append(System.lineSeparator())
                .append("</tr>").append(System.lineSeparator())
                .append("<tr>").append(System.lineSeparator())
                .append("<td>").append(worker.getName()).append("</td>")
                .append("<td>").append(worker.getHired()).append("</td>")
                .append("<td>").append(worker.getFired()).append("</td>")
                .append("<td>").append(worker.getSalary()).append("</td>")
                .append("</tr>").append(System.lineSeparator())
                .append("</table>").append(System.lineSeparator())
                .append("</body>").append(System.lineSeparator())
                .append("</html>").append(System.lineSeparator());
        assertThat(htmlEngine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenGeneratedForAccounting() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report<String> accountingReport = new AccountingReportEngine(store);
        String salaryFormated = String.format("%.2f",
                worker.getSalary() / AccountingReportEngine.RATE) + "USD";
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(salaryFormated).append(";")
                .append(System.lineSeparator());
        assertThat(accountingReport.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenGeneratedForHR() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 200);
        Employee worker3 = new Employee("Maria", now, now, 90);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report<String> hRreport = new HRreportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker3.getName()).append(";")
                .append(worker3.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(hRreport.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenGeneratedForJSON() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        var jsonReport = new JSONreportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("[{\"name\":\"").append(worker.getName())
                .append("\",\"hired\":{\"year\":").append(worker.getHired().get(Calendar.YEAR))
                .append(",\"month\":").append(worker.getHired().get(Calendar.MONTH))
                .append(",\"dayOfMonth\":").append(worker.getHired().get(Calendar.DAY_OF_MONTH))
                .append(",\"hourOfDay\":").append(worker.getHired().get(Calendar.HOUR_OF_DAY))
                .append(",\"minute\":").append(worker.getHired().get(Calendar.MINUTE))
                .append(",\"second\":").append(worker.getHired().get(Calendar.SECOND))
                .append("},\"fired\":{\"year\":").append(worker.getFired().get(Calendar.YEAR))
                .append(",\"month\":").append(worker.getFired().get(Calendar.MONTH))
                .append(",\"dayOfMonth\":").append(worker.getFired().get(Calendar.DAY_OF_MONTH))
                .append(",\"hourOfDay\":").append(worker.getFired().get(Calendar.HOUR_OF_DAY))
                .append(",\"minute\":").append(worker.getFired().get(Calendar.MINUTE))
                .append(",\"second\":").append(worker.getFired().get(Calendar.SECOND))
                .append("},\"salary\":").append(worker.getSalary()).append("}]");
        assertThat(jsonReport.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenGeneratedForXML() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance(Locale.US);
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        var xmlReport = new XMLreportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .append("\n")
                .append("<report>").append("\n")
                .append("    ").append("<employees>").append("\n")
                .append("        ").append("<fired>")
                .append(ToISOdate.convert(worker.getFired())).append("</fired>")
                .append("\n")
                .append("        ").append("<hired>").append(ToISOdate.convert(worker.getHired()))
                .append("</hired>")
                .append("\n")
                .append("        ").append("<name>").append(worker.getName()).append("</name>")
                .append("\n")
                .append("        ").append("<salary>").append(worker.getSalary()).append(
                        "</salary>")
                .append("\n")
                .append("    ").append("</employees>").append("\n")
                .append("</report>").append("\n");
        assertThat(xmlReport.generate(em -> true), is(expect.toString()));
    }
}