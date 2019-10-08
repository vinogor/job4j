package jdbc.magnit;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StoreSQL sql = new StoreSQL(new Config());
        sql.connectDB();
        sql.createTable();
        sql.generate(10);
        List<Entry> list = sql.load();

        File source = new File("c:\\sqlite\\source.xml");
        StoreXML storeXML = new StoreXML(source);
        storeXML.save(list);

        new ConvertXSQT().convert(
            source,
            new File("c:\\sqlite\\dest.xml"),
            new File("c:\\sqlite\\scheme.xml")
        );
    }
}