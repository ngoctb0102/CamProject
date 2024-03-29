//package Room;
//import Space.*;
import java.util.ArrayList;

import javafx.scene.shape.Line;
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
    private ArrayList<Points> po = new ArrayList<Points>(); //list of object's apex
    private double h;
    ArrayList<Points> p1 = new ArrayList<Points>();
    Plane P1;
    ArrayList<Points> p2 = new ArrayList<Points>();
    Plane P2;
    ArrayList<Points> p3 = new ArrayList<Points>();
    Plane P3;
    ArrayList<Points> p4 = new ArrayList<Points>();
    Plane P4;
    ArrayList<Points> p5 = new ArrayList<Points>();
    Plane P5;
    ArrayList<Points> p6 = new ArrayList<Points>();
    Plane P6;
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
        Vector ab =  new Vector(a,b);
        Vector ac = new Vector(a,c);
        Vector ad =  new Vector(a,d);
        if(cal.ScalarVec(ab, ac) == 0){
            A = a;
            B = b;
            C = d;
            D = c;
        }else if(cal.ScalarVec(ab,ad) == 0){
            A = a;
            B = b;
            C = c;
            D = d;
        }else if(cal.ScalarVec(ac,ad) == 0){
            A = a;
            B = c;
            C = b;
            D = d;
        }else{
            throw new  IllegalArgumentException("invalid input object");
        }
        this.h = h;
        A1 = new Points(A.getX(),A.getY(),A.getZ()+h);
        B1 = new Points(B.getX(),B.getY(),B.getZ()+h);
        C1 = new Points(C.getX(),C.getY(),C.getZ()+h);
        D1 = new Points(D.getX(),D.getY(),D.getZ()+h);
        po.add(A); po.add(A1);
        po.add(B); po.add(B1);
        po.add(C); po.add(C1);
        po.add(D); po.add(D1);
        p1.add(A); p1.add(B); p1.add(C); p1.add(D); // ABCD
        P1 = new Plane(A,B,C);
        p2.add(A1); p2.add(B1); p2.add(C1); p2.add(D1); //A1B1C1D1
        P2 = new Plane(A1,B1,C1);
        p3.add(A); p3.add(A1); p3.add(B); p3.add(B1); //ABA1B1
        P3 = new Plane(A,A1,B);
        p4.add(A); p4.add(A1); p4.add(D); p4.add(D1); //ADA1D1
        P4 = new Plane(A,A1,D);
        p5.add(C); p5.add(C1); p5.add(B); p5.add(B1); //CBC1D1
        P5 = new Plane(C,C1,B);
        p6.add(C); p6.add(C1); p6.add(D); p6.add(D1); //CDC1D1
        P6 = new Plane(C,C1,D);
    }
    public Object(Points a, Points b, Points c, Points d, Points e, Points f, Points g, Points h){
        Plane bot = new Plane(a,b,c);
        double he = cal.PointToPlane(e, bot);
        Vector ab =  new Vector(a,b);
        Vector ac = new Vector(a,c);
        Vector ad =  new Vector(a,d);
        if(cal.ScalarVec(ab, ac) == 0){
            A = a;
            B = b;
            C = d;
            D = c;
        }else if(cal.ScalarVec(ab,ad) == 0){
            A = a;
            B = b;
            C = c;
            D = d;
        }else if(cal.ScalarVec(ac,ad) == 0){
            A = a;
            B = c;
            C = b;
            D = d;
        }else{
            throw new  IllegalArgumentException("invalid input object");
        }
        
        this.h = he;
        A1 = new Points(A.getX(),A.getY(),A.getZ()+he);
        B1 = new Points(B.getX(),B.getY(),B.getZ()+he);
        C1 = new Points(C.getX(),C.getY(),C.getZ()+he);
        D1 = new Points(D.getX(),D.getY(),D.getZ()+he);
        po.add(A); po.add(A1);
        po.add(B); po.add(B1);
        po.add(C); po.add(C1);
        po.add(D); po.add(D1);
        p1.add(A); p1.add(B); p1.add(C); p1.add(D); // ABCD
        P1 = new Plane(A,B,C);
        p2.add(A1); p2.add(B1); p2.add(C1); p2.add(D1); //A1B1C1D1
        P2 = new Plane(A1,B1,C1);
        p3.add(A); p3.add(A1); p3.add(B); p3.add(B1); //ABA1B1
        P3 = new Plane(A,A1,B);
        p4.add(A); p4.add(A1); p4.add(D); p4.add(D1); //ADA1D1
        P4 = new Plane(A,A1,D);
        p5.add(C); p5.add(C1); p5.add(B); p5.add(B1); //CBC1D1
        P5 = new Plane(C,C1,B);
        p6.add(C); p6.add(C1); p6.add(D); p6.add(D1); //CDC1D1
        P6 = new Plane(C,C1,D);
    }
    //ccheck where is object
    public boolean checkPosObj(double z){
        double a = A.getZ();
        double b = B.getZ();
        double c = C.getZ();
        double d = D.getZ();
        return a == z && b == z && c == z && d == z;
    }
    //check if point in object
    public boolean checkInObj(Points m){
        double v = cal.GetQuaAre(p1)*h;
        double v1 = cal.GetVolPyr(p1, m);
        double v2 = cal.GetVolPyr(p2, m);
        double v3 = cal.GetVolPyr(p3, m);
        double v4 = cal.GetVolPyr(p4, m);
        double v5 = cal.GetVolPyr(p5, m);
        double v6 = cal.GetVolPyr(p6, m);
        // System.out.println(v);
        // System.out.println(v1);
        // System.out.println(v2);
        // System.out.println(v3);
        // System.out.println(v4);
        // System.out.println(v5);
        // System.out.println(v6);
        double vM = v1 + v2 + v3 + v4 + v5 + v6;
        // System.out.println(vM);
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
                System.out.println("in other");
                return true;
            }
        }
        for(int i = 0;i<po.size();i++){
            if(o.checkInObj(po.get(i))){
                System.out.println("in other");
                return true;
            }
        }
        return false;
    }
    //check if object o on object ?
    public int checkInputObj(Object o){
        if(!checkObInOb(o)){
            // System.out.println("in other 1");
            return 1;//outsize
        }else{
            Plane P = new Plane(A1,B1,C1);
            if(cal.IsInPlane(o.A, P)){
                // System.out.println("in other - 1");
                return -1; //on
            }else{
                // System.out.println("in other 0");
                return 0; //in
            }
        }
    }
    //check if point in objplane
    public boolean checkOnObjPlane(Points a){
        return cal.GetVolPyr(p4, a) == 0.0 || cal.GetVolPyr(p1, a) == 0.0 || cal.GetVolPyr(p2,a) == 0.0 || 
        cal.GetVolPyr(p3, a) == 0.0 || cal.GetVolPyr(p5,a) == 0.0 || cal.GetVolPyr(p6, a) == 0.0;
    }
    //check if two points is hidden by object
    public boolean checkTwoPo(Points a, Points b){
        Lines d = new Lines(a,b);
        ArrayList<Points> h = new ArrayList<Points>();
        h.add(cal.GetIntPoint(d, P1));
        h.add(cal.GetIntPoint(d, P2));
        h.add(cal.GetIntPoint(d, P3));
        h.add(cal.GetIntPoint(d, P4));
        h.add(cal.GetIntPoint(d, P5));
        h.add(cal.GetIntPoint(d, P6));
        double ab = cal.PointDistance(a, b);
        for(int i = 0;i <h.size();i++){
            if(h.get(i)!=null){
                if(checkInObj(h.get(i))){
                    double dab = cal.PointDistance(a, h.get(i)) + cal.PointDistance(b, h.get(i));
                    if((double) Math.round(ab*100)/100 == (double) Math.round(dab*100)/100){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
