package com.edugames;
// Imports
import com.edugames.view.StartView;
import javafx.application.Application;
import javafx.stage.Stage;

/*
 * Class Main
 * Main class of the JavaFX application.
 * @param: Constructor receives the primaryStage(Stage) and isServer(Boolean) as parameters.
 * @author: Marcus Friberg
 * @author: marcus.friberg@edu.edugrad.se
 * @author: Shermin Gilanziadeh
 * @author: shermin.gilanizadeh@edu.edugrade.se
 * @author: Linda Djurström
 * @author: linda.djurstrom@edu.edugrad.se
 * @author: Matilda Wintence
 * @author: matilda.wintence@edu.edugrade.se
 * @author: Martin Andersson
 * @author: martin.andersson@edu.edugrade.se
 * @version: 1.0
 */
public class Main extends Application {

    // Main Method, launches the start-method.
    public static void main(String[] args) {
	    launch(args);
    }

    /*
     * Start method
     * Starts the JavaFX application and presents the StartView.
     * @param Takes a Stage as parameter.
     * @author: Marcus Friberg
     * @author: marcus.friberg@edu.edugrad.se
     * @author: Shermin Gilanziadeh
     * @author: shermin.gilanizadeh@edu.edugrade.se
     * @author: Linda Djurström
     * @author: linda.djurstrom@edu.edugrad.se
     * @author: Matilda Wintence
     * @author: matilda.wintence@edu.edugrade.se
     * @author: Martin Andersson
     * @author: martin.andersson@edu.edugrade.se
     * @version: 1.0
     */
    @Override
    public void start(Stage primaryStage) {
        // Set the title of the window and create a StackPane
        primaryStage.setTitle("Sänka skepp");
        // Create a new startView and provide it with the primaryStage and let it present itself
        StartView startView = new StartView(primaryStage);
        startView.present();
    }
}
