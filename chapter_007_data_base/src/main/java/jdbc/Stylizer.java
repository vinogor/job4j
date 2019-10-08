package jdbc;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.File;

// почему-то не переносит на новую строчку если открыть в блокноте
// но если открыть в браузере то переносы есть

public class Stylizer {

    public static void main(String args[]) throws TransformerException {
        String xsl = "" +
            // всегда должен начинаться с XML декларации
            "<?xml version=\"1.0\"?>\r\n" +

            // определяет, что данный документ это таблица стилей XSLT
            // (с атрибутами номера версии и пространства имен XSLT).
            "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\r\n" +

            // атрибут match используется для ассоциации шаблона с XML элементом
            // Атрибут match="/" ассоциирует шаблон с корневым элементом исходного XML документа.
            // Содержимое элемента <xsl:template> определяет некий HTML код, который записывается в выходной документ.
            "<xsl:template match=\"/\">\r\n" +

            // создаём тэг
            "<entries>\r\n" +
            //  для каждого тэга values внутри тэга user в исходном файле
            "   <xsl:for-each select=\"user/values\">\r\n" +
            // создаём тэг
            "       <entry>\r\n" +
            // задаём ему имя атрибута
            "           <xsl:attribute name=\"href\">\r\n" +
            // присваиваем атрибуту значение, полученное из содержимого тэга <value>
            "               <xsl:value-of select=\"value\"/>\r\n" +
            "           </xsl:attribute>\r\n" +
            "       </entry>\r\n" +
            "   </xsl:for-each>\r\n" +
            " </entries>\r\n" +

            "</xsl:template>\r\n" +
            "</xsl:stylesheet>\r\n";

        String xml = "" +
            "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\r\n" +
            "<user>\r\n" +
            "    <values>\r\n" +
            "        <value>1</value>\r\n" +
            "    </values>\r\n" +
            "    <values>\r\n" +
            "        <value>2</value>\r\n" +
            "    </values>\r\n" +
            "</user>";

        System.out.println(xsl);
        System.out.println();
        System.out.println(xml);
        System.out.println();

        File file = new File("c:\\sqlite\\file2.xml");

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(
            new StreamSource(
                new ByteArrayInputStream(xsl.getBytes()))
        );
        transformer.transform(new StreamSource(
                new ByteArrayInputStream(xml.getBytes())),
            new StreamResult(System.out)
//            new StreamResult(file)

        );
    }
}