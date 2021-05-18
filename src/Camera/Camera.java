import java.util.ArrayList;

//package Camera;

//import Space.*;
public class Camera extends Points{
    //get angle in radian
    Cal cal = new Cal();
    private double Angle; 
    private double Lenght;
    Points t1 = new Points();
    Points t2 = new Points(); // 2 points on top
    Points b1 = new Points(); // 2 points under top 
    Points b2 = new Points();
    public double getAngle() {
        return Angle;
    }
    public void setAngle(int angle) {
        Angle = angle;
    }
    public double getLenght() {
        return Lenght;
    }
    public void setLenght(double lenght) {
        Lenght = lenght;
    }
    public Camera(double x, double y, double z, double angle, double lenght) {
        super(x, y, z);
        Angle = Math.toRadians(angle/2); 
        Lenght = lenght;
    }
    public boolean checkInCam(Points a){
        Points S = new Points(this.x,this.y,this.z);
        S.print();
        ArrayList<Points> p = new ArrayList<Points>();
        p.add(t1);
        p.add(t2);
        p.add(b1);
        p.add(b2);
        //V of camera
        double v = cal.GetVolPyr(p, S);
        System.out.println(v);
        ArrayList<Points> p1 = new ArrayList<Points>();
        ArrayList<Points> p2 = new ArrayList<Points>();
        ArrayList<Points> p3 = new ArrayList<Points>();
        ArrayList<Points> p4 = new ArrayList<Points>();
        p1.add(t1); p1.add(t2); p1.add(S);
        p2.add(t1); p2.add(b1); p2.add(S);
        p3.add(t2); p3.add(b2); p3.add(S);
        p4.add(b1); p4.add(b2); p4.add(S);
        double v1 = cal.GetVolPyr(p1, a);
        double v2 = cal.GetVolPyr(p2, a);
        double v3 = cal.GetVolPyr(p3, a);
        double v4 = cal.GetVolPyr(p4, a);
        double v5 = cal.GetVolPyr(p, a);
        double vS = v1 + v2 + v3 + v4 + v5;
        System.out.println(v1 + " " + v2 + " " + v3 + " " + v4 + " "+v5+" "+ vS);
        if((double) Math.round(v*1000)/1000 == (double) Math.round(vS*1000)/1000){
            return true;
        }else{
            return false;
        }
    }
}
