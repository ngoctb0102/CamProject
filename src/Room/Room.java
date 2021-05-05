package Room;
import Space.*;
import java.lang.*;
import java.util.ArrayList;

import Camera.Camera;
public class Room {
    Cal cal = new Cal();
    private double l;
    private double w;
    private double h;
    private ArrayList<Camera> Cams = new ArrayList<Camera>();
    private ArrayList<Object> Objs = new ArrayList<Object>();
    private final Plane Bot = new Plane(0,0,1,0); //z = 0
    private final Plane Left = new Plane(1,0,0,0); //x = 0
    private final Plane Right = new Plane(1,0,0,-w); //x = w 
    private final Plane Behind = new Plane(0,1,0,0); //y = 0
    private final Plane Front = new Plane(0,1,0,-l); //y = l
    private final Plane Top = new Plane(0,0,1,-h);//z = h 
    public Room(double l, double w, double h) {
        this.l = l;
        this.w = w;
        this.h = h;
    }
    //add camera to Room
    public void addCam(Camera c){
        if(cal.IsInPlane(c,Top)){
            Cams.add(c);
        }
    }
    //add Obj to Room
    public void addObj(Object o){
        Objs.add(o);
    }
}
