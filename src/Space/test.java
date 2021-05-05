package Space;
public class test {
    public static void main(String[] args) {
        Points A = new Points(1,1,1);
        Points B = new Points(1,2,3);
        Vector a = new Vector(A,B);
        System.out.println(a.Distance());
    }
}
