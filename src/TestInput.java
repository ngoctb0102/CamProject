import java.util.ArrayList;
import java.util.Scanner;
//import Camera.Camera;
import java.io.FileInputStream;
import java.io.IOException;

public class TestInput {
    public static void main(String[] args) throws Exception {
        // Room r = new Room(20.0,20.0,20.0);
        // Camera c = new Camera(13,0,18,60,60);
        // r.addCam(c);
        // Camera c1 = new Camera(7,0,6,60,60);
        // r.addCam(c1);
        // Points A = new Points(5,5,0);
        // Points B = new Points(5,10,0);
        // Points C = new Points(10,10,0);
        // Points D = new Points(10,5,0);
        // Object o = new Object(A,B,C,D,15);
        // r.addObj(o);
        // Points A1 = new Points(1,1,0);
        // Points B1 = new Points(3,3,0);
        // Points C1 = new Points(3,1,0);
        // Points D1 = new Points(1,3,0);
        // Object o1 = new Object(A1,B1,C1,D1,8);
        // r.addObj(o1);
        // Points a2 = new Points(1,1,6);
        // Points b2 = new Points(2,2,6);
        // Points c2 = new Points(2,1,6);
        // Points d2 = new Points(1,2,6);
        // Object o2 = new Object(a2,b2,c2,d2,4);
        // r.addObj(o2);
        //System.out.println(r.IsLight(new Points(18,0,0)));
        //System.out.println(r.perLighter() + "%");
        //System.out.println(r.lBot().size());
        // getDraw g = new getDraw(r,1);
        // g.show();




        // read file
        int count = 0;
    	String [] lines = new String[100];
        String path = "src/File/input.txt";
    	FileInputStream fileInputStream = new FileInputStream(path);
        Scanner scanner = new Scanner(fileInputStream);
    	try {
    		while (scanner.hasNextLine()) {
                lines[count] = scanner.nextLine();
                count++;
            }
    	    } finally {
                try {
                    scanner.close();
                    fileInputStream.close();
                } catch (IOException ex) {
                    // System.out.println("error");
                }
            }
    	int num_of_obj = Integer.parseInt(lines[1]); // so object
    	int  num_of_camera = Integer.parseInt(lines[2+num_of_obj]); // so camera

    	
        // tach toa do room 
        String arr_point[] = lines[0].split(" ");
        ArrayList<Double> nums = new ArrayList<Double>(); // mang toa do camera
        for(String w: arr_point) {
        	String[] a = w.replace(")", "").replace("(", "").split(",");
        	for(int i = 0; i < a.length; i++) {
        		double temp = Double.parseDouble(a[i]);
        		nums.add(temp);
        	}
        }
        

       // Khoi tao room
        ArrayList<Points> arr_pointRoom = new ArrayList<Points>();
        for(int i = 0; i < nums.size(); i = i + 3 ){
           Points pointRoom = new Points(nums.get(i), nums.get(i+1), nums.get(i+2));
            arr_pointRoom.add(pointRoom);
        }
        Room r = new Room(arr_pointRoom.get(0),arr_pointRoom.get(1),arr_pointRoom.get(2),arr_pointRoom.get(3),arr_pointRoom.get(4),arr_pointRoom.get(5),arr_pointRoom.get(6),arr_pointRoom.get(7));


        //tach toa do cua object 
        ArrayList<Double> numsObj = new ArrayList<Double>(); // mang toa do object
        for(int j = 2; j < (2 + num_of_obj); j++ ){
        String arr_pointObj[] = lines[j].split(" ");
        for(String w: arr_pointObj) {
        	String[] a = w.replace(")", "").replace("(", "").split(",");
        	for(int i = 0; i < a.length; i++) {
        		double temp2 = Double.parseDouble(a[i]);
        		numsObj.add(temp2);
        	}
        }
        }
        
        // Khoi tao vat the 
        ArrayList<Points> arr_pointObj = new ArrayList<Points>();
        for(int i = 0; i < numsObj.size(); i = i + 3 ){
           Points pointObj = new Points(numsObj.get(i), numsObj.get(i+1), numsObj.get(i+2));
            arr_pointObj.add(pointObj);
        }
        ArrayList<Object> arrObject = new ArrayList<Object>();
        for(int i = 0; i < arr_pointObj.size(); i = i + 8){
        r.addObj(new Object(arr_pointObj.get(i),arr_pointObj.get(i+1),arr_pointObj.get(i+2),arr_pointObj.get(i+3),arr_pointObj.get(i+4),arr_pointObj.get(i+5),arr_pointObj.get(i+6),arr_pointObj.get(i+7)));
        //arrObject.add(obj);
        }
        
        
        // tach toa do camera
        ArrayList<Double> numsCam = new ArrayList<Double>(); // mang toa do camera
        for(int j = 3 + num_of_obj; j <= (2 + num_of_camera + num_of_obj); j++ ){
        String arr_cam[] = lines[j].split(" ");
        for(String w: arr_cam) {
        	String[] a = w.replace(")", "").replace("(", "").split(",");
        	for(int i = 0; i < a.length; i++) {
        		double temp3 = Double.parseDouble(a[i]);
        		numsCam.add(temp3);
        	}
        }
        }

        // Khoi tao camera
        ArrayList<Camera> arrCamera = new ArrayList<Camera>();
        for(int i = 0; i < numsCam.size(); i = i + 5){
            r.addCam( new Camera(numsCam.get(i), numsCam.get(i+1), numsCam.get(i+2), numsCam.get(i+3), numsCam.get(i+4)));
            //arrCamera.add(cam);
        }

        //System.out.println(nums);
        //System.out.println(arr_pointRoom);
        //System.out.println(numsObj);
        //System.out.println(numsCam);
        // System.out.println(r.getCams().size());
        getDraw g = new getDraw(r,0);
        g.show();
    }
}