package Space;

public class Points{
    private double x; //1 point in Oxyz have 3 dimension (x,y,z)
    private double y; 
    private double z;
    private boolean state; //state = true : light || state = false : dark
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public double getZ() {
        return z;
    }
    public void setZ(double z) {
        this.z = z;
    }
    public Points(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public boolean isState() {
        return state;
    }
    public void setState(boolean state) {
        this.state = state;
    }
    public Points() {
    } 
}