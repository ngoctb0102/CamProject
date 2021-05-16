import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import java.net.URL;
 
public class TestMain  extends Application {
  
 
   @Override
   public void start(Stage primaryStage) {
       try {
           // Đọc file fxml và vẽ giao diện.src\\UserInterface\\Camera-OOP.fxml
           Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("cam.fxml"));
           primaryStage.setTitle("Camera Control");
           primaryStage.setScene(new Scene(root));
           primaryStage.show();
        
       } catch(Exception e) {
           e.printStackTrace();
       }
   }
  
   public static void main(String[] args) {
       Application.launch(args);
   }
  
}
