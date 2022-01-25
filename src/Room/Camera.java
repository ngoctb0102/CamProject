import java.util.ArrayList;

//package Camera;

//import Space.*;
public class Camera extends Points{
    //get angle in radian
    Cal cal = new Cal();
    private double hAngle; 
    private double lAngle;
    public double gethAngle() {
        return hAngle;
    }
    public void sethAngle(double hAngle) {
        this.hAngle = hAngle;
    }
    public double getlAngle() {
        return lAngle;
    }
    public void setlAngle(double lAngle) {
        this.lAngle = lAngle;
    }
    private double Lenght;
    Points t1 = new Points();
    Points t2 = new Points(); // 2 points on top
    Points b1 = new Points(); // 2 points under top 
    Points b2 = new Points();
    public double getLenght() {
        return Lenght;
    }
    public Camera(double x, double y, double z) {
        super(x, y, z);
    }
    public void setLenght(double lenght) {
        Lenght = lenght;
    }
    public Camera(double x, double y, double z, double hangle, double langle, double lenght) {
        super(x, y, z);
        hAngle = Math.toRadians(hangle/2); 
        lAngle = Math.toRadians(langle/2);
        Lenght = lenght;
    }
    public Camera(double x, double y, double z, double hangle, double langle){
        super(x, y, z);
        hAngle = Math.toRadians(hangle/2); 
        lAngle = Math.toRadians(langle/2);
        Lenght = 1000;
    }
    public Camera(double x, double y, double z) {
        super(x, y, z);
    }
    // public Camera(String s){
    //     String[] a = s.split(" ");
    //     String[] arg = a[0].replace(")", "").replace("(", "").split(",");
    //     double x = Double.parseDouble(arg[0]);
    //     double y = Double.parseDouble(arg[1]);
    //     double z = Double.parseDouble(arg[2]);
    //     this.x = x;
    //     this.y = y;
    //     this.z = z;
    //     hAngle = Math.toRadians(Double.parseDouble(a[1]));
    //     lAngle = Math.toRadians(Double.parseDouble(a[2]));
    //     Lenght = 1000;
    // }
    public boolean checkInCam(Points a){
        Points S = new Points(this.x,this.y,this.z);
        //S.print();
        ArrayList<Points> p = new ArrayList<Points>();
        p.add(t1);
        p.add(t2);
        p.add(b1);
        p.add(b2);
        //V of camera
        double v = cal.GetVolPyr(p, S);
        // System.out.println(v);
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
        // System.out.println(v1 + " " + v2 + " " + v3 + " " + v4 + " "+v5+" "+ vS);
        return (double) Math.round(v*1000)/1000 == (double) Math.round(vS*1000)/1000;
    }
    public boolean checkIsOne(Camera c){
        return c.getX() == getX() && c.getY() == getY() && c.getZ() == getZ();
    }
    public boolean isInList(ArrayList<Camera> cam){
        if(cam.size() == 0){
            return true;
        }else{
            for(int i = 0;i < cam.size();i++){
                if(checkIsOne(cam.get(i))){
                    return false;
                }
            }
            return true;
        }
    }
}
