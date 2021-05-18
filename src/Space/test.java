//package Space;
import java.util.ArrayList;
public class test {
    public static void main(String[] args) {
        Cal c = new Cal();
        Points A = new Points(0,0,0);
        Points B = new Points(0,1,0);
        Points C = new Points(1,0,0);
        Points D = new Points(1,1,0);
        Points S = new Points(1,2,5);
        Plane P = new Plane(A,B,C);
        ArrayList<Points> p = new ArrayList<Points>();
        p.add(A); p.add(B); p.add(C); p.add(D);
        System.out.println(c.GetVolPyr(p, S));
    }
}
