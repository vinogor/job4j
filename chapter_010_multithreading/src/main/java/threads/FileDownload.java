package threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

// java -jar wget.jar url 200
// wget (ссылка) (скорость в килобайтах в секунту)

// Для того, чтобы ограничить скорость скачивания
// нужно проверять сколько байтов загрузилось за 1 секунду.
// Если объем больше, то нужно выставлять паузу.
// Пауза должна вычисляться, а не быть константой.

// Program arguments:
// https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml 200

// как создать jar в idea https://www.youtube.com/watch?v=NtFQS4kRgCs

public class FileDownload implements Runnable {

    private static final Logger LOG = LogManager.getLogger(FileDownload.class.getName());

    private String file;
    private int speedLimit;

    public FileDownload(String file, String speedLimit) {
        this.file = file;
        this.speedLimit = Integer.parseInt(speedLimit) * 1024;
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new FileDownload(args[0], args[1]));
        thread.start();
    }

    @Override
    public void run() {
        LOG.trace("==== файл для скачивания: " + file);

        try (BufferedInputStream in = new BufferedInputStream(new URL(file).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {

            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            int sumBytesRead = 0;
            long start = System.currentTimeMillis();
            long current;
            long diff;

            LOG.trace("==== ограничение по скорочти скачивания, байты в 1 сек: " + speedLimit);
            LOG.trace("==== начинаем скачивать и записывать: " + file);
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                sumBytesRead += bytesRead;
                LOG.trace("==== скачалось " + bytesRead + " байт, всего скачано " + sumBytesRead + " байт");

                current = System.currentTimeMillis();
                // прошла секунда или чуть больше
                diff = current - start;
                LOG.trace("==== прошло времени, мс: " + diff);
                if (diff >= 1000) {
                    LOG.trace("==== прошло ~1 сек");
                    // если скорость превышена, то задерживаем чтобы в среднем была нужная скорость
                    if (sumBytesRead > speedLimit) {
                        LOG.trace("==== слишком быстрая скачка, начинаем задержку");
                        Thread.sleep((sumBytesRead / speedLimit) - diff);
                    }
                    sumBytesRead = 0;
                    start = System.currentTimeMillis();
                }
            }
            LOG.trace("==== завершаем скачивание и запись");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}