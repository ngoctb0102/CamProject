import java.io.InputStream;
 
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
 
public class ImageViewDemo extends Application {
 
    @Override
    public void start(Stage primaryStage) throws Exception {
 
        Class<?> clazz = this.getClass();
 
        InputStream input = clazz.getResourceAsStream("/org/o7planning/javafx/icon/java-48.png");
 
        Image image = new Image(input);
 
        ImageView imageView = new ImageView(image);
 
        InputStream input2 = clazz.getResourceAsStream("/org/o7planning/javafx/icon/java-48.png");
        Image image2 = new Image(input2, 100, 200, false, true);
        ImageView imageView2 = new ImageView(image2);
 
        FlowPane root = new FlowPane();
        root.setPadding(new Insets(20));
 
        root.getChildren().addAll(imageView, imageView2);
 
        Scene scene = new Scene(root, 400, 200);
 
        primaryStage.setTitle("JavaFX ImageView (o7planning.org)");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 
    public static void main(String[] args) {
        Application.launch(args);
    }
 
}