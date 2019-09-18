package inputOutput;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class AnalizyTest {

    private static Analizy analizy;
    private static final String prop = System.getProperty("user.dir"); // получаем путь до корня текущего МОДУЛЯ
    private static final String path = prop + "/src/main/resources/"; // полный путь до файла
    private static final String sourceTest = "sourceTest.txt";
    private static final String targetTest = "targetTest.txt";

    @Before
    public void setUp() {
        analizy = new Analizy();
    }

    @After
    public void after() {
        new File(path + sourceTest).delete();
        new File(path + targetTest).delete();
    }

    @Test
    public void unavailableTest1() {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(path + sourceTest))) {
            out.println("200 10:56:01");
            out.println("200 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        analizy.unavailable(path + sourceTest, path + targetTest);

        try (BufferedReader reader = new BufferedReader(new FileReader(path + targetTest))) {
            assertThat(reader.readLine(), is("10:58:01;10:59:01;"));
            assertThat(reader.readLine(), is("11:01:02;11:02:02;"));
            assertThat(reader.readLine(), is(nullValue()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void unavailableTest2() {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(path + sourceTest))) {
            out.println("200 10:56:01");
            out.println("200 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("500 11:02:02");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        analizy.unavailable(path + sourceTest, path + targetTest);

        try (BufferedReader reader = new BufferedReader(new FileReader(path + targetTest))) {
            assertThat(reader.readLine(), is("10:58:01;10:59:01;"));
            assertThat(reader.readLine(), is("11:01:02;no data;"));
            assertThat(reader.readLine(), is(nullValue()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void unavailableTest3() {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(path + sourceTest))) {
            out.println("200 10:56:01");
            out.println("200 10:57:01");
            out.println("200 10:59:01");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        analizy.unavailable(path + sourceTest, path + targetTest);

        try (BufferedReader reader = new BufferedReader(new FileReader(path + targetTest))) {
            assertThat(reader.readLine(), is(nullValue()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}