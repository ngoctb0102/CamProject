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
    public ArrayList<Camera> getCams() {
        return Cams;
    }
    public void setCams(ArrayList<Camera> cams) {
        Cams = cams;
    }
    //list of object
    private ArrayList<Object> Objs = new ArrayList<Object>();
    private Plane Bot; //z = 0
    private Plane Left; //x = 0
    private Plane Right; //x = w 
    private Plane Behind; //y = 0
    private Plane Front; //y = l
    private Plane Top;//z = h 
    public Room(double l, double w, double h) {
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
    //add camera to Room
    public void addCam(Camera c){
        System.out.println(Top.getD());
        if(cal.IsInPlane(c,Top)){
            if(checkInR(c)){
                Cams.add(c);
                CamVision(c);
            }
        }
    }
    //add Obj to Room
    public void addObj(Object o){
        if(checkObj(o)){
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
    //check if point in room
    public boolean checkInR(Points p){
        if(0 <= p.getX() && p.getX() <= w && 0 <= p.getY() && p.getY() <= l && 0 <= p.getZ() && p.getZ() <= h){
            return true;
        }else{
            return false;
        }
    }
    //check add Object
    public boolean checkObj(Object o){
        for(int i = 0;i<o.getPo().size();i++){
            if(!checkInR(o.getPo().get(i))){
                return false; //object is not in room
            }
        }
        if(Objs.size() > 0){
            for(int i = 0;i < Objs.size();i++){
                if(Objs.get(i).checkInputObj(o) == 0){
                    return false;
                }
            }
            for(int i = 0;i < Objs.size();i++){
                if(Objs.get(i).checkInputObj(o) == -1){
                    return true;
                }
            }
            if(o.checkPosObj(0.0) || o.checkPosObj(h)){
                return true;
            }else{
                return false;
            }
        }else{
            if(o.checkPosObj(0.0) || o.checkPosObj(h)){
                return true;
            }else{
                return false;
            }
        }
    }
    //check point in any obj ?
    public boolean checkInObj(Points a){
        if(Objs.size() == 0){
            return false;
        }else{
            for(int i = 0;i < Objs.size();i++){
                if(Objs.get(i).checkInObj(a)){
                    return false;
                }
            }
            return true;
        }
    }
    //check point in any cam vision ?
    public boolean checkInCamV(Points a){
        if(Cams.size() == 0){
            return false;
        }else{
            for(int i = 0;i<Cams.size();i++){
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
        for(int i = 0;i<Cams.size();i++){
            if(Cams.get(i).checkInCam(a)){
                cam.add(Cams.get(i));
            }
        }
        return cam;
    }
    //function check if point is light or not
    public boolean IsLight(Points a){
        if(!checkInR(a)){
            return false;
        }else{
            if(checkInObj(a)){
                return false;
            }else{
                if(!checkInCamV(a)){
                    return false;
                }else{
                    ArrayList<Camera> cam = getCamSeeP(a);
                    for(int i = 0;i < cam.size();i++){
                        if(Objs.size() > 0)
                        {for(int j = 0;j < Objs.size();j++){
                            if(!Objs.get(i).checkTwoPo(a, cam.get(i))){
                                return true;
                            }
                        }}else{return true;}
                    }
                    return false;
                }
            }
        }
    }
}

