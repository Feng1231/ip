package duck.data;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import duck.data.exception.DuckException;
import duck.data.task.Task;
import duck.storage.Storage;



/**
 * Represents a list of tasks in the Duck application.
 * This class extends {@link ArrayList} and provides methods for adding and deleting tasks,
 * as well as interacting with the storage system to persist changes.
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Adds a task to the task list and updates the storage system.
     *
     * @param task The task to be added to the list.
     * @param storage The storage system used to save the task.
     * @throws DuckException If an error occurs while interacting with the storage system.
     */
    public void addTask(Task task, Storage storage) throws DuckException {
        this.add(task);
        storage.appendTask(task);
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + this.size() + " tasks in the list.\n");
    }

    /**
     * Marks a task as done or incomplete and updates the storage system.
     *
     * @param taskIndex The index of the task to be marked as done.
     * @param isDone A boolean indicating whether the task should be marked as done or incomplete.
     */
    public void updateTaskStatus(int taskIndex, boolean isDone) {
        if (isDone) {
            this.get(taskIndex).markAsDone();
        } else {
            this.get(taskIndex).markAsIncomplete();
        }


    }
    /**
     * Deletes a task from the task list by its index and updates the storage system.
     *
     * @param index The index of the task to be deleted.
     * @param storage The storage system used to update the task list.
     * @throws DuckException If an error occurs while interacting with the storage system.
     */
    public void deleteTask(int index, Storage storage) throws DuckException {
        System.out.println("Noted. I've removed this task:\n" + this.get(index));
        this.remove(index);
        storage.writeTasks(this);
        System.out.println("Now you have " + this.size() + " tasks in the list.\n");
    }

    /**
     * Finds all the tasks in the task list that contain the specified keyword in description.
     *
     * @param keyword The index of the task to be marked as done.
     */
    public TaskList findTasks(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (Task t : this) {
            if (t.getDescription().contains(keyword)) {
                matchingTasks.add(t);
            }
        }
        return matchingTasks;
    }

    /** Prints all the tasks in the task list, with index. */
    public void printTasks() {
        AtomicInteger idx = new AtomicInteger(1);
        this.forEach(task -> System.out.println(idx.getAndIncrement() + "." + task.toString()));
    }
}
