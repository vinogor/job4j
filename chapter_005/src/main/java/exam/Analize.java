package exam;

import java.util.ArrayList;
import java.util.List;

public class Analize {

    // вначале:
    //      в previous есть удалённые, изменённые, не изменённые
    //      в previous есть            изменённые, не изменённые, добавленные

    public Info diff(List<User> previous, List<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;

        List<User> previousTemp = new ArrayList<>(previous);
        for (User prevUser : previousTemp) {
            if (current.contains(prevUser)) {
                // удаляем одинаковые User, т.е. не изменённые
                previous.remove(prevUser);
                current.remove(prevUser);
            }
        }

        // терерь:
        //      в previous остались только удалённые и изменённые
        //      в current остались только изменённые и добавленные
        //
        // найдём все изменённые
        for (User prevUser : previous) {
            for (User currUser : current) {
                if (prevUser.id == currUser.id) {
                    changed++;
                }
            }
        }

        deleted = previous.size() - changed;
        added = current.size() - changed;

        return new Info(added, changed, deleted);
    }
}
