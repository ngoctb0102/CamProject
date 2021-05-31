import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.paint.Color;
import javafx.scene.shape.Line; 
import javafx.stage.Stage;  
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.scene.text.Text;

public class draw extends Application{
    String[] s1;
    String[] s2;
    String[] s3;
    String s4;
    // Room r;
    // ArrayList<Points> p = new ArrayList<Points>();
    // //to build a draw !!
    // double x;
    // double y;
    // public Room getR() {
    //     return r;
    // }
    // public void setR(Room r) {
    //     this.r = r;
    // }
    // public ArrayList<Points> getP() {
    //     return p;
    // }
    // public void setP(ArrayList<Points> p) {
    //     this.p = p;
    // }
    // public double getX() {
    //     return x;
    // }
    // public void setX(double x) {
    //     this.x = x;
    // }
    // public double getY() {
    //     return y;
    // }
    // public void setY(double y) {
    //     this.y = y;
    // }
    // public int getOption() {
    //     return option;
    // }
    // public void setOption(int option) {
    //     this.option = option;
    // }
    // private int option = 0;
    //cuntruc to build a draw funtion
    //0. Top
    //1. bot
    //2. Left
    //3. Right
    //4. behind
    //5. front
    // public draw(Room room, int option){
    //     super();
    //     this.r = room;
    //     this.option = option;
    //     if(option == 0){
    //         this.p = room.lTop();
    //         this.x = room.getW();
    //         this.y = room.getL();
    //     }else if(option == 1){
    //         this.p = room.lBot();
    //         this.x = room.getW();
    //         this.y = room.getL();
    //     }else if(option == 2){
    //         this.p = room.lLeft();
    //         this.x = room.getL();
    //         this.y = room.getH();
    //     }else if(option == 3){
    //         this.p = room.lRight();
    //         this.x = room.getL();
    //         this.y = room.getH();
    //     }else if(option == 4){
    //         this.p = room.lBehind();
    //         this.x = room.getW();
    //         this.y = room.getH();
    //     }else if(option == 5){
    //         this.p = room.lFront();
    //         this.x = room.getW();
    //         this.y = room.getH();
    //     }else{
    //         this.p = room.lTop();
    //         this.x = room.getW();
    //         this.y = room.getL();
    //     }
    // }
    @Override
    public void init() throws Exception{
        this.s1 = getParameters().getRaw().get(0).split("\\s");
        this.s2 = getParameters().getRaw().get(1).split("\\s");
        this.s3 = getParameters().getRaw().get(2).split("\\s");
        this.s4 = getParameters().getRaw().get(3);
    }
    public void start(Stage draw) throws Exception{
        //super.init();
        System.out.println("Drawing please wait ...");
        Group root = new Group();
        //set full plane is dark
        // System.out.println(getX());
        //x = getParameters().get
        double x = Double.parseDouble(s1[0]);
        double y = Double.parseDouble(s1[1]);
        //mutil percentage
        double z = 10;
        double t = 5;
        if(x < 10 || y < 10){
            z = 100;
            t = 0.5;
        }
        Rectangle re = new Rectangle(t*z,t*z,x*z,y*z);
        root.getChildren().add(re);
        if(s2.length != 1){
            for(int i = 0;i<s2.length;i+=2){
                Line line = new Line((Double.parseDouble(s2[i])+t)*z,(Double.parseDouble(s2[i+1])+t)*z,(Double.parseDouble(s2[i])+t)*z,(Double.parseDouble(s2[i+1])+t)*z);
                line.setStroke(Color.WHITE);
                root.getChildren().add(line);
            }
        }
        
        Line l1 = new Line(t*z,t*z,(t+x)*z,t*z);
        Line l2 = new Line((t+x)*z,t*z,(t+x)*z,(t+y)*z);
        Line l3 = new Line((t+x)*z,(t+y)*z,t*z,(t+y)*z);
        Line l4 = new Line(t*z,(t+y)*z,t*z,t*z);
        root.getChildren().add(l1);
        root.getChildren().add(l2);
        root.getChildren().add(l3);
        root.getChildren().add(l4);
        Text t1 = new Text();
        t1.setText(s3[0]);
        t1.setX(50);
        t1.setY(15);
        Line ox = new Line(5,5,50,5);
        root.getChildren().add(ox);
        Line o1 = new Line(50,5,45,3);
        root.getChildren().add(o1);
        Line o2 = new Line(50,5,45,7);
        root.getChildren().add(o2);
        Text t2 = new Text();
        t2.setText(s3[1]);
        t2.setX(10);
        t2.setY(50);
        Line oy = new Line(5,5,5,50);
        Line o3 = new Line(5,50,3,45);
        Line o4 = new Line(5,50,7,45);
        root.getChildren().add(o3);
        root.getChildren().add(o4);
        root.getChildren().add(oy);
        root.getChildren().add(t1);
        root.getChildren().add(t2);
        Text title = new Text();
        title.setText(s4);
        title.setX(20 + t*x);
        title.setY(20);
        root.getChildren().add(title);
        
        Scene scene = new Scene(root, 100 + z*x, 100 + z*x);
        
        draw.setTitle("Here is your view");
        draw.setScene(scene);
        draw.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    public void show(String[] args){
        launch(args);
    }
}
