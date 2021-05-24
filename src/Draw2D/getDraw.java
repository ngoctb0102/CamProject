import java.util.ArrayList;
public class getDraw {
    String[] s;
    public getDraw(Room r, int option){
        String []s = {"",""};
        s[0] = s[0] + Double.toString(r.getW()) + " " + Double.toString(r.getL());
        //get top
        if(option == 0){
            ArrayList<Points> p = r.lTop();
            for(int i = 0;i < p.size();i++){
            s[1] = s[1] + Double.toString(p.get(i).getX()) + " " + Double.toString(p.get(i).getY()) + " ";
            }   
        }else if(option == 1){
            //get bot
            ArrayList<Points> p = r.lBot();
            for(int i = 0;i < p.size();i++){
            s[1] = s[1] + Double.toString(p.get(i).getX()) + " " + Double.toString(p.get(i).getY()) + " ";
            }
        }else if(option == 2){
            //get Left
            ArrayList<Points> p = r.lLeft();
            for(int i = 0;i < p.size();i++){
            s[1] = s[1] + Double.toString(p.get(i).getY()) + " " + Double.toString(p.get(i).getZ()) + " ";
            }
        }else if(option == 3){
            //get right
            ArrayList<Points> p = r.lRight();
            for(int i = 0;i < p.size();i++){
            s[1] = s[1] + Double.toString(p.get(i).getY()) + " " + Double.toString(p.get(i).getZ()) + " ";
            }
        }else if(option == 4){
            //get behind
            ArrayList<Points> p = r.lBehind();
            for(int i = 0;i < p.size();i++){
            s[1] = s[1] + Double.toString(p.get(i).getX()) + " " + Double.toString(p.get(i).getZ()) + " ";
            }
        }else if(option == 5){
            //get front
            ArrayList<Points> p = r.lFront();
            for(int i = 0;i < p.size();i++){
            s[1] = s[1] + Double.toString(p.get(i).getX()) + " " + Double.toString(p.get(i).getZ()) + " ";
            }
        }
        this.s = s;
    }
    public void show(){
        draw d = new draw();
        d.show(s);
    }
}
