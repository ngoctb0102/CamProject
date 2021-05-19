
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.paint.Color;
import javafx.scene.shape.Line; 
import javafx.stage.Stage;  
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

public class testdraw extends Application {

    public void start(Stage primaryStage) {
        Room r = new Room(20.0,20.0,20.0);
        Camera c = new Camera(20,20,20,90,90,10.0);
        r.addCam(c);
        Camera c1 = new Camera(18,0,20,90,90,50);
        r.addCam(c1);
        Points A = new Points(5,5,0);
        Points B = new Points(5,10,0);
        Points C = new Points(10,10,0);
        Points D = new Points(10,5,0);
        Object o = new Object(A,B,C,D,15);
        r.addObj(o);
        Points A1 = new Points(1,1,0);
        Points B1 = new Points(3,3,0);
        Points C1 = new Points(3,1,0);
        Points D1 = new Points(1,3,0);
        Object o1 = new Object(A1,B1,C1,D1,8);
        r.addObj(o1);
        ArrayList<Points> p = r.lBot();
        Group root = new Group();
        Rectangle re = new Rectangle(100,100,20,20);
        root.getChildren().add(re);
        for(int i = 0;i<p.size();i++){
            Line line = new Line(p.get(i).getX()+100,p.get(i).getY()+100,p.get(i).getX()+100,p.get(i).getY()+100);
        //Line lines = new Line(11,11,11,11);
            line.setStroke(Color.GREEN);
            root.getChildren().add(line);
        }
        
        
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

