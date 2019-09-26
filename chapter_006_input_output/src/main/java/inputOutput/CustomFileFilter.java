package inputOutput;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

public class CustomFileFilter implements FileFilter {

    private String searchName = null;
    private boolean isIncludeExts;
    private List<String> exts;

    public CustomFileFilter(List<String> exts, boolean isIncludeExts) {
        this.isIncludeExts = isIncludeExts;
        this.exts = exts;
    }

    public CustomFileFilter(String searchName) {
        this.searchName = searchName;
    }

    @Override
    public boolean accept(File pathname) {
        if (pathname.isFile()) {
            String name = pathname.getName();

            // если ищем файл с конкретным именем
            if (searchName!= null) {
                return searchName.equals(name);
            }

            // если ищем файл с одни из заданных расширений
            if (name.lastIndexOf(".") != -1 && name.lastIndexOf(".") != 0) {
                String ext = name.substring(name.lastIndexOf(".") + 1);
                return isIncludeExts == exts.contains(ext);
            }
            return false;
        } else {
            return true;
        }
    }
}
