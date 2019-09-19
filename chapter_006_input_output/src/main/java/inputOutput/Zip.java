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

        File file = new File(root);
        Queue<File> stack = new LinkedList<>();
        List<File> result = new ArrayList<>();

        if (!file.isDirectory()) {
            return null;
        }
        stack.add(file);

        while (!stack.isEmpty()) {
            File leaf = stack.poll(); // достаём с головы и удаляем

            // получаем список файлов и папок, сюда засунем фильтр расширений
            File[] files = leaf.listFiles(
                pathname -> {
                    if (pathname.isFile()) {
                        String name = pathname.getName();
                        if (name.lastIndexOf(".") != -1 && name.lastIndexOf(".") != 0) {
                            String currentExt = name.substring(name.lastIndexOf(".") + 1);
                            return !ext.equals(currentExt);
                        }
                        return false;
                    } else {
                        return true;
                    }
                }
            );

            if (files != null) {
                for (File fileTmp : files) {
                    if (fileTmp.isDirectory()) {
                        stack.add(fileTmp);
                    } else {
                        result.add(fileTmp);
                    }
                }
            }
        }
        return result;
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