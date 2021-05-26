import java.nio.Buffer;
import java.util.ArrayList;
public class getDraw {
    String[] s;
    public getDraw(Room r, int option){
        String []s = {"","","",""};
        s[0] = s[0] + Double.toString(r.getW()) + " " + Double.toString(r.getL());
        //get top
        StringBuilder builder = new StringBuilder();
        if(option == 0){
            ArrayList<Points> p = r.lTop();
            if(p.size() != 0){
                for(int i = 0;i < p.size();i++){
                    builder.append(Double.toString(p.get(i).getX()));
                    builder.append(" ");
                    builder.append(Double.toString(p.get(i).getY()));
                    builder.append(" ");
                    // s[1] = s[1] + Double.toString(p.get(i).getX()) + " " + Double.toString(p.get(i).getY()) + " ";
                    }  
                    s[1] = builder.toString();
            }else{
                s[1] = "1";
            }
            s[2] = "x y";
            s[3] = "Mặt trần nhà";
        }else if(option == 1){
            //get bot
            ArrayList<Points> p = r.lBot();
            if(p.size() != 0){
                for(int i = 0;i < p.size();i++){
                    builder.append(Double.toString(p.get(i).getX()));
                    builder.append(" ");
                    builder.append(Double.toString(p.get(i).getY()));
                    builder.append(" ");
                    //s[1] = s[1] + Double.toString(p.get(i).getX()) + " " + Double.toString(p.get(i).getY()) + " ";
                    }
                    s[1] = builder.toString();
            }else{
                s[1] = "1";
            }
            s[2] = "x y";
            s[3] = "Mặt sàn nhà";
        }else if(option == 2){
            //get Left
            ArrayList<Points> p = r.lLeft();
            if(p.size() != 0){
                for(int i = 0;i < p.size();i++){
                    builder.append(Double.toString(p.get(i).getY()));
                    builder.append(" ");
                    builder.append(Double.toString(p.get(i).getZ()));
                    builder.append(" ");
                    //s[1] = s[1] + Double.toString(p.get(i).getY()) + " " + Double.toString(p.get(i).getZ()) + " ";
                    }
                    s[1] = builder.toString();
            }else{
                s[1] = "1";
            }
            s[2] = "y z";
            s[3] = "Mặt bên trái";
        }else if(option == 3){
            //get right
            ArrayList<Points> p = r.lRight();
            if(p.size() != 0){
                for(int i = 0;i < p.size();i++){
                    builder.append(Double.toString(p.get(i).getY()));
                    builder.append(" ");
                    builder.append(Double.toString(p.get(i).getZ()));
                    builder.append(" ");
                    //s[1] = s[1] + Double.toString(p.get(i).getY()) + " " + Double.toString(p.get(i).getZ()) + " ";
                    }
                    s[1] = builder.toString();
            }else{
                s[1] = "1";
            }
            s[2] = "y z";
            s[3] = "Mặt bên phải";
        }else if(option == 4){
            //get behind
            ArrayList<Points> p = r.lBehind();
            if(p.size() != 0){
                for(int i = 0;i < p.size();i++){
                    builder.append(Double.toString(p.get(i).getX()));
                    builder.append(" ");
                    builder.append(Double.toString(p.get(i).getZ()));
                    builder.append(" ");
                    //s[1] = s[1] + Double.toString(p.get(i).getX()) + " " + Double.toString(p.get(i).getZ()) + " ";
                    }
                    s[1] = builder.toString();
            }else{
                s[1] = "1";
            }
            s[2] = "x z";
            s[3] = "Mặt đằng sau";
        }else if(option == 5){
            //get front
            ArrayList<Points> p = r.lFront();
            if(p.size() != 0){
                for(int i = 0;i < p.size();i++){
                    builder.append(Double.toString(p.get(i).getX()));
                    builder.append(" ");
                    builder.append(Double.toString(p.get(i).getZ()));
                    builder.append(" ");
                    //s[1] = s[1] + Double.toString(p.get(i).getX()) + " " + Double.toString(p.get(i).getZ()) + " ";
                    }
                    s[1] = builder.toString();
            }else{
                s[1] = "1";
            }
            s[2] = "x z";
            s[3] = "Mặt đăng trước";
        }
        this.s = s;
    }
    public void show(){
        draw d = new draw();
        d.show(s);
    }
}
