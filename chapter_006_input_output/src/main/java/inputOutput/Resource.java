package inputOutput;

import java.io.File;

public class Resource {
    public static void main(String[] args) {
        String fileName = "app.properties";
        System.out.println(fileName);
//        System.out.println(new Resource().getClass().getResource(fileName));
//        System.out.println(Resource.class.getClassLoader().getResource(fileName));

        System.out.println(Resource.class.getClassLoader().getResource(fileName));

        String prop = System.getProperty("user.dir"); // корень всего проэкта
        System.out.println(prop);
        File file = new File(prop + File.separator + "chapter_006_input_output/src/main/resources/app.properties");

        System.out.println(file);
//        File file = new File()
    }
}
