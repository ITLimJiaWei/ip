import java.util.ArrayList;
import java.util.Scanner;

public class Hoshi {



    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object

        String logo = "\n" +
                " __    __    ______        _______. __    __   __  \n" +
                "|  |  |  |  /  __  \\      /       ||  |  |  | |  | \n" +
                "|  |__|  | |  |  |  |    |   (----`|  |__|  | |  | \n" +
                "|   __   | |  |  |  |     \\   \\    |   __   | |  | \n" +
                "|  |  |  | |  `--'  | .----)   |   |  |  |  | |  | \n" +
                "|__|  |__|  \\______/  |_______/    |__|  |__| |__| \n" +
                "                                                   \n";

        System.out.println(logo);

        System.out.println("____________________________________________________________\n" +
                "Hello! Im Hoshi!\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n");

        ArrayList<Task> arrayList = new ArrayList<Task>();

        //Task[] array = new Task[100];
        //int indexCounter = -1;

        while (true) {


            System.out.println("Add ToDo/Deadline/Event OR List(Lowercase): ");

            // take in input
            String input = scanner.nextLine();

            // bye
            if (input.equals("bye")) {
                break;

                // list
            } else if (input.equals("list")) {

                if (!arrayList.isEmpty()) {

                    // for loop for listing
                    for (int i = 0; i < arrayList.size(); i++) {

                        System.out.println(i+1 + ". "+ arrayList.get(i).toString() + "\n");
                    }
                }  else {
                    System.out.println("Hoshi doesn't have anything stored! Please add a task first");
                }


                // mark
            } else if (input.startsWith("mark")) {

                // split string
                String[] splitInput = input.split(" ");

                // get only the number from the 2nd half of the splitInput
                int markIndex = Integer.parseInt(splitInput[1]) - 1;


                try {

                    // if specified index is not out of bounds
                    if (markIndex <= arrayList.size()) {

                        arrayList.get(markIndex).setIsDone(true);

                        System.out.println("Nice! I've marked this task as done: \n");
                        System.out.println(arrayList.get(markIndex).toString() + "\n");
                    } else {
                        throw new HoshiException("Hoshi doesn't have such a task!");
                    }


                } catch (HoshiException e) {
                    System.out.println(e.getMessage());
                }


                // unmark
            } else if (input.startsWith("unmark")) {

                // split string
                String[] splitInput = input.split(" ");

                // get only the number from the 2nd half of the splitInput
                int markIndex = Integer.parseInt(splitInput[1]) - 1;

                try {

                    // if specified index is not out of bounds
                    if (markIndex <= arrayList.size()) {

                        // set isDone to false
                        arrayList.get(markIndex).setIsDone(false);

                        System.out.println("OK, I've marked this task as not done yet: \n");
                        System.out.println(arrayList.get(markIndex).toString() + "\n");

                    } else {
                        throw new HoshiException("Hoshi doesn't have such a task!");
                    }

                } catch (HoshiException e) {
                    System.out.println(e.getMessage());
                }

                // delete a task
            } else if (input.startsWith("delete")) {

                // split string
                String[] splitInput = input.split(" ");

                // get only the number from the 2nd half of the splitInput
                int markIndex = Integer.parseInt(splitInput[1]) - 1;

                System.out.println("OK, Hoshi has removed ( " + arrayList.get(markIndex).getDesc() + " )! \n");

                arrayList.remove(markIndex);

                // add a Task
            } else {

                switch (input) {
                    case "todo" -> {

                        System.out.println("Understood! What is your ToDo? ");
                        String desc = scanner.nextLine();

                        try {

                            if (desc.isEmpty()) {
                                throw new HoshiException("Hoshi dosen't understand! Is input empty?");
                            }

                            Todo newToDo = new Todo(desc);
                            arrayList.add(newToDo);
                            System.out.println("added: " + input);

                        } catch (HoshiException e) {
                            System.out.println(e.getMessage());
                        }

                    }
                    case "deadline" -> {

                        System.out.println("Understood! What is your Deadline? ");
                        String desc = scanner.nextLine();

                        try {

                            if (desc.isEmpty()) {
                                throw new HoshiException("Hoshi dosen't understand! Is input empty?");
                            }

                            System.out.println("When would you like your Deadline to be due by? ");

                            // take in input
                            String endTime = scanner.nextLine();

                            Deadline newDeadline = new Deadline(desc, endTime);
                            arrayList.add(newDeadline);
                            System.out.println("added: " + input);

                        } catch (HoshiException e) {
                            System.out.println(e.getMessage());
                        }


                    }
                    case "event" -> {

                        System.out.println("Understood! What is your Event? ");

                        String desc = scanner.nextLine();

                        try {
                            if (desc.isEmpty()) {
                                throw new HoshiException("Hoshi dosen't understand! Is input empty?");
                            }

                            System.out.println("When would you like your Event to start? ");

                            // take in input
                            String startTime = scanner.nextLine();

                            System.out.println("When would you like your Event to end? ");

                            // take in input
                            String endTime = scanner.nextLine();

                            Event newEvent = new Event(desc, startTime, endTime);
                            arrayList.add(newEvent);
                            System.out.println("added: " + input);


                        } catch (HoshiException e) {
                            System.out.println(e.getMessage());
                        }


                    }
                    default ->

                        // in event of invalid input
                            System.out.println("Hoshi dosen't understand! Please try again with the above keywords(in lowercase)");
                }

                System.out.println("____________________________________________________________");
            }



            // echo
            //System.out.println(input);


        }

        bye();




    }

    /**
     * Prints bye message when user terminates the program
     *
     */
    static void bye() {
        System.out.println("""
                Bye. Hope to see you again soon!\s
                ____________________________________________________________
                """);
    }

}
