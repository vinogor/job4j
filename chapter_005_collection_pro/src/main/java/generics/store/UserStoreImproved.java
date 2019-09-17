package generics.store;

public class UserStoreImproved extends AbstractStore<User> {
    public UserStoreImproved(int len) {
        super(len);
    }

//    private final SimpleArray<User> array;

//    public UserStoreImproved(int len) {
//        array = new SimpleArray<>(len);
//    }

//   // @Override
//    public void add(User model) {
//        array.add(model);
//    }
//
//    // найти по id и вставить туда model
//  //  @Override
//    public boolean replace(String id, User model) {
//        boolean result = false;
//        int index = 0;
//        for (User user : array) {
//            if (user.equals(model)) {
//                array.set(index, model);
//                result = true;
//                break;
//            }
//            index++;
//        }
//        return result;
//    }
//
//    @Override
//    public boolean delete(String id) {
//        boolean result = false;
//        int index = 0;
//        for (User user : array) {
//            if (user.getId().equals(id)) {
//                array.remove(index);
//                result = true;
//                break;
//            }
//            index++;
//        }
//        return result;
//    }
//
//    @Override
//    public User findById(String id) {
//        User result = null;
//        int index = 0;
//        for (User user : array) {
//            if (user.getId().equals(id)) {
//                result = user;
//                break;
//            }
//            index++;
//        }
//        return result;
//    }
}
