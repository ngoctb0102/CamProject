package Camera;

import Space.*;
import java.util.ArrayList;
public class Camera extends Points{
    private int Angle; 
    private double Lenght;
    public int getAngle() {
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
    Cal c = new Cal();
    //check a point if it's in the camerazone
    public boolean isCameraZone(Points aPoint, ArrayList<Points> p){
            Arraylist<Points> p1 = new ArrayList<Points>();
            Arraylist<Points> p2 = new ArrayList<Points>();
            Arraylist<Points> p3 = new ArrayList<Points>();
            Arraylist<Points> p4 = new ArrayList<Points>();
            Arraylist<Points> p5 = new ArrayList<Points>();
           p1.add(p.get(0));    p1.add(p.get(1));   p1.add(p.get(2));
           p2.add(p.get(0));    p2.add(p.get(2));   p2.add(p.get(3));
           p3.add(p.get(0));    p3.add(p.get(3));   p3.add(p.get(4));
           p4.add(p.get(0));    p4.add(p.get(4));   p4.add(p.get(1));
           p5.add(p.get(1));    p5.add(p.get(2));   p5.add(p.get(3));   p5.add(p.get(4));
           double camVolume = c.GetVolPyr(p5, p.get(0));
           double sumVolume = c.GetVolPyr(p1, aPoint) + c.GetVolPyr(p2, aPoint) + c.GetVolPyr(p3, aPoint) + c.GetVolPyr(p4, aPoint) + c.GetVolPyr(p5, aPoint);
           //lam tron gia tri
           double a = (double)Math.round(camVolume * 100)/100;
           double b = (double)Math.round(sumVolume * 100)/100;
           if(a == b){
               return true;
           }
           return false;
    }

}