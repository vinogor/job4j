package jdbc.magnit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Генерация XML из данных базы
// Для создания xml файла нужно использовать технологию JAXB.
public class StoreXML {

    // - target - Файл куда будет сохраняться данные.
    private File target;

    public StoreXML(File target) {
        this.target = target;
    }

    // сохраняет данные из list в файл target
    public void save(List<Entry> list) {
        Entries entries = new Entries(list);
        try {
            target.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(Entries.class, Entry.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            // чтобы переносы строки и пробелы тоже сериализовывались
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            // запуск сериализации (что, куда)
            System.out.println("сериализация в файл xml");
            jaxbMarshaller.marshal(
                entries,
                target
//                System.out  // для вывода на экран
            );
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @XmlRootElement(name = "my_entries")
    static class Entries {

        @XmlElement(name = "my_entry")
        private List<Entry> list;

        public Entries() {
        }

        public Entries(List<Entry> list) {
            this.list = list;
        }
    }
}
