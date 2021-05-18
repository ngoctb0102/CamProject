//package Space;
import java.util.ArrayList;
public class test {
    public static void main(String[] args) {
        Cal c = new Cal();
        Points A = new Points(0,0,0);
        Points B = new Points(0,1,0);
        Points C = new Points(1,0,0);
        Points D = new Points(1,1,0);
        Points S = new Points(1,2,3);
        Plane P = new Plane(A,B,C);
        
        Plane Top = new Plane(0,0,1,-100.0);
        Camera cam = new Camera(0.0,0.0,100.0,60.0,50.0);
        System.out.println(c.IsInPlane(cam,Top));
    }
}
