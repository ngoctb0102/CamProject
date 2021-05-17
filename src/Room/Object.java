//package Room;
//import Space.*;
import java.util.ArrayList;
public class Object {
    Cal cal = new Cal();
    Points A;
    Points B;
    Points C;
    Points D;
    Points A1;
    Points B1;
    Points C1;
    Points D1;
    private ArrayList<Points> po; //list of object's apex
    private double h;
    public Points getA() {
        return A;
    }
    public void setA(Points a) {
        A = a;
    }
    public Points getB() {
        return B;
    }
    public void setB(Points b) {
        B = b;
    }
    public Points getC() {
        return C;
    }
    public void setC(Points c) {
        C = c;
    }
    public Points getD() {
        return D;
    }
    public void setD(Points d) {
        D = d;
    }
    public ArrayList<Points> getPo() {
        return po;
    }
    public void setPo(ArrayList<Points> po) {
        this.po = po;
    }
    public double getH() {
        return h;
    }
    public void setH(double h) {
        this.h = h;
    }
    public Object(Points a, Points b, Points c, Points d, double h) {
        A = a;
        B = b;
        C = c;
        D = d;
        this.h = h;
        A1 = new Points(A.getX(),A.getY(),A.getZ()+h);
        B1 = new Points(B.getX(),B.getY(),B.getZ()+h);
        C1 = new Points(C.getX(),C.getY(),C.getZ()+h);
        D1 = new Points(D.getX(),D.getY(),D.getZ()+h);
        po.add(A); po.add(A1);
        po.add(B); po.add(B1);
        po.add(C); po.add(C1);
        po.add(D); po.add(D1);
    }
    //ccheck where is object
    public boolean checkPosObj(double z){
        double a = A.getZ();
        double b = B.getZ();
        double c = C.getZ();
        double d = D.getZ();
        if(a == z && b == z && c == z && d == z){
            return true;
        }else{
            return false;
        }
    }
    //check if point in object
    public boolean checkInObj(Points m){
        ArrayList<Points> p = new ArrayList<Points>();
        p.add(A);
        p.add(B);
        p.add(C);
        p.add(D);
        double v = cal.GetQuaAre(p)*h;
        ArrayList<Points> p1 = new ArrayList<Points>();
        p1.add(A); p1.add(B); p1.add(C); p1.add(D); // ABCD
        ArrayList<Points> p2 = new ArrayList<Points>();
        p2.add(A1); p2.add(B1); p2.add(C1); p2.add(D1); //A1B1C1D1
        ArrayList<Points> p3 = new ArrayList<Points>();
        p3.add(A); p3.add(A1); p3.add(B); p3.add(B1); //ABA1B1
        ArrayList<Points> p4 = new ArrayList<Points>();
        p4.add(A); p4.add(A1); p4.add(D); p4.add(D1); //ADA1D1
        ArrayList<Points> p5 = new ArrayList<Points>();
        p5.add(C); p5.add(C1); p5.add(B); p5.add(B1); //CBC1D1
        ArrayList<Points> p6 = new ArrayList<Points>();
        p6.add(C); p6.add(C1); p6.add(D); p6.add(D1); //CDC1D1
        double v1 = cal.GetVolPyr(p1, m);
        double v2 = cal.GetVolPyr(p2, m);
        double v3 = cal.GetVolPyr(p3, m);
        double v4 = cal.GetVolPyr(p4, m);
        double v5 = cal.GetVolPyr(p5, m);
        double v6 = cal.GetVolPyr(p6, m);
        double vM = v1 + v2 + v3 + v4 + v5 + v6;
        if((double) Math.round(v*1000)/1000 == (double) Math.round(vM*1000)/1000){
            return true;
        }else{
            return false;
        }
    }
    //check 2 object to see they are intersect or not
    public boolean checkObInOb(Object o){
        for(int i = 0;i < o.getPo().size();i++){
            if(checkInObj(o.getPo().get(i))){
                return false;
            }
        }
        for(int i = 0;i<po.size();i++){
            if(o.checkInObj(po.get(i))){
                return false;
            }
        }
        return true;
    }
    //check if object o on object ?
    public int checkInputObj(Object o){
        if(!checkObInOb(o)){
            return 1;//outsize
        }else{
            Plane P = new Plane(A1,B1,C1);
            if(cal.IsInPlane(o.A, P)){
                return -1; //on
            }else{
                return 0; //in
            }
        }
    }
}
