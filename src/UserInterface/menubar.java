import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
 
public class MenuBar extends Application {
 
   @Override
   public void start(Stage stage) {
  
       // Tạo MenuBar
       MenuBar menuBar = new MenuBar();
 
  
       // Tạo các Menu
       Menu insertMenu = new Menu("Insert");
       Menu editMenu = new Menu("Edit");
       Menu helpMenu = new Menu("Help");
  
       // Tạo các MenuItem
       MenuItem insertItem = new MenuItem("Room");
       MenuItem camItem = new MenuItem("Camera");
       MenuItem objItem = new MenuItem("Object");
  
       // Sét đặt phím tắt cho MenuItem Room.
       insertItem.setAccelerator(KeyCombination.keyCombination("Ctrl+R"));
 
        // Thiết lập sự kiện khi người dùng chọn vào Exit.
       exitItem.setOnAction(new EventHandler<ActionEvent>() {
 
           @Override
           public void handle(ActionEvent event) {
               System.exit(0);
           }
       });
 
       // Add menuItems to the Menus
       // Thêm các MenuItem vào Menu.
       fileMenu.getItems().addAll(newItem, openFileItem, exitItem);
 
       // Add Menus to the MenuBar
       // Thêm các Menu vào MenuBar
       menuBar.getMenus().addAll(insertMenu, editMenu, helpMenu);
 
       BorderPane root = new BorderPane();
       root.setTop(menuBar);
       Scene scene = new Scene(root, 350, 200);
 
       stage.setTitle("JavaFX Menu (o7planning.org)");
       stage.setScene(scene);
       stage.show();
   }
 
   public static void main(String[] args) {
       Application.launch(args);
   }
 
}