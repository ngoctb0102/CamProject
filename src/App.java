import java.util.ArrayList;
public class App {
    public static void main(String[] args) throws Exception {
        Room r = new Room(20.0,20.0,20.0);
        Camera c = new Camera(13,0,20,60,60);
        r.addCam(c);
        Camera c1 = new Camera(7,0,20,60,60);
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
        //System.out.println(r.IsLight(new Points(18,0,0)));
        //System.out.println(r.perLighter() + "%");
        //System.out.println(r.lBot().size());
        getDraw g = new getDraw(r,4);
        g.show();
    }
}
