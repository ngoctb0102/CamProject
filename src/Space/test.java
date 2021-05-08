//package Space;
import java.util.ArrayList;
public class test {
    public static void main(String[] args) {
        Cal c = new Cal();
        ArrayList<Points> p = new ArrayList<Points>();
        ArrayList<Points> s = new ArrayList<Points>();
        Points A = new Points(0,0,0);
        Points B = new Points(0,1,0);
        Points C = new Points(1,0,0);
        Points D = new Points(1,1,0);
        Points S = new Points(1,2,3);
        p.add(A);
        p.add(B);
        p.add(C);
        s = p;
        s.add(D);
        System.out.println(c.GetTriArea(p));
        System.out.println(c.GetQuaAre(s));
        System.out.println(c.GetVolPyr(p, S));
        Vector a = new Vector(A,B);
        System.out.println(a.Distance());
    }
}
