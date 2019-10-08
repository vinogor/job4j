package jdbc;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

public class XmlUsage {

    // объект может быть «корнем дерева» элементов в XML
    @XmlRootElement
    public static class User {

        private List<Field> values;

        // нужен пустой конструктор чтобы всё работало
        public User() {
        }

        public User(List<Field> values) {
            this.values = values;
        }

        public List<Field> getValues() {
            return values;
        }

        public void setValues(List<Field> values) {
            this.values = values;
        }
    }

    @XmlRootElement
    public static class Field {

        private int value;

        public Field() {
        }

        public Field(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }


    public static void main(String[] args) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(User.class, Field.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // чтобы переносы строки и пробелы тоже сериализовывались
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // запуск сериализации (что, куда)
        jaxbMarshaller.marshal(
            new User(Arrays.asList(new Field(1), new Field(2))),
            System.out
        );
    }
}
