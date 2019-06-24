package ru.job4j.search;

import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     *
     * @param taskToPut задача
     */
    public void put(Task taskToPut) {

        if (tasks.size() == 0) {
            tasks.add(taskToPut);
        } else {
            // чтобы избежать канкарент модиф эксепшен
            LinkedList<Task> tasksCopy = new LinkedList<>(tasks);
            for (Task task : tasksCopy) {
                if (task.getPriority() >= taskToPut.getPriority()) {
                    tasks.add(tasksCopy.indexOf(task), taskToPut);
                    break;
                }
            }
        }
    }

    // возвращает и удаляет из головы
    public Task take() {
        return this.tasks.poll();
    }
}