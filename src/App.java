public class App {
    public static void main(String[] args) throws Exception {
        Room r = new Room(20.0,20.0,20.0);
        Camera c = new Camera(20,20,20,90.0,10.0);
        r.addCam(c);
        //Object o = new Object()
        r.getCams().get(0).print();
        r.getCams().get(0).t1.print();
        r.getCams().get(0).t2.print();
        r.getCams().get(0).b1.print();
        r.getCams().get(0).b2.print();
        System.out.println(r.IsLight(new Points(10,10,20)));
    }
}
