package hoshi.utils;

import hoshi.command.AddCommand;
import hoshi.command.ByeCommand;
import hoshi.command.Command;
import hoshi.command.DeleteCommand;
import hoshi.command.FindCommand;
import hoshi.command.InitializeCommand;
import hoshi.command.ListCommand;
import hoshi.command.MarkCommand;
import hoshi.task.TaskList;
import hoshi.ui.Ui;

/**
 * Parser class for making sense of user command and calling the relevant function
 */
public class Parser {

    private static final int MIN_MARK_LENGTH = 5;
    private static final int MIN_UNMARK_LENGTH = 7;
    private static final int MIN_DELETE_LENGTH = 7;
    private static final String INPUT_ERROR_MESSAGE = "Hoshi doesn't understand, try a different input?";

    private final Storage storage = new Storage("./data/Hoshi.txt");

    /**
     * Parses all user commands into their respective methods as well as display the bye message once
     * program terminates
     *
     * @param input    the input that the user entered which is used to check which command to execute
     * @param taskList the TaskList that stores 3 types of tasks
     * @param ui       Ui that handles all user interaction
     * @return response corresponding to the function that was called depending on user input
     */
    public String parseCommand(String input, TaskList taskList, Ui ui) {

        // check if input is empty
        if (input.isEmpty()) {
            return ui.displayError(INPUT_ERROR_MESSAGE);
        }
        // begin to parse input
        String[] splitInput = input.split(" ");
        String commandInput = splitInput[0].toLowerCase();
        Command command;

        // switch case for parsing commands
        switch (commandInput) {
        case "initialize":
            command = new InitializeCommand();
            break;

        case "bye":
            command = new ByeCommand();
            break;

        case "list":
            command = new ListCommand();
            break;

        case "mark":
            if (input.trim().length() < MIN_MARK_LENGTH) {
                return ui.displayTaskToMark();
            }
            command = new MarkCommand(splitInput, true);
            break;

        case "unmark":
            if (input.trim().length() < MIN_UNMARK_LENGTH) {
                return ui.displayTaskToMark();
            }
            command = new MarkCommand(splitInput, false);
            break;

        case "delete":
            if (input.trim().length() < MIN_DELETE_LENGTH) {
                return ui.displayError(INPUT_ERROR_MESSAGE);
            }
            int deleteIndex = Integer.parseInt(splitInput[1]) - 1;
            command = new DeleteCommand(deleteIndex);
            break;

        case "add":
            command = new AddCommand(splitInput);
            break;

        case "find":
            String keyword = splitInput[1];
            command = new FindCommand(keyword);
            break;

        default:
            return ui.displayError(INPUT_ERROR_MESSAGE);
        }
        return command.execute(taskList, ui, storage);
    }
}
