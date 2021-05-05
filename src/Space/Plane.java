package Space;

public class Plane { //Ax+By+Cz+D = 0
    private double A;
    private double B;
    private double C;
    private double D;
    public double getA() {
        return A;
    }
    public void setA(double a) {
        A = a;
    }
    public double getB() {
        return B;
    }
    public void setB(double b) {
        B = b;
    }
    public double getC() {
        return C;
    }
    public void setC(double c) {
        C = c;
    }
    public double getD() {
        return D;
    }
    public void setD(double d) {
        D = d;
    }
    public Plane(double a, double b, double c, double d) {
        A = a;
        B = b;
        C = c;
        D = d;
    }
    public Plane(Points A, Vector v){
        this.A = v.getVx();
        this.B = v.getVy();
        this.C = v.getVz();
        this.D = -A.getX()*v.getVx() - A.getY()*v.getVy() - A.getZ()*v.getVz(); 
    }
}
