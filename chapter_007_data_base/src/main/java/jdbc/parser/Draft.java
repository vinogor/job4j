package jdbc.parser;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Draft {
    
    public static void main(String[] args) {
    
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd MMM yy");
        String date = formatForDateNow.format(new Date()).replace(".", "");
        
        System.out.println(date);
        
        
//        String s = "5 июн. 18, 12:10";
//        String f = "d MMM yy, HH:mm";
//
//        // а если заменить "июня" на "июн" и "MMMM" на "MMM"
//        // то почему то перестаёт работать....
//
//        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern(f);
//        LocalDateTime localDateTime = LocalDateTime.parse(s, formatter1);
//        System.out.println(localDateTime);
    }
}
