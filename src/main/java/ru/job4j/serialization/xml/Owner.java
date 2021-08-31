package ru.job4j.serialization.xml;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "owner")
public class Owner {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private int age;
    @XmlAttribute
    private String license;

    public Owner() { }

    public Owner(String name, int age, String license) {
        this.name = name;
        this.age = age;
        this.license = license;
    }

    @Override
    public String toString() {
        return "Owner{"
                + "name='" + name + '\''
                + ", age=" + age
                + ", license='" + license + '\''
                + '}';
    }
}
