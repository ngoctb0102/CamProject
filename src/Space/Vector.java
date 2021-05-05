package Space;

// import java.lang.Math;
public class Vector{
    private double vx;
    private double vy;
    private double vz; // vector have 3 para
    public double getVx() {
        return vx;
    }
    public void setVx(double vx) {
        this.vx = vx;
    }
    public double getVy() {
        return vy;
    }
    public void setVy(double vy) {
        this.vy = vy;
    }
    public double getVz() {
        return vz;
    }
    public void setVz(double vz) {
        this.vz = vz;
    }
    public Vector(double vx, double vy, double vz) { //vector A (x,y,z)
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;
    }
    public Vector(Points A, Points B){ //vA = AB = (xB-xA;yB-yA;zB-zA)
        this.vx = B.getX() - A.getX();
        this.vy = B.getY() - A.getY();
        this.vz = B.getZ() - A.getZ();
    }
    public double Distance(){
        return Math.sqrt(vx*vx + vy*vy + vz*vz);
    }
}
