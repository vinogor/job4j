package map.custom;

public class DraftMain {


    public static void main(String[] args) {
        DraftMain draftMain = new DraftMain();

        System.out.println(Math.log(16)/Math.log(2));

//        int hashCode = draftMain.hashCode();
//        System.out.println(hashCode);
//        int random = (int) (Math.random() * 2_000_000_000);
        int random = (int) (Math.random() * Integer.MAX_VALUE);
        System.out.println(random);
        int i = random >>> (int) (28);
        System.out.println(i);
//        int i = random >>> (int) (32 - (Math.log(16) / Math.log(2)));
    }
}
