package hoshi.ui;

import hoshi.task.Task;
import hoshi.task.TaskList;

/**
 * Ui class that handles User Interactions for HoSHI
 */
public class Ui {

    /**
     * Displays all tasks that the user has previously added to Hoshi
     */
    public String displayTasks(TaskList tasks) {
        if (!tasks.isEmpty()) {
            return tasks.toString();
        }
        return "Hoshi doesn't have anything stored! Please add a task first";
    }

    /**
     * Displays all tasks that the user has previously added to Hoshi that match the search query
     */
    public String displayFoundTasks(TaskList tasks) {
        if (!tasks.isEmpty()) {
            return displayMatchingList() + "\n" + tasks;
        }

        return "Hoshi couldn't find tasks with that keyword, please try again!";
    }

    /**
     * Displays text requesting user to specify which task to mark
     */
    public String displayTaskToMark() {
        return "Please specify the task number to mark!";
    }

    /**
     * Displays text indicating text has been marked as done
     */
    public String displayTaskMarked(Task task) {
        return "Nice! Hoshi has marked this task as done: \n" + task.toString();
    }

    /**
     * Displays text indicating text has been unmarked
     */
    public String displayTaskUnmarked(Task task) {
        return "Nice! Hoshi has marked this task as not done: \n" + task.toString();
    }

    /**
     * Displays text requesting user to specify which task to delete
     */
    public String displayTaskToDelete() {
        return "Please specify the task number to delete!";
    }

    /**
     * Displays text indicating which task was deleted
     */
    public String displayTaskDeleted(Task task) {
        return "OK, Hoshi has removed (" + task.getDesc() + ")!";
    }

    /**
     * Displays text prompting the user to specify a keyword to search for
     */
    public String displayKeywordToFind() {
        return "Please specify the keyword to find this task!";
    }

    /**
     * Displays text indicating task has been added
     */
    public String displayTaskAdded(String desc, String taskType) {
        return "Hoshi has added " + taskType + ": " + desc;
    }

    /**
     * Displays text indicating search has returned several tasks
     */
    public String displayMatchingList() {
        return "Hoshi has found the following tasks matching your search!";
    }

    /**
     * Displays text indicating that task has already been marked
     */
    public String displayAlreadyMarked() {
        return "Hoshi has already marked this task!";
    }

    /**
     * Displays text indicating that task has already been unmarked
     */
    public String displayAlreadyUnmarked() {
        return "Hoshi has already unmarked this task!";
    }

    /**
     * Displays text indicating an error has occurred
     */
    public String displayError(String message) {
        return message;
    }

    /**
     * Displays text indicating a loading error has occurred
     */
    public void displayLoadingError(String message) {
        System.out.println("Hoshi can't load data! " + message);
    }

    /**
     * Displays text indicating a saving error has occurred
     */
    public void displaySavingError(String message) {
        System.out.println("Hoshi can't save data! " + message);
    }

    /**
     * Displays text indicating the program has terminated
     */
    public String displayBye() {
        return "Bye, Hope to see you again soon! \n";
    }

    /**
     * Displays text indicating the program has started
     */
    public String initialize() {
        return "Welcome to Hoshi! Try the following commands out!\n"
                + "1.) Add todo/deadline/event\n"
                + "2.) Mark/Unmark {1}\n"
                + "3.) Delete {1}\n"
                + "4.) Find\n"
                + "5.) List\n"
                + "6.) Bye";
    }
}
