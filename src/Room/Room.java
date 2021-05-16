//package Room;
//import Space.*;
import java.lang.*;
import java.util.ArrayList;

//import Camera.Camera;
public class Room {
    Cal cal = new Cal();
    private double l;
    private double w;
    private double h;
    //list of camera
    private ArrayList<Camera> Cams = new ArrayList<Camera>();
    //list of object
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
            if(c.getX() >= 0.0 && c.getY() <= w){
                if(c.getY() >= 0.0 && c.getY() <= l){
                    Cams.add(c); //check if cam in room
                    CamVision(c);
                }
            }
        }
    }
    //add Obj to Room
    public void addObj(Object o){
        if(o.checkPosObj(0.0) || o.checkPosObj(h)){
            Objs.add(o);
        }
    }
    //check where the camera is ?
    public int CheckCam(Camera c){
        if(!cal.IsInPlane(c,Top)){
            return 0; //if camera is not in top return 0
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
        if(CheckCam(c) == 2){
            //get center of camera
            Points H = new Points(c.getLenght()*Math.cos(c.getAngle()),c.getY(),h - c.getLenght()*Math.sin(c.getAngle()));
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
            double d = c.getLenght()*Math.tan(c.getAngle());
            //get top apex
            Points T1 = new Points(mT.getX(),mT.getY()+d,mT.getZ());
            Points T2 = new Points(mT.getX(),mT.getY()-d,mT.getZ());
            c.t1 = T1;
            c.t2 = T2;
            //get bbot apex
            Points B1 = new Points(mB.getX(),mB.getY()+d,mT.getZ());
            Points B2 = new Points(mB.getX(),mB.getY()-d,mT.getZ());
            c.b1 = B1;
            c.b2 = B2;
        }else if(CheckCam(c) == 4){
            Points H = new Points(c.getX(),l - c.getLenght()*Math.cos(c.getAngle()),h - c.getLenght()*Math.sin(c.getAngle()));
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
            double d = c.getLenght()*Math.tan(c.getAngle());
            //get top apex
            Points T1 = new Points(mT.getX()+d,mT.getY(),mT.getZ());
            Points T2 = new Points(mT.getX()-d,mT.getY(),mT.getZ());
            c.t1 = T1;
            c.t2 = T2;
            //get bbot apex
            Points B1 = new Points(mB.getX()+d,mB.getY(),mT.getZ());
            Points B2 = new Points(mB.getX()-d,mB.getY(),mT.getZ());
            c.b1 = B1;
            c.b2 = B2;
        }else if(CheckCam(c) == 6){
            Points H = new Points(w - c.getLenght()*Math.cos(c.getAngle()),c.getY(),h - c.getLenght()*Math.sin(c.getAngle()));
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
            double d = c.getLenght()*Math.tan(c.getAngle());
            //get top apex
            Points T1 = new Points(mT.getX(),mT.getY()+d,mT.getZ());
            Points T2 = new Points(mT.getX(),mT.getY()-d,mT.getZ());
            c.t1 = T1;
            c.t2 = T2;
            //get bbot apex
            Points B1 = new Points(mB.getX(),mB.getY()+d,mT.getZ());
            Points B2 = new Points(mB.getX(),mB.getY()-d,mT.getZ());
            c.b1 = B1;
            c.b2 = B2;
        }else if(CheckCam(c) == 8){
            Points H = new Points(c.getX(),c.getLenght()*Math.cos(c.getAngle()),h - c.getLenght()*Math.sin(c.getAngle()));
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
            double d = c.getLenght()*Math.tan(c.getAngle());
            //get top apex
            Points T1 = new Points(mT.getX()+d,mT.getY(),mT.getZ());
            Points T2 = new Points(mT.getX()-d,mT.getY(),mT.getZ());
            c.t1 = T1;
            c.t2 = T2;
            //get bbot apex
            Points B1 = new Points(mB.getX()+d,mB.getY(),mT.getZ());
            Points B2 = new Points(mB.getX()-d,mB.getY(),mT.getZ());
            c.b1 = B1;
            c.b2 = B2;
        }else if(CheckCam(c) == 1){ //in angle
            double dz = c.getLenght()*Math.sin(c.getAngle());
            double dxy = c.getLenght()*Math.cos(c.getAngle())/Math.sqrt(2.0);
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
            double d = c.getLenght()*Math.tan(c.getAngle())/Math.sqrt(2.0);
            //get top apex
            Points T1 = new Points(mT.getX()+d,mT.getY()-d,mT.getZ());
            Points T2 = new Points(mT.getX()-d,mT.getY()+d,mT.getZ());
            c.t1 = T1;
            c.t2 = T2;
            //get bbot apex
            Points B1 = new Points(mB.getX()+d,mB.getY()-d,mT.getZ());
            Points B2 = new Points(mB.getX()-d,mB.getY()+d,mT.getZ());
            c.b1 = B1;
            c.b2 = B2;
        }else if(CheckCam(c) == 3){
            double dz = c.getLenght()*Math.sin(c.getAngle());
            double dxy = c.getLenght()*Math.cos(c.getAngle())/Math.sqrt(2.0);
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
            double d = c.getLenght()*Math.tan(c.getAngle())/Math.sqrt(2.0);
            //get top apex
            Points T1 = new Points(mT.getX()+d,mT.getY()+d,mT.getZ());
            Points T2 = new Points(mT.getX()-d,mT.getY()-d,mT.getZ());
            c.t1 = T1;
            c.t2 = T2;
            //get bbot apex
            Points B1 = new Points(mB.getX()+d,mB.getY()+d,mT.getZ());
            Points B2 = new Points(mB.getX()-d,mB.getY()-d,mT.getZ());
            c.b1 = B1;
            c.b2 = B2;
        }else if(CheckCam(c) == 5){
            double dz = c.getLenght()*Math.sin(c.getAngle());
            double dxy = c.getLenght()*Math.cos(c.getAngle())/Math.sqrt(2.0);
            Points H = new Points(c.getX() - dxy,c.getY() - dxy,h - dz);
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
            double d = c.getLenght()*Math.tan(c.getAngle())/Math.sqrt(2.0);
            //get top apex
            Points T1 = new Points(mT.getX()+d,mT.getY()-d,mT.getZ());
            Points T2 = new Points(mT.getX()-d,mT.getY()+d,mT.getZ());
            c.t1 = T1;
            c.t2 = T2;
            //get bbot apex
            Points B1 = new Points(mB.getX()+d,mB.getY()-d,mT.getZ());
            Points B2 = new Points(mB.getX()-d,mB.getY()+d,mT.getZ());
            c.b1 = B1;
            c.b2 = B2;
        }else if(CheckCam(c) == 7){
            double dz = c.getLenght()*Math.sin(c.getAngle());
            double dxy = c.getLenght()*Math.cos(c.getAngle())/Math.sqrt(2.0);
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
            double d = c.getLenght()*Math.tan(c.getAngle())/Math.sqrt(2.0);
            //get top apex
            Points T1 = new Points(mT.getX()+d,mT.getY()+d,mT.getZ());
            Points T2 = new Points(mT.getX()-d,mT.getY()-d,mT.getZ());
            c.t1 = T1;
            c.t2 = T2;
            //get bbot apex
            Points B1 = new Points(mB.getX()+d,mB.getY()+d,mT.getZ());
            Points B2 = new Points(mB.getX()-d,mB.getY()-d,mT.getZ());
            c.b1 = B1;
            c.b2 = B2;
        }
        
    }
}
