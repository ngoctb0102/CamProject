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
    //get number of light point :>
    private ArrayList<Points> lLeft = new ArrayList<Points>();
    private ArrayList<Points> lRight = new ArrayList<Points>();
    private ArrayList<Points> lBehind = new ArrayList<Points>();
    private ArrayList<Points> lFront = new ArrayList<Points>();
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
        if(cal.IsInPlane(c,Top)){
            if(checkInR(c)){
                Cams.add(c);
                System.out.println("Added Camera");
                CamVision(c);
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
        }else if(CheckCam(c) == 4){
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
        }else if(CheckCam(c) == 6){
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
        }else if(CheckCam(c) == 8){
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
        }else if(CheckCam(c) == 1){ //in angle
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
        }else if(CheckCam(c) == 3){
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
        }else if(CheckCam(c) == 5){
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
        }else if(CheckCam(c) == 7){
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
                    return true;
                }
            }
            return false;
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
                    for(int i = 0;i < cam.size();i++){
                        if(Objs.size() > 0){
                            for(int j = 0;j < Objs.size();j++){
                                if(!Objs.get(i).checkTwoPo(a, cam.get(i))){
                                    return true;
                                }
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
        ArrayList<Points> lPoints = new ArrayList<Points>();
        for(int i = 0;i<100;i++){
            for(int j = 0;j<100;j++){
                for(int k = 0;k<100;k++){
                    Points T = new Points(w*(double)k/100,l*(double)j/100,h*(double)i/100);
                    if(IsLight(T)){
                        lPoints.add(T);
                    }
                }
            }
        }
        return lPoints.size();
    }
    //function get lighted are percentage
    public double perLighter(){
        double re = getVolLighted()/10000;
        return Math.round(re*100)/100;
    }
    //function get point is in Object
    public double getInObj(){
        ArrayList<Points> inObj = new ArrayList<Points>();
        for(int i = 0;i<100;i++){
            for(int j = 0;j<100;j++){
                for(int k = 0;k<100;k++){
                    Points T = new Points(w*(double)k/100,l*(double)j/100,h*(double)i/100);
                    if(checkInObj(T)){
                        inObj.add(T);
                    }
                }
            }
        }
        return inObj.size();
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
        for(int i = 0;i < 500;i++ ){
            for(int j = 0; j < 500;j++){
                Points a = new Points(w*(double)i/1000,l*(double)j/1000,0);
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
        for(int i = 0;i < 1000;i++ ){
            for(int j = 0; j < 1000;j++){
                Points a = new Points(w,l*(double)i/1000,h*(double)j/1000);
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
        for(int i = 0;i < 1000;i++ ){
            for(int j = 0; j < 1000;j++){
                Points a = new Points(0,l*(double)i/1000,h*(double)j/1000);
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
        for(int i = 0;i < 1000;i++ ){
            for(int j = 0; j < 1000;j++){
                Points a = new Points(w*(double)i/1000,0,h*(double)j/1000);
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
        for(int i = 0;i < 1000;i++ ){
            for(int j = 0; j < 1000;j++){
                Points a = new Points(w*(double)i/1000,l,h*(double)j/1000);
                if(IsLight(a)){
                    lFront.add(a);
                }
            }
        }
        return lFront;
    }

}

