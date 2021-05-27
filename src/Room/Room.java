//package Room;
//import Space.*;
import java.lang.*;
import java.util.*;
import java.util.ArrayList;

//import Camera.Camera;
public class Room {
    Cal cal = new Cal();
    private double l;
    private double w;
    private double h;
    public double getL() {
        return l;
    }
    public void setL(double l) {
        this.l = l;
    }
    public double getW() {
        return w;
    }
    public void setW(double w) {
        this.w = w;
    }
    public double getH() {
        return h;
    }
    public void setH(double h) {
        this.h = h;
    }
    //list of camera
    private ArrayList<Camera> Cams = new ArrayList<Camera>();
    public ArrayList<Camera> getCams() {
        return Cams;
    }
    public void setCams(ArrayList<Camera> cams) {
        Cams = cams;
    }
    //list of object
    private ArrayList<Object> Objs = new ArrayList<Object>();
    public ArrayList<Object> getObjs() {
        return Objs;
    }
    public void setObjs(ArrayList<Object> objs) {
        Objs = objs;
    }
    private Plane Bot; //z = 0
    private Plane Left; //x = 0
    private Plane Right; //x = w 
    private Plane Behind; //y = 0
    private Plane Front; //y = l
    private Plane Top;//z = h 
    //get number of light point :>
    // private ArrayList<Points> lLeft = new ArrayList<Points>();
    // private ArrayList<Points> lRight = new ArrayList<Points>();
    // private ArrayList<Points> lBehind = new ArrayList<Points>();
    // private ArrayList<Points> lFront = new ArrayList<Points>();
    public Room(double w, double l, double h) {
        this.l = l;
        this.w = w;
        this.h = h;
        Bot = new Plane(0,0,1,0); //z = 0
        Left = new Plane(1,0,0,0); //x = 0
        Right = new Plane(1,0,0,0.0-w); //x = w 
        Behind = new Plane(0,1,0,0); //y = 0
        Front = new Plane(0,1,0,0.0-l); //y = l
        Top = new Plane(0,0,1,0.0-h);//z = h 
    }
    public Room(Points a, Points b, Points c, Points d, Points e, Points f, Points g, Points h){
        List<Double> width = new ArrayList<Double>(); 
        width.add(a.getX()); width.add(b.getX()); width.add(c.getX()); width.add(d.getX());
        width.add(e.getX()); width.add(f.getX()); width.add(g.getX()); width.add(h.getX());
        double wi = Collections.max(width);
        List<Double> lenght = new ArrayList<Double>();
        lenght.add(a.getY()); lenght.add(b.getY()); lenght.add(c.getY()); lenght.add(d.getY());
        lenght.add(e.getY()); lenght.add(f.getY()); lenght.add(g.getY()); lenght.add(h.getY());
        double le = Collections.max(lenght);
        List<Double> height = new ArrayList<Double>();
        height.add(a.getZ()); height.add(b.getZ()); height.add(c.getZ()); height.add(d.getZ());
        height.add(e.getZ()); height.add(f.getZ()); height.add(g.getZ()); height.add(h.getZ());
        double he = Collections.max(height);
        this.l = le;
        this.w = wi;
        this.h = he;
        Bot = new Plane(0,0,1,0); //z = 0
        Left = new Plane(1,0,0,0); //x = 0
        Right = new Plane(1,0,0,0.0-wi); //x = w 
        Behind = new Plane(0,1,0,0); //y = 0
        Front = new Plane(0,1,0,0.0-le); //y = l
        Top = new Plane(0,0,1,0.0-he);//z = h
    }
    //add camera to Room
    public void addCam(Camera c){
        if(CheckCam(c) != 0){
            if(checkInR(c)){
                if(c.isInList(Cams)){
                    Cams.add(c);
                    System.out.println("Added Camera");
                    CamVision(c);
                }
            }
        }
    }
    //add Obj to Room
    public void addObj(Object o){
        if(checkObj(o)){
            System.out.println("Added Obj");
            Objs.add(o);
        }
    }
    //check where the camera is ?
    public int CheckCam(Camera c){
        if(!cal.IsInPlane(c,Top)){
            if(cal.IsInPlane(c,Left)){
                if(cal.IsInPlane(c,Behind)){
                    return 11; //if in left and behind
                }else if(cal.IsInPlane(c,Front)){
                    return 13; //if in left and front
                }else{
                    return 12;//if only in left
                }
            }else if(cal.IsInPlane(c,Front)){
                if(cal.IsInPlane(c,Right)){
                    return 15;//if in front and right
                }else{
                    return 14;//if only in front
                }
            }else if(cal.IsInPlane(c,Right)){
                if(cal.IsInPlane(c, Behind)){
                    return 17;//if in right anf behind
                }else{
                    return 16;//only in right
                }
            }else if(cal.IsInPlane(c,Behind)){
                return 18;//only in behind
            }else{
                return 0;//error position
            } //if camera is not in top return 0
        }else{
            if(cal.IsInPlane(c,Left)){
                if(cal.IsInPlane(c,Behind)){
                    return 1; //if in left and behind
                }else if(cal.IsInPlane(c,Front)){
                    return 3; //if in left and front
                }else{
                    return 2;//if only in left
                }
            }else if(cal.IsInPlane(c,Front)){
                if(cal.IsInPlane(c,Right)){
                    return 5;//if in front and right
                }else{
                    return 4;//if only in front
                }
            }else if(cal.IsInPlane(c,Right)){
                if(cal.IsInPlane(c, Behind)){
                    return 7;//if in right anf behind
                }else{
                    return 6;//only in right
                }
            }else if(cal.IsInPlane(c,Behind)){
                return 8;//only in behind
            }else{
                return 0;//error position
            }
        }
    }
    //get cam vision in room
    private void CamVision(Camera c){
        //in left x = 0
        int i = CheckCam(c);
        if(i == 2){
            //get center of camera
            Points H = new Points(c.getLenght()*Math.cos(c.gethAngle()),c.getY(),h - c.getLenght()*Math.sin(c.gethAngle()));
            //top insection
            Points T = new Points(H.getX(),H.getY(),h);
            //bot plane of camera
            Vector n = new Vector(c,H);
            Plane P = new Plane(H,n);
            //mid top insect
            Lines cT = new Lines(c,T);
            Points mT = cal.GetIntPoint(cT,P);
            //mid bot insect
            Points mB = new Points(2*H.getX() - mT.getX(),2*H.getY() - mT.getY(),2*H.getZ() - mT.getZ());
            //distance from mid too apex
            double d = c.getLenght()*Math.tan(c.getlAngle());
            //get top apex
            Points T1 = new Points(mT.getX(),mT.getY()+d,mT.getZ());
            Points T2 = new Points(mT.getX(),mT.getY()-d,mT.getZ());
            c.t1 = T1;
            c.t2 = T2;
            //get bbot apex
            Points B1 = new Points(mB.getX(),mB.getY()+d,mB.getZ());
            Points B2 = new Points(mB.getX(),mB.getY()-d,mB.getZ());
            c.b1 = B1;
            c.b2 = B2;
        }else if(i == 4){
            Points H = new Points(c.getX(),l - c.getLenght()*Math.cos(c.gethAngle()),h - c.getLenght()*Math.sin(c.gethAngle()));
            //top insection
            Points T = new Points(H.getX(),H.getY(),h);
            //bot plane of camera
            Vector n = new Vector(c,H);
            Plane P = new Plane(H,n);
            //mid top insect
            Lines cT = new Lines(c,T);
            Points mT = cal.GetIntPoint(cT,P);
            //mid bot insect
            Points mB = new Points(2*H.getX() - mT.getX(),2*H.getY() - mT.getY(),2*H.getZ() - mT.getZ());
            //distance from mid too apex
            double d = c.getLenght()*Math.tan(c.getlAngle());
            //get top apex
            Points T1 = new Points(mT.getX()+d,mT.getY(),mT.getZ());
            Points T2 = new Points(mT.getX()-d,mT.getY(),mT.getZ());
            c.t1 = T1;
            c.t2 = T2;
            //get bbot apex
            Points B1 = new Points(mB.getX()+d,mB.getY(),mB.getZ());
            Points B2 = new Points(mB.getX()-d,mB.getY(),mB.getZ());
            c.b1 = B1;
            c.b2 = B2;
        }else if(i == 6){
            Points H = new Points(w - c.getLenght()*Math.cos(c.gethAngle()),c.getY(),h - c.getLenght()*Math.sin(c.gethAngle()));
            //top insection
            Points T = new Points(H.getX(),H.getY(),h);
            //bot plane of camera
            Vector n = new Vector(c,H);
            Plane P = new Plane(H,n);
            //mid top insect
            Lines cT = new Lines(c,T);
            Points mT = cal.GetIntPoint(cT,P);
            //mid bot insect
            Points mB = new Points(2*H.getX() - mT.getX(),2*H.getY() - mT.getY(),2*H.getZ() - mT.getZ());
            //distance from mid too apex
            double d = c.getLenght()*Math.tan(c.getlAngle());
            //get top apex
            Points T1 = new Points(mT.getX(),mT.getY()+d,mT.getZ());
            Points T2 = new Points(mT.getX(),mT.getY()-d,mT.getZ());
            c.t1 = T1;
            c.t2 = T2;
            //get bbot apex
            Points B1 = new Points(mB.getX(),mB.getY()+d,mB.getZ());
            Points B2 = new Points(mB.getX(),mB.getY()-d,mB.getZ());
            c.b1 = B1;
            c.b2 = B2;
        }else if(i == 8){
            Points H = new Points(c.getX(),c.getLenght()*Math.cos(c.gethAngle()),h - c.getLenght()*Math.sin(c.gethAngle()));
            //top insection
            Points T = new Points(H.getX(),H.getY(),h);
            //bot plane of camera
            Vector n = new Vector(c,H);
            Plane P = new Plane(H,n);
            //mid top insect
            Lines cT = new Lines(c,T);
            Points mT = cal.GetIntPoint(cT,P);
            //mid bot insect
            Points mB = new Points(2*H.getX() - mT.getX(),2*H.getY() - mT.getY(),2*H.getZ() - mT.getZ());
            //distance from mid too apex
            double d = c.getLenght()*Math.tan(c.getlAngle());
            //get top apex
            Points T1 = new Points(mT.getX()+d,mT.getY(),mT.getZ());
            Points T2 = new Points(mT.getX()-d,mT.getY(),mT.getZ());
            c.t1 = T1;
            c.t2 = T2;
            //get bbot apex
            Points B1 = new Points(mB.getX()+d,mB.getY(),mB.getZ());
            Points B2 = new Points(mB.getX()-d,mB.getY(),mB.getZ());
            c.b1 = B1;
            c.b2 = B2;
        }else if(i == 1){ //in angle
            double dz = c.getLenght()*Math.sin(c.gethAngle());
            double dxy = c.getLenght()*Math.cos(c.gethAngle())/Math.sqrt(2.0);
            Points H = new Points(c.getX() + dxy,c.getY() + dxy,h - dz);
            //top insection
            Points T = new Points(H.getX(),H.getY(),h);
            //bot plane of camera
            Vector n = new Vector(c,H);
            Plane P = new Plane(H,n);
            //mid top insect
            Lines cT = new Lines(c,T);
            Points mT = cal.GetIntPoint(cT,P);
            //mid bot insect
            Points mB = new Points(2*H.getX() - mT.getX(),2*H.getY() - mT.getY(),2*H.getZ() - mT.getZ());
            //distance from mid too apex
            double d = c.getLenght()*Math.tan(c.getlAngle())/Math.sqrt(2.0);
            //get top apex
            Points T1 = new Points(mT.getX()+d,mT.getY()-d,mT.getZ());
            Points T2 = new Points(mT.getX()-d,mT.getY()+d,mT.getZ());
            c.t1 = T1;
            c.t2 = T2;
            //get bbot apex
            Points B1 = new Points(mB.getX()+d,mB.getY()-d,mB.getZ());
            Points B2 = new Points(mB.getX()-d,mB.getY()+d,mB.getZ());
            c.b1 = B1;
            c.b2 = B2;
        }else if(i == 3){
            double dz = c.getLenght()*Math.sin(c.gethAngle());
            double dxy = c.getLenght()*Math.cos(c.gethAngle())/Math.sqrt(2.0);
            Points H = new Points(c.getX() + dxy,c.getY() - dxy,h - dz);
            //top insection
            Points T = new Points(H.getX(),H.getY(),h);
            //bot plane of camera
            Vector n = new Vector(c,H);
            Plane P = new Plane(H,n);
            //mid top insect
            Lines cT = new Lines(c,T);
            Points mT = cal.GetIntPoint(cT,P);
            //mid bot insect
            Points mB = new Points(2*H.getX() - mT.getX(),2*H.getY() - mT.getY(),2*H.getZ() - mT.getZ());
            //distance from mid too apex
            double d = c.getLenght()*Math.tan(c.getlAngle())/Math.sqrt(2.0);
            //get top apex
            Points T1 = new Points(mT.getX()+d,mT.getY()+d,mT.getZ());
            Points T2 = new Points(mT.getX()-d,mT.getY()-d,mT.getZ());
            c.t1 = T1;
            c.t2 = T2;
            //get bbot apex
            Points B1 = new Points(mB.getX()+d,mB.getY()+d,mB.getZ());
            Points B2 = new Points(mB.getX()-d,mB.getY()-d,mB.getZ());
            c.b1 = B1;
            c.b2 = B2;
        }else if(i == 5){
            double dz = c.getLenght()*Math.sin(c.gethAngle());
            double dxy = c.getLenght()*Math.cos(c.gethAngle())/Math.sqrt(2.0);
            //System.out.println(dz + "  " + dxy);
            Points H = new Points(c.getX() - dxy,c.getY() - dxy,h - dz);
            //H.print();
            //top insection
            Points T = new Points(H.getX(),H.getY(),h);
            //T.print();
            //bot plane of camera
            Vector n = new Vector(c,H);
            Plane P = new Plane(H,n);
            //mid top insect
            Lines cT = new Lines(c,T);
            Points mT = cal.GetIntPoint(cT,P);
            //mT.print();
            //mid bot insect
            Points mB = new Points(2*H.getX() - mT.getX(),2*H.getY() - mT.getY(),2*H.getZ() - mT.getZ());
            //mB.print();
            //distance from mid too apex
            double d = c.getLenght()*Math.tan(c.getlAngle())/Math.sqrt(2.0);
            //mB.print();
            //System.out.println("d = " + d);
            //get top apex
            Points T1 = new Points(mT.getX()+d,mT.getY()-d,mT.getZ());
            Points T2 = new Points(mT.getX()-d,mT.getY()+d,mT.getZ());
            c.t1 = T1;
            c.t2 = T2;
            //get bbot apex
            Points B1 = new Points(mB.getX()+d,mB.getY()-d,mB.getZ());
            Points B2 = new Points(mB.getX()-d,mB.getY()+d,mB.getZ());
            c.b1 = B1;
            c.b2 = B2;
        }else if(i == 7){
            double dz = c.getLenght()*Math.sin(c.gethAngle());
            double dxy = c.getLenght()*Math.cos(c.getlAngle())/Math.sqrt(2.0);
            Points H = new Points(c.getX() - dxy,c.getY() + dxy,h - dz);
            //top insection
            Points T = new Points(H.getX(),H.getY(),h);
            //bot plane of camera
            Vector n = new Vector(c,H);
            Plane P = new Plane(H,n);
            //mid top insect
            Lines cT = new Lines(c,T);
            Points mT = cal.GetIntPoint(cT,P);
            //mid bot insect
            Points mB = new Points(2*H.getX() - mT.getX(),2*H.getY() - mT.getY(),2*H.getZ() - mT.getZ());
            //distance from mid too apex
            double d = c.getLenght()*Math.tan(c.getlAngle())/Math.sqrt(2.0);
            //get top apex
            Points T1 = new Points(mT.getX()+d,mT.getY()+d,mT.getZ());
            Points T2 = new Points(mT.getX()-d,mT.getY()-d,mT.getZ());
            c.t1 = T1;
            c.t2 = T2;
            //get bbot apex
            Points B1 = new Points(mB.getX()+d,mB.getY()+d,mB.getZ());
            Points B2 = new Points(mB.getX()-d,mB.getY()-d,mB.getZ());
            c.b1 = B1;
            c.b2 = B2;
        }if(i == 12){
            //get center of camera
            Points H = new Points(c.getLenght(),c.getY(),c.getZ());
            double dz = c.getLenght()*Math.tan(c.gethAngle());
            Points mT = new Points(H.getX(),H.getY(),H.getZ()+dz);
            //mid bot insect
            Points mB = new Points(H.getX(),H.getY(),H.getZ()-dz);
            //distance from mid too apex
            double d = c.getLenght()*Math.tan(c.getlAngle());
            //get top apex
            Points T1 = new Points(mT.getX(),mT.getY()+d,mT.getZ());
            Points T2 = new Points(mT.getX(),mT.getY()-d,mT.getZ());
            c.t1 = T1;
            c.t2 = T2;
            //get bbot apex
            Points B1 = new Points(mB.getX(),mB.getY()+d,mB.getZ());
            Points B2 = new Points(mB.getX(),mB.getY()-d,mB.getZ());
            c.b1 = B1;
            c.b2 = B2;
        }else if(i == 14){
            Points H = new Points(c.getX(),l - c.getLenght(),c.getZ());
            double dz = c.getLenght()*Math.tan(c.gethAngle());
            //mid top insect
            Points mT = new Points(H.getX(),H.getY(),H.getZ()+dz);
            //mid bot insect
            Points mB = new Points(H.getX(),H.getY(),H.getZ()-dz);
            //distance from mid too apex
            double d = c.getLenght()*Math.tan(c.getlAngle());
            //get top apex
            Points T1 = new Points(mT.getX()+d,mT.getY(),mT.getZ());
            Points T2 = new Points(mT.getX()-d,mT.getY(),mT.getZ());
            c.t1 = T1;
            c.t2 = T2;
            //get bbot apex
            Points B1 = new Points(mB.getX()+d,mB.getY(),mB.getZ());
            Points B2 = new Points(mB.getX()-d,mB.getY(),mB.getZ());
            c.b1 = B1;
            c.b2 = B2;
        }else if(i == 16){
            Points H = new Points(w-c.getX(),c.getY(),c.getZ());
            double dz = c.getLenght()*Math.tan(c.gethAngle());
            //mid top insect
            Points mT = new Points(H.getX(),H.getY(),H.getZ()+dz);
            //mid bot insect
            Points mB = new Points(H.getX(),H.getY(),H.getZ()-dz);
            //distance from mid too apex
            double d = c.getLenght()*Math.tan(c.getlAngle());
            //get top apex
            Points T1 = new Points(mT.getX(),mT.getY()+d,mT.getZ());
            Points T2 = new Points(mT.getX(),mT.getY()-d,mT.getZ());
            c.t1 = T1;
            c.t2 = T2;
            //get bbot apex
            Points B1 = new Points(mB.getX(),mB.getY()+d,mB.getZ());
            Points B2 = new Points(mB.getX(),mB.getY()-d,mB.getZ());
            c.b1 = B1;
            c.b2 = B2;
        }else if(i == 18){
            Points H = new Points(c.getX(),c.getLenght(),c.getZ());
            double dz = c.getLenght()*Math.tan(c.gethAngle());
            //mid top insect
            Points mT = new Points(H.getX(),H.getY(),H.getZ()+dz);
            //mid bot insect
            Points mB = new Points(H.getX(),H.getY(),H.getZ()-dz);
            //distance from mid too apex
            double d = c.getLenght()*Math.tan(c.getlAngle());
            //get top apex
            Points T1 = new Points(mT.getX()+d,mT.getY(),mT.getZ());
            Points T2 = new Points(mT.getX()-d,mT.getY(),mT.getZ());
            c.t1 = T1;
            c.t2 = T2;
            //get bbot apex
            Points B1 = new Points(mB.getX()+d,mB.getY(),mB.getZ());
            Points B2 = new Points(mB.getX()-d,mB.getY(),mB.getZ());
            c.b1 = B1;
            c.b2 = B2;
        }else if(i == 11){ //in angle
            double dz = c.getLenght()*Math.tan(c.gethAngle());
            double dxy = c.getLenght()/Math.sqrt(2.0);
            Points H = new Points(c.getX() + dxy,c.getY() + dxy,c.getZ());
            Points mT = new Points(H.getX(),H.getY(),H.getZ()+dz);
            //mid bot insect
            Points mB = new Points(H.getX(),H.getY(),H.getZ()-dz);
            //distance from mid too apex
            double d = c.getLenght()*Math.tan(c.getlAngle())/Math.sqrt(2.0);
            //get top apex
            Points T1 = new Points(mT.getX()+d,mT.getY()-d,mT.getZ());
            Points T2 = new Points(mT.getX()-d,mT.getY()+d,mT.getZ());
            c.t1 = T1;
            c.t2 = T2;
            //get bbot apex
            Points B1 = new Points(mB.getX()+d,mB.getY()-d,mB.getZ());
            Points B2 = new Points(mB.getX()-d,mB.getY()+d,mB.getZ());
            c.b1 = B1;
            c.b2 = B2;
        }else if(i == 13){
            double dz = c.getLenght()*Math.tan(c.gethAngle());
            double dxy = c.getLenght()/Math.sqrt(2.0);
            Points H = new Points(c.getX() + dxy,c.getY() - dxy,c.getZ());
            Points mT = new Points(H.getX(),H.getY(),H.getZ()+dz);
            //mid bot insect
            Points mB = new Points(H.getX(),H.getY(),H.getZ()-dz);
            //distance from mid too apex
            double d = c.getLenght()*Math.tan(c.getlAngle())/Math.sqrt(2.0);
            //get top apex
            Points T1 = new Points(mT.getX()+d,mT.getY()+d,mT.getZ());
            Points T2 = new Points(mT.getX()-d,mT.getY()-d,mT.getZ());
            c.t1 = T1;
            c.t2 = T2;
            //get bbot apex
            Points B1 = new Points(mB.getX()+d,mB.getY()+d,mB.getZ());
            Points B2 = new Points(mB.getX()-d,mB.getY()-d,mB.getZ());
            c.b1 = B1;
            c.b2 = B2;
        }else if(i == 15){
            double dz = c.getLenght()*Math.tan(c.gethAngle());
            double dxy = c.getLenght()/Math.sqrt(2.0);
            Points H = new Points(c.getX() - dxy,c.getY() - dxy,c.getZ());
            Points mT = new Points(H.getX(),H.getY(),H.getZ()+dz);
            //mid bot insect
            Points mB = new Points(H.getX(),H.getY(),H.getZ()-dz);
            //mB.print();
            //distance from mid too apex
            double d = c.getLenght()*Math.tan(c.getlAngle())/Math.sqrt(2.0);
            //mB.print();
            //System.out.println("d = " + d);
            //get top apex
            Points T1 = new Points(mT.getX()+d,mT.getY()-d,mT.getZ());
            Points T2 = new Points(mT.getX()-d,mT.getY()+d,mT.getZ());
            c.t1 = T1;
            c.t2 = T2;
            //get bbot apex
            Points B1 = new Points(mB.getX()+d,mB.getY()-d,mB.getZ());
            Points B2 = new Points(mB.getX()-d,mB.getY()+d,mB.getZ());
            c.b1 = B1;
            c.b2 = B2;
        }else if(i == 17){
            double dz = c.getLenght()*Math.tan(c.gethAngle());
            double dxy = c.getLenght()/Math.sqrt(2.0);
            Points H = new Points(c.getX() - dxy,c.getY() + dxy,c.getZ());
            Points mT = new Points(H.getX(),H.getY(),H.getZ()+dz);
            //mid bot insect
            Points mB = new Points(H.getX(),H.getY(),H.getZ()-dz);
            //distance from mid too apex
            double d = c.getLenght()*Math.tan(c.getlAngle())/Math.sqrt(2.0);
            //get top apex
            Points T1 = new Points(mT.getX()+d,mT.getY()+d,mT.getZ());
            Points T2 = new Points(mT.getX()-d,mT.getY()-d,mT.getZ());
            c.t1 = T1;
            c.t2 = T2;
            //get bbot apex
            Points B1 = new Points(mB.getX()+d,mB.getY()+d,mB.getZ());
            Points B2 = new Points(mB.getX()-d,mB.getY()-d,mB.getZ());
            c.b1 = B1;
            c.b2 = B2;
        } 
    }
    //check if point in room
    public boolean checkInR(Points p){
        return 0 <= p.getX() && p.getX() <= w && 0 <= p.getY() && p.getY() <= l && 0 <= p.getZ() && p.getZ() <= h;
    }
    //check add Object
    public boolean checkObj(Object o){
        int a = o.getPo().size();
        for(int i = 0;i<a;i++){
            if(!checkInR(o.getPo().get(i))){
                o.getPo().get(i).print();
                //System.out.println("not in room");
                return false; //object is not in room
            }
        }
        int b = Objs.size();
        if(b > 0){
            for(int i = 0;i <b ;i++){
                if(Objs.get(i).checkInputObj(o) == 0){
                    // System.out.println("in other ob");
                    return false;
                }
            }
            for(int i = 0;i < b;i++){
                if(Objs.get(i).checkInputObj(o) == -1){
                    // System.out.println("on other");
                    return true;
                }
            }
            return o.checkPosObj(0.0);
        }else{
            //System.out.println(o.checkPosObj(0.0));
            return o.checkPosObj(0.0);
        }
    }
    //check point in any obj ?
    public boolean checkInObj(Points a){
        int b = Objs.size();
        if(b == 0){
            return false;
        }else{
            for(int i = 0;i < b;i++){
                if(Objs.get(i).checkInObj(a)){
                    return true;
                }
            }
            return false;
        }
    }
    //check point in any cam vision ?
    public boolean checkInCamV(Points a){
        int b = Cams.size();
        if(b == 0){
            return false;
        }else{
            for(int i = 0;i<b;i++){
                if(Cams.get(i).checkInCam(a)){
                    return true;
                }
            }
            return false;
        }
    }
    //get every cam in CamVision
    public ArrayList<Camera> getCamSeeP(Points a){
        ArrayList<Camera> cam = new ArrayList<Camera>();
        int b = Cams.size();
        for(int i = 0;i<b;i++){
            if(Cams.get(i).checkInCam(a)){
                cam.add(Cams.get(i));
            }
        }
        return cam;
    }
    //function check if point is light or not
    public boolean IsLight(Points a){
        if(!checkInR(a)){
            //System.out.println("not in room");
            return false;
        }else{
            if(checkInObj(a)){
                //System.out.println("Is in obj");
                return false;
            }else{
                if(!checkInCamV(a)){
                    //System.out.println("not thing see");
                    return false;
                }else{
                    ArrayList<Camera> cam = getCamSeeP(a);
                    int b = cam.size();
                    int c = Objs.size();
                    for(int i = 0;i < b;i++){
                        if(c > 0){
                            int count = 0;
                            for(int j = 0;j < c;j++){
                                if(!Objs.get(j).checkTwoPo(a, cam.get(i))){
                                    count = count + 1;
                                }
                            }if(count == c){
                                return true;
                            }   
                        }else{
                            return true;
                        }
                    }
                    return false;
                }
            }
        }
    }
    //function get set of lighted points
    public double getVolLighted(){
        // ArrayList<Points> lPoints = new ArrayList<Points>();
        double vol = 0;
        for(int i = 0;i<100;i++){
            for(int j = 0;j<100;j++){
                for(int k = 0;k<100;k++){
                    Points T = new Points(w*(double)k/100,l*(double)j/100,h*(double)i/100);
                    if(IsLight(T)){
                        vol = vol + 1;
                    }
                }
            }
        }
        return vol;
    }
    //function get lighted are percentage
    public double perLighter(){
        double re = getVolLighted()/10000;
        return Math.round(re*100)/100;
    }
    //function get point is in Object
    public double getInObj(){
        // ArrayList<Points> inObj = new ArrayList<Points>();
        double count = 0;
        for(int i = 0;i<100;i++){
            for(int j = 0;j<100;j++){
                for(int k = 0;k<100;k++){
                    Points T = new Points(w*(double)k/100,l*(double)j/100,h*(double)i/100);
                    if(checkInObj(T)){
                        count = count + 1;
                    }
                }
            }
        }
        return count;
    }
    //function get percentage lighted with object dark
    public double perLight(){
        double v = 1000000 - getInObj();
        double re = getVolLighted()*100/v;
        return Math.round(re*100)/100;
    }
    //function get lighted Bot z = 0
    public ArrayList<Points> lBot(){
        ArrayList<Points> lBot = new ArrayList<Points>();
        for(int i = 0;i < 501;i++ ){
            for(int j = 0; j < 501;j++){
                Points a = new Points(w*(double)i/500,l*(double)j/500,0);
                if(IsLight(a)){
                    lBot.add(a);
                }
            }
        }
        return lBot;
    }
    //function get lighted right x = w
    public ArrayList<Points> lRight(){
        ArrayList<Points> lRight = new ArrayList<Points>();
        for(int i = 0;i < 501;i++ ){
            for(int j = 0; j < 501;j++){
                Points a = new Points(w,l*(double)i/500,h*(double)j/500);
                if(IsLight(a)){
                    lRight.add(a);
                }
            }
        }
        return lRight;
    }
    //f get left x = 0
    public ArrayList<Points> lLeft(){
        ArrayList<Points> lLeft = new ArrayList<Points>();
        for(int i = 0;i < 501;i++ ){
            for(int j = 0; j < 501;j++){
                Points a = new Points(0,l*(double)i/500,h*(double)j/500);
                if(IsLight(a)){
                    lLeft.add(a);
                }
            }
        }
        return lLeft;
    }
    //f get behind y = 0
    public ArrayList<Points> lBehind(){
        ArrayList<Points> lBehind = new ArrayList<Points>();
        for(int i = 0;i < 501;i++ ){
            for(int j = 0; j < 501;j++){
                Points a = new Points(w*(double)i/500,0,h*(double)j/500);
                if(IsLight(a)){
                    lBehind.add(a);
                }
            }
        }
        return lBehind;
    }
    //f get front y = l
    public ArrayList<Points> lFront(){
        ArrayList<Points> lFront = new ArrayList<Points>();
        for(int i = 0;i < 501;i++ ){
            for(int j = 0; j < 501;j++){
                Points a = new Points(w*(double)i/500,l,h*(double)j/500);
                if(IsLight(a)){
                    lFront.add(a);
                }
            }
        }
        return lFront;
    }
    //f get top z = h
    public ArrayList<Points> lTop(){
        ArrayList<Points> lTop = new ArrayList<Points>();
        for(int i = 0;i < 501;i++ ){
            for(int j = 0; j < 501;j++){
                Points a = new Points(w*(double)i/500,l*(double)j/500,h);
                if(IsLight(a)){
                    lTop.add(a);
                }
            }
        }
        return lTop;
    }
}

