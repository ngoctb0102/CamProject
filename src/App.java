public class App {
    public static void main(String[] args) throws Exception {
        Room r = new Room(100.0,100.0,100.0);
        Camera c = new Camera(0.0,0.0,100.0,60.0,50.0);
        r.addCam(c);
        System.out.println(r.IsLight(new Points(0,0,100)));
    }
}
