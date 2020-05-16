package monitoreSynch;

import java.io.*;

// Поправьте код с ошибками в коде.
// - Ошибки в многопоточности.
// - Ошибки в IO.
// - Ошибки в производительности.

public class ParseFile {

    private File file;

    public synchronized void setFile(File f) {
        this.file = f;
    }

    public synchronized File getFile() {
        return this.file;
    }

    public synchronized String getContent() throws IOException {

        StringBuilder sb;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append('\n');
            }
        }
        return sb.toString();
    }

    public synchronized String getContentWithoutUnicode() throws IOException {

        StringBuilder sb;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            sb = new StringBuilder();

            int data;
            while ((data = br.read()) != -1) {
                if (data < 0x80) { // ???
                    sb.append((char) data);
                }
            }
        }
        return sb.toString();
    }

    public synchronized void saveContent(String content) throws IOException {

        try (FileWriter writer = new FileWriter(file, false)) {
            writer.write(content);
            writer.flush();
        }
    }
}