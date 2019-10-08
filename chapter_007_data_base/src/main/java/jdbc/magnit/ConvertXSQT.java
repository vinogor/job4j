package jdbc.magnit;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ConvertXSQT {

    public void convert(File source, File dest, File scheme) {
        System.out.println("преобразуем xml в другой формат через XSTL");
        String xmlInput = null;
        String xslScheme = null;
        try {
            xmlInput = new String(Files.readAllBytes(source.toPath()));
            xslScheme = new String(Files.readAllBytes(scheme.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = factory.newTransformer(
                new StreamSource(
                    new ByteArrayInputStream(xslScheme.getBytes())
                )
            );
            System.out.println("    загружаем схему преобразования");
            transformer.transform(new StreamSource(
                    new ByteArrayInputStream(xmlInput.getBytes())
                ),
//                new StreamResult(System.out)
                new StreamResult(dest)
            );
            System.out.println("    записываем результат преобразования в файл");
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        System.out.println("завершаем работу программы");
    }
}
