package inputOutput;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/*
Program arguments:
-d c:\Projects\IdeaProjects\job4j\chapter_006_input_output\ -e *.xml -o project.zip
*/

public class Zip {

    private List<File> seekBy(String root, String ext) {
        return new Search().files(
            root,
            new CustomFileFilter(
                new ArrayList<>() {{
                    add(ext);
                }},
                false // исключая
            )
        );
    }

    void pack(List<File> sources, File target) {

        // поток куда будем писать
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {

            for (File file : sources) {
                // создаём заготовку файла в который будем записывать
                zip.putNextEntry(new ZipEntry(file.getPath()));

                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                    // записываем
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

//        System.out.println(Arrays.toString(args));

        Args arguments = new Args(args);
        String directory = arguments.directory();
        String exclude = arguments.exclude().substring(2);
        String output = arguments.output();

        Zip zip = new Zip();

        List<File> files = zip.seekBy(directory, exclude);

//        for (int i = 0; i < (files != null ? files.size() : 0); i++) {
//            System.out.println(files.get(i));
//        }

        zip.pack(files, new File(directory + "/" + output));
    }
}