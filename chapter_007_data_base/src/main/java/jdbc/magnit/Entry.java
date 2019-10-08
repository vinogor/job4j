package jdbc.magnit;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlType(name = "а тут имя не проявляется")
public class Entry {

    @XmlElement(name = "my_field")
    private int field;

    public Entry() {
    }

    public Entry(int field) {
        this.field = field;
    }

    public void setField(int field) {
        this.field = field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return field == entry.field;
    }

    @Override
    public int hashCode() {
        return Objects.hash(field);
    }

    @Override
    public String toString() {
        return "Entry{" +
            "field=" + field +
            '}';
    }
}