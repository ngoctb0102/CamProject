import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
public class TestMain  extends Application {
  
 
   @Override
   public void start(Stage primaryStage) {
       try {
           // Đọc file fxml và vẽ giao diện.
           Parent root = FXMLLoader.load(getClass().getResource("D:\OOP thuc hanh\CamProject\src\Camera-OOP.fxml"));
           
           primaryStage.setTitle("Camera Control");
           primaryStage.setScene(new Scene(root));
           primaryStage.show();
        
       } catch(Exception e) {
           e.printStackTrace();
       }
   }
  
   public static void main(String[] args) {
       launch(args);
   }
  
}