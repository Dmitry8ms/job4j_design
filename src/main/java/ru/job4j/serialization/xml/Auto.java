package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "vw")
@XmlAccessorType(XmlAccessType.FIELD)
public class Auto {
    @XmlAttribute
    private String model;
    @XmlAttribute
    private String color;
    private Owner owner;
    @XmlAttribute
    private boolean stolen;
    private String[] options;

    public Auto() {    }

    public Auto(String model, String color, Owner owner, boolean stolen, String... options) {
        this.model = model;
        this.color = color;
        this.owner = owner;
        this.stolen = stolen;
        this.options = options;
    }

    @Override
    public String toString() {
        return "Auto{"
                + "model='" + model + '\''
                + ", color='" + color + '\''
                + ", owner=" + owner
                + ", stolen=" + stolen
                + ", options=" + Arrays.toString(options)
                + '}';
    }

    public static void main(String[] args) throws JAXBException, IOException {
        Auto vw = new Auto("Touareg",
                "Moon Light", new Owner("Ivan Ivanov", 40, "77AUM676"),
                false, "busyness interior", "glass roof", "sport engine");
        JAXBContext context = JAXBContext.newInstance(Auto.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(vw, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Auto result = (Auto) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}