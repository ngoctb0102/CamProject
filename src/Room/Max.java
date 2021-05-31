import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Max {
    private Room r;
    private double hAngle;
    private double lAngle;
    private double v;
    ArrayList<Camera> cams = new ArrayList<Camera>();
    //private int numCam = 0;
    public Max(Room r, double hAngle, double lAngle) {
        // ArrayList<Camera> cam = new ArrayList<Camera>();
        // //make camera list empty
        // r.setCams(cam);
        this.v = Math.round(((1000000 - r.getInObj())/10000*100)/100);
        this.r = r;
        this.hAngle = hAngle;
        this.lAngle = lAngle;
    }
    public Camera getMaxCam(){
        List<Double> list = new ArrayList<Double>();
        ArrayList<Camera> c = new ArrayList<Camera>();
        double w = r.getW();
        double l = r.getL();
        double h = r.getH();
        for(int i = 0;i < 10;i++){
            Camera ca = new Camera(w*(double)i/10,0,h,hAngle,lAngle);
            r.addCam(ca);
            // System.out.println("is here 1");
            double d = r.perLighter();
            //System.out.println(d);
            list.add(d);
            c.add(ca);
            r.getCams().remove(ca);
            if(d == v){
                return ca;
            }
        }
        for(int i = 0;i < 10;i++){
            Camera ca = new Camera(w*(double)i/10,l,h,hAngle,lAngle);
            r.addCam(ca);
            // System.out.println("is here 1");
            double d = r.perLighter();
            //System.out.println(d);
            list.add(d);
            c.add(ca);
            r.getCams().remove(ca);
            if(d == v){
                return ca;
            }
        }
        for(int i = 0;i < 10;i++){
            Camera ca = new Camera(0,l*(double)i/10,h,hAngle,lAngle);
            r.addCam(ca);
            // System.out.println("is here 1");
            double d = r.perLighter();
            //System.out.println(d);
            list.add(d);
            c.add(ca);
            r.getCams().remove(ca);
            if(d == v){
                return ca;
            }
        }
        for(int i = 0;i < 10;i++){
            Camera ca = new Camera(w,l*(double)i/10,h,hAngle,lAngle);
            r.addCam(ca);
            // System.out.println("is here 1");
            double d = r.perLighter();
            //System.out.println(d);
            list.add(d);
            c.add(ca);
            r.getCams().remove(ca);
            if(d == v){
                return ca;
            }
        }
        double max = Collections.max(list);
        //System.out.println(max);
        int size = list.size();
        for(int i = 0;i<size;i++){
            if(list.get(i) == max){
                //System.out.println("is here 2");
                return c.get(i);
            }
        }
        return null;
    }
    public void getMax(){
        //System.out.println("is here v = " + v);
        double x = r.perLighter();
        while(x < v){
            Camera c = getMaxCam();
            if(c != null){
                double check = r.perLighter();
                r.addCam(c);
                double check1 = r.perLighter();
                r.getCams().remove(c);
                if(check == check1){
                    break;
                }else if(check1 > check){
                    //System.out.println("is here 3");
                    r.addCam(c);
                    x = check1;
                }
            }else{
                break;
            }
        }
    }
    public void getMaxLimit(int numCam){
        for(int i = 0;i < numCam;i++){
            Camera c = getMaxCam();
            if(c != null){
                double check = r.perLighter();
                r.addCam(c);
                double check1 = r.perLighter();
                r.getCams().remove(c);
                if(check == check1){
                    break;
                }else if(check1 > check){
                    //System.out.println("is here 3");
                    r.addCam(c);
                }
            }else{
                break;
            }
        }
    }
    public void CameraLimited(int numCam){
        getMaxLimit(numCam);
        ArrayList<Camera> cam = r.getCams();
        System.out.println("Room is light max with : " + cam.size() + " camera");
        System.out.println("Cam position is :");
        for(int i = 0;i < cam.size();i++){
            cam.get(i).print();
        }
        System.out.println("Room is lighted " + r.perLighter() + "%");
    }

    
    public void CameraUnlimited(){
        getMax();
        ArrayList<Camera> cam = r.getCams();
        System.out.println("Room is light max with : " + cam.size() + " camera");
        System.out.println("Cam position is :");
        for(int i = 0;i < cam.size();i++){
            cam.get(i).print();
        }
        System.out.println("Room is lighted " + r.perLighter() + "%");
    }
}
