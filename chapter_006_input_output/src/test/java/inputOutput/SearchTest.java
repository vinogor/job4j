package inputOutput;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SearchTest {

    private Search search;
    private List<String> exts;

    // C:\Users\AndUser\AppData\Local\Temp\
    private static final String path = System.getProperty("java.io.tmpdir");
    private static final File mainFile = new File(path + "/javaTest");

    @Before
    public void setUp() {
        search = new Search();
        exts = new ArrayList<>();
        mainFile.mkdir(); // базовый каталог для тестовых файлов
    }

    // удаляем всё что насоздавали, без рекурсии, через стэк
    @After
    public void tearDown() {
        Queue<File> stack = new LinkedList<>();
        stack.add(mainFile);

        while (!stack.isEmpty()) {
            File leaf = stack.poll(); // достаём с головы и удаляем
            File[] files = leaf.listFiles(); // получаем список файлов и папок

            if (files == null) {
                leaf.delete();
            } else {
                for (File fileTmp : files) {
                    if (fileTmp.isDirectory()) {  // если директория, то
                        if (!fileTmp.delete()) { // если в ней что-то есть
                            stack.offer(fileTmp); // добавляем директорию в конец
                            stack.offer(leaf); // добавляем директорию предка чтобы потом удалить и её
                        }
                    } else { // если это файл - удаляем
                        fileTmp.delete();
                    }
                }
            }
        }
        mainFile.delete(); // удаляем главную папку
    }

// удаление всех файлов и папок через РЕКУРСИЮ:

//    private void deleteFile(File path) {
//        if (path.isDirectory()) {
//            for (File f : path.listFiles()) {
//                if (f.isDirectory()) deleteFile(f);
//                else f.delete();
//            }
//        }
//        path.delete();
//    }

    @Test
    public void filesTest1() throws IOException {

        new File(path + "/javaTest/1.txt").createNewFile();
        new File(path + "/javaTest/someDir").mkdir();
        new File(path + "/javaTest/someDir/2.txt").createNewFile();

        exts.add("txt");
        List<String> result = search.convert(search.files(path + "/javaTest", new CustomFileFilter(exts, true)));
        List<String> expected = new ArrayList<>();
        expected.add("1.txt");
        expected.add("2.txt");

        assertThat(result, is(expected));
    }

    @Test
    public void filesTest2() {
        new File(path + "/javaTest/someDir").mkdir();
        exts.add("txt");
        List<String> result = search.convert(search.files(path + "/javaTest", new CustomFileFilter(exts, true)));
        List<String> expected = new ArrayList<>();
        assertThat(result, is(expected));
    }

    @Test
    public void filesTest3() throws IOException {

        new File(path + "/javaTest/1.txt").createNewFile();
        new File(path + "/javaTest/someDir").mkdir();
        new File(path + "/javaTest/someDir/2.txt").createNewFile();
        new File(path + "/javaTest/someDir/3.txt").createNewFile();
        new File(path + "/javaTest/someDir/someDir2").mkdir();
        new File(path + "/javaTest/someDir/someDir2/anotherDir").mkdir();
        new File(path + "/javaTest/someDir/someDir2/anotherDir/4.txt").createNewFile();

        exts.add("txt");
        List<String> result = search.convert(search.files(path + "/javaTest", new CustomFileFilter(exts, true)));
        List<String> expected = new ArrayList<>();
        expected.add("1.txt");
        expected.add("2.txt");
        expected.add("3.txt");
        expected.add("4.txt");

        assertThat(result, is(expected));
    }

    @Test
    public void filesTest4() throws IOException {

        new File(path + "/javaTest/1.txt").createNewFile();
        new File(path + "/javaTest/1.qqq").createNewFile();
        new File(path + "/javaTest/someDir").mkdir();
        new File(path + "/javaTest/someDir/2.qqq").createNewFile();

        exts.add("txt");
        List<String> result = search.convert(search.files(path + "/javaTest", new CustomFileFilter(exts, true)));
        List<String> expected = new ArrayList<>();
        expected.add("1.txt");

        assertThat(result, is(expected));
    }
}