package generics.store;

import generics.SimpleArray;

public class RoleStore implements Store<Role> {

    private final SimpleArray<Role> array;

    public RoleStore(int len) {
        array = new SimpleArray<>(len);
    }

    @Override
    public void add(Role model) {
        array.add(model);
    }

    // найти по id и вставить туда model
    @Override
    public boolean replace(String id, Role model) {
        boolean result = false;
        int index = 0;
        for (Role user : array) {
            if (user.getId().equals(id)) {
                array.set(index, model);
                result = true;
                break;
            }
            index++;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        int index = 0;
        for (Role user : array) {
            if (user.getId().equals(id)) {
                array.remove(index);
                result = true;
                break;
            }
            index++;
        }
        return result;
    }

    @Override
    public Role findById(String id) {
        Role result = null;
        int index = 0;
        for (Role user : array) {
            if (user.getId().equals(id)) {
                result = user;
                break;
            }
            index++;
        }
        return result;
    }
}
