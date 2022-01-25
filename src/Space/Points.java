//package Space;

public class Points{
    protected double x; //1 point in Oxyz have 3 dimension (x,y,z)
    protected double y; 
    protected double z;
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
    public void print(){
        System.out.print(x + " " + y + " " + z); 
    }
    // public Points(String s){
    //     System.out.println(s);
    //     String[] a = s.replace(")", "").replace("(", "").split(",");
        
    //     this.x = Double.parseDouble(a[0]);
    //     this.y = Double.parseDouble(a[1]);
    //     this.z = Double.parseDouble(a[2]);
    // }
}