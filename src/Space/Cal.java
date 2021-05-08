//package Space;

import java.util.ArrayList;
public class Cal {    //Calculate class
    //Get distance between 2 point
    public Double PointDistance(Points A, Points B){ 
        Vector v = new Vector(A,B);
        return v.Distance();
    }
    //Get distance from point to Plane
    public Double PointToPlane(Points A, Plane P){
        double dis = Math.sqrt(P.getA()*P.getA()+P.getB()*P.getB()+P.getC()*P.getC());
        return Math.abs(P.getA()*A.getX()+P.getB()*A.getY()+P.getC()*A.getZ()+P.getD())/dis;
    }
    //check points in line
    public Boolean IsInLine(Points A, Lines d){
        double t = (A.getX() - d.getXo())/d.getA();
        double y = d.getB()*t + d.getYo();
        double z = d.getC()*t + d.getZo();
        if(y == A.getY() && z == A.getZ()){ //if has t x = xo+at,y=yo+bt,z=zo+ct
            return true;
        }else{
            return false;
        }
    }
    //check points in plane
    public Boolean IsInPlane(Points A,Plane P){
        if(PointToPlane(A,P) == 0){
            return true;
        }else{
            return false;
        }
    }
    //x = b1c2 - b2c1, y = c1a2 - c2a1, z = a1b2 - a2b1
    public Vector DirectedVec(Vector v1, Vector v2){ 
        double x = v1.getVy()*v2.getVz() - v2.getVy()*v2.getVz();
        double y = v1.getVz()*v2.getVx() - v2.getVz()*v1.getVx();
        double z = v1.getVx()*v2.getVy() - v2.getVx()*v1.getVy();
        Vector v = new Vector(x,y,z);
        return v;
    }
    //v1*v2 = x1x2 + y1y2 + z1z2
    public Double ScalarVec(Vector v1, Vector v2){ 
        return v1.getVx()*v2.getVx()+v1.getVy()*v2.getVy()+v1.getVz()*v2.getVz();
    }
    //Get relative position of 2 lines
    public Integer RelPosLine(Lines a, Lines b){
        Points A = new Points(a.getXo(),a.getYo(),a.getZo());
        Points B = new Points(b.getXo(),b.getYo(),b.getZo());
        Vector AB = new Vector(A,B);
        Vector u1 = new Vector(a.getA(),a.getB(),a.getC());
        Vector u2 = new Vector(b.getA(),b.getB(),b.getC());
        Vector Dir = DirectedVec(u1,u2);
        //[u1,u2] = 0 -> paralle or concident
        if(Dir.Distance() == 0){
            if(IsInLine(A,b)){
                return 0; //"parallel"
            }else{
                return 1; //"concident"
            }
        }else{
            //[u1,u2].AB = 0 -> intersect else no-coplanar
            if(ScalarVec(Dir,AB) == 0){
                return -1; //"intersect";
            }else{
                return -2; //"no-coplanar";
            }
        }
    }
    //relative between line and plane
    //A(xo + at) + B(yo + bt) + C(zo+ct) + D = 0
    //(Aa + Bb + Cc)t + (Axo + Byo + Czo + D) = 0
    public int RelLineToPlane(Lines d, Plane P){
        double a = P.getA()*d.getA()+P.getB()*d.getB()+P.getC()*d.getC();
        double b = P.getA()*d.getXo()+P.getB()*d.getYo()+P.getC()+d.getZo() + P.getD();
        if(a == 0){
            if(b == 0){
                return -1;//d in P
            }else{
                return 1;//d // P
            }
        }else{
            return 0; // d intersect P
        }
    }
    //get intersect point
    public Points GetIntPoint(Lines d, Plane P){
        double a = P.getA()*d.getA()+P.getB()*d.getB()+P.getC()*d.getC();
        double b = P.getA()*d.getXo()+P.getB()*d.getYo()+P.getC()+d.getZo() + P.getD();
        if(RelLineToPlane(d,P) == 0){
            double t = -b/a;
            Points A = new Points(d.getXo()+d.getA()*t, d.getYo()+d.getB()*t,d.getZo()+d.getC()*t);
            return A;
        }else{
            return null;
        }
    }
    //get Area of Triangle
    public Double GetTriArea(ArrayList<Points> p){
        Points A = p.get(0);
        Points B = p.get(1);
        Points C = p.get(2);
        double AB = PointDistance(A,B);
        double BC = PointDistance(B,C);
        double CA = PointDistance(C,A);
        double peri = (AB+BC+CA)/2; //half of perimeter
        return Math.sqrt(peri*(peri - AB)*(peri - BC)*(peri - CA));//herong 
    }
    //get Area of Quadrilateral
    public Double GetQuaAre(ArrayList<Points> p){
        Points t = p.remove(0);
        ArrayList<Points> S1 = p;
        p.add(t);
        t = p.remove(1);
        ArrayList<Points> S2 = p;
        p.add(t);
        t = p.remove(2);
        ArrayList<Points> S3 = p;
        p.add(t);
        t = p.remove(3);
        ArrayList<Points> S4 = p;
        return (GetTriArea(S1) + GetTriArea(S2) + GetTriArea(S3) + GetTriArea(S4))/2;
    }
    //get Volume of pyramid
    public Double GetVolPyr(ArrayList<Points> p,Points S){
        Plane P = new Plane(p.get(0),p.get(1),p.get(2));
        if(IsInPlane(S,P)){
            return 0.0;
        }else{
            if(p.size() == 3){
                return PointToPlane(S,P)*GetTriArea(p)/3;
            }else{
                return PointToPlane(S,P)*GetQuaAre(p)/3;
            }
        }
    }
}
