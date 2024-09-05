package hoshi.gui;

import java.util.Objects;

import hoshi.Hoshi;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


/**
 * Controller for the Hoshi's main GUI.
 */
public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Hoshi hoshi;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.JPG"));
    private final Image hoshiImage = new Image(this.getClass().getResourceAsStream("/images/Hoshi.JPG"));

    /**
     * Initializes GUi and initial message
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        userInput.setPromptText("Enter your input here...");

        String initialMessage = "Welcome to Hoshi! Try the following commands out! \n"
                + "1.) Add todo/deadline/event\n"
                + "2.) Mark/Unmark\n"
                + "3.) Delete\n"
                + "4.) Find\n"
                + "5.) Bye";
        dialogContainer.getChildren().add(DialogBox.getHoshiDialog(initialMessage, hoshiImage));
    }

    /**
     * Injects the Hoshi instance
     */
    public void setHoshi(Hoshi d) {
        hoshi = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = hoshi.run(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getHoshiDialog(response, hoshiImage)
        );
        userInput.clear();

        if (Objects.equals(response, "Bye, Hope to see you again soon! \n")) {

            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            // instruct to exit the application after the delay
            delay.setOnFinished(event -> Platform.exit());
            // start the delay
            delay.play();
        }
    }

}
