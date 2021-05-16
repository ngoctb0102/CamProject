import javafx.application.Application;
import java.awt.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.*;
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("DemoJavaFX");
        javafx.scene.control.Button btn = new javafx.scene.control.Button();
        btn.setText("DemoJavaFX");
        StackPane layout = new StackPane();
        layout.getChildren().add(btn);
        Scene scene = new Scene(layout, 300, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }

 
}
