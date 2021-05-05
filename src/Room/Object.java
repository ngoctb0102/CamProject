package Room;
import Space.*;
public class Object {
    Points A;
    Points B;
    Points C;
    Points D;
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
    }
}
