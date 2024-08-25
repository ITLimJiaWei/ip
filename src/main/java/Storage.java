import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Storage class that handles I/O related functions such as Loading and Saving for Hoshi
 */
public class Storage {

    private final String filePath;

    /**
     * Constructs a new instance of Storage.
     *
     * @param filePath String path of where Hoshi txt file is relatively located from root
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }



    /**
     * Loads tasks from hoshi txt file if user is not new else greets the user.
     *
     * @param filePath  String filepath that contains the path of the hoshi txt file.
     * @param arrayList ArrayList of 3 types of tasks to be retrieved from hoshi txt file.
     */
    private static void Load(String filePath, ArrayList<Task> arrayList) throws FileNotFoundException {

        File file = new File(filePath);

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {

                String line = scanner.nextLine();
                String[] parts = line.split(", ");

                String taskType = parts[0];
                Boolean isDone = Boolean.FALSE;
                if (Objects.equals(parts[1], "D")) {
                    isDone = Boolean.TRUE;
                }
                String description = parts[2];

                switch (taskType) {
                case "Todo":

                    Todo todo = new Todo(description, isDone);
                    arrayList.add(todo);
                    break;

                case "Deadline":

                    String deadlineEndTime = parts[3];

                    LocalDate deadlineDateTimeEnd = LocalDate.parse(deadlineEndTime);

                    Deadline deadline = new Deadline(description, isDone, deadlineDateTimeEnd);
                    arrayList.add(deadline);
                    break;

                case "Event":

                    String endTime = parts[3];
                    String startTime = parts[4];

                    LocalDate dateTimeEnd = LocalDate.parse(endTime);
                    LocalDate dateTimeStart = LocalDate.parse(startTime);

                    Event event = new Event(description, isDone, dateTimeEnd, dateTimeStart);
                    arrayList.add(event);
                    break;

                default:

                    System.out.println("Hoshi is not aware of this task type: " + taskType + "!");
                    break;
                }

            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Hoshi has detected a new user! Welcome!");
        }

    }


    /**
     * Saves tasks added and retrieved during the program to hoshi txt file.
     *
     * @param filePath  String filepath that contains the path of the hoshi txt file.
     * @param arrayList ArrayList of 3 types of tasks to be written to hoshi txt file.
     */
    private static void Save(String filePath, ArrayList<Task> arrayList) throws IOException {

        try {
            FileWriter fileWriter = new FileWriter(filePath);

            for (Task task : arrayList) {

                // Deadline(TaskType), T(D = Done/ ND = Not Done), Description, endTime, startTime
                String taskType = task.getClass().getName();
                String isDone = task.getStatusIcon();
                if (Objects.equals(isDone, " ")) {
                    isDone = "ND";
                } else {
                    isDone = "D";
                }
                String description = task.getDesc();

                String additionalFields = "";

                if (taskType.equals("Deadline")) {

                    Deadline deadline = ((Deadline) task);
                    additionalFields = ", " + deadline.getEndTime();

                } else if (taskType.equals("Event")) {

                    Event event = ((Event) task);
                    additionalFields = ", " + event.getEndTime() + ", " + event.getStartTime();

                }

                String textToAdd = taskType + ", " + isDone + ", " + description + additionalFields + System.lineSeparator();
                fileWriter.write(textToAdd);
            }
            fileWriter.close();


        } catch (IOException e) {
            System.out.println("Hoshi has an error! " + e.getMessage());
        }

    }


}
