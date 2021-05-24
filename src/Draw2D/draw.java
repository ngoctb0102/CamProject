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

public class draw extends Application{
    String[] s1;
    String[] s2;
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
    }
    public void start(Stage draw) throws Exception{
        //super.init();
        
        Group root = new Group();
        //set full plane is dark
        // System.out.println(getX());
        //x = getParameters().get
        double x = Double.parseDouble(s1[0]);
        double y = Double.parseDouble(s1[1]);
        Rectangle re = new Rectangle(5*10,5*10,x*10,y*10);
        root.getChildren().add(re);
        for(int i = 0;i<s2.length;i+=2){
            Line line = new Line((Double.parseDouble(s2[i])+5)*10,(Double.parseDouble(s2[i+1])+5)*10,(Double.parseDouble(s2[i])+5)*10,(Double.parseDouble(s2[i+1])+5)*10);
            line.setStroke(Color.WHITE);
            root.getChildren().add(line);
        }
        Line l1 = new Line(5*10,5*10,(5+x)*10,5*10);
        Line l2 = new Line((5+x)*10,5*10,(5+x)*10,(5+y)*10);
        Line l3 = new Line((5+x)*10,(5+y)*10,5*10,(5+y)*10);
        Line l4 = new Line(5*10,(5+y)*10,5*10,5*10);
        root.getChildren().add(l1);
        root.getChildren().add(l2);
        root.getChildren().add(l3);
        root.getChildren().add(l4);
        
        
        
        Scene scene = new Scene(root, 100 + 10*x, 100 + 10*x);
        
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
