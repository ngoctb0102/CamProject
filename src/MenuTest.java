import java.util.Scanner;
public class MenuTest{
    Scanner newScan = new Scanner(System.in);
    int menuPoint;
    int funct1;
    public void main(String[] args){
        //initialize new room
        Room r = new Room(0,0,0);
        do{
            System.out.println("******************************CAMERA_MENU******************************\n");
            System.out.println("1. Make a new room.\n");
            System.out.println("2. Check a point is light or not?\n");
            System.out.println("3. Show percentage of room was seen by camera.\n");
            System.out.println("4. Show projection of room.\n");
            System.out.println("5. Change the number of cameras in room.\n");
            System.out.println("6. Find the minimum numbers of camera to see all the room.\n");
            System.out.println("7. Find the most suitable location in room for existed cameras.\n");
            System.out.println("8. Quit menu.\n");
            System.out.println("***********************************************************************\n");
            System.out.println("Please choose one funtion you want: \n");
            menuPoint = newScan.nextInt();
            switch(menuPoint){
                case 1:
                    //doFuntion1();
                    r = this.doFunction1();
                    System.out.println(this.doFunction3NoObj(r));
                    System.out.println(this.doFunction3WithObj(r));
                    break;
                case 2:
                    // make a point from user
                    System.out.println("Insert x of point: ");
                    int xP = newScan.nextInt();
                    System.out.println("Insert y of point: ");
                    int yP = newScan.nextInt();
                    System.out.println("Insert z of point: ");
                    int zP = newScan.nextInt();
                    //doFuntion2();
                    this.doFunction2(new Points(xP, yP, zP));
                    break; 
                case 3:
                    //doFunction3();
                    System.out.println(this.doFunction3NoObj(r));
                    System.out.println(this.doFunction3WithObj(r));
                    break;
                case 4:
                    //doFunction4();
                    this.doFunction4(r);
                    break;
                case 5:
                    //doFunction5();
                    this.doFunction5(r);
                    break;
                case 6:
                    //doFunction6();
                    this.doFunction6(r);
                    break;
                case 7:
                    //doFunction7();
                    this.doFunction7(r);
                    break;
                case 8:
                    System.out.println("\nThanks for using menu of our team <3\n\n");
                    break;
                default:
                    System.out.println("\nPlease type number from 1 to 8!!\n\n");
        }
        }
        while(menuPoint != 8);    
        }




//function 1: 
public Room doFunction1(){
    
    Room r = new Room(0,0,0);

    System.out.println("What do youu want:\n");
    System.out.println("1. Get input from file.\n");
    System.out.println("2. Type input.\n");
    System.out.println("Choose one!");
    funct1 = newScan.nextInt();

    switch(funct1){
        case 1:
            ReadFile f = new ReadFile("src/File/input.txt");
            r = f.makeRoom();
            System.out.println(doFunction3NoObj(r)); 
            System.out.println(doFunction3WithObj(r));
            break;
        case 2:
            //add room
            int lengthRoom, heightRoom, widthRoom;
            do{
                System.out.println("Insert the lenght of room: ");
                lengthRoom = newScan.nextInt();
                if(lengthRoom <= 0 || lengthRoom > 100){
                    System.out.println("Length of room is more than 0 and less then  equal 100");
                }
            }
            while(lengthRoom <= 0 || lengthRoom > 100);

            do{
                System.out.println("Insert the height of room: ");
                heightRoom = newScan.nextInt();
                if(heightRoom <= 0 || heightRoom > 100){
                    System.out.println("height of room is more than 0 and less then  equal 100");
                }
            }
            while(heightRoom <= 0 || heightRoom > 100);

            do{
                System.out.println("Insert the width of room: ");
                widthRoom = newScan.nextInt();
                if(widthRoom <= 0 || widthRoom > 100){
                    System.out.println("Width of room is more than 0 and less then  equal 100");
                }
            }
            while(widthRoom <= 0 || widthRoom > 100);

            r = new Room(widthRoom, lengthRoom, heightRoom);

            //add camera
            System.out.println("Insert the number of cameras: ");
            int numCams = newScan.nextInt();
            for(int i = 0; i < numCams; i++){
                System.out.println("Insert x of camera: ");
                int xCam = newScan.nextInt();
                System.out.println("Insert y of camera: ");
                int yCam = newScan.nextInt();
                System.out.println("Insert z of camera: ");
                int zCam = newScan.nextInt();
                System.out.println("Insert high angle of camera: ");
                int hCam = newScan.nextInt();
                System.out.println("Insert wide angle of camera: ");
                int wCam = newScan.nextInt();
                r.addCam(new Camera(xCam, yCam, zCam, hCam, wCam));
            }
            //add object
            System.out.println("Insert the number of objects: ");
            int numObjs = newScan.nextInt();
            for(int i = 0; i < numObjs; i++){
                // Point 1 of obj
                System.out.println("Insert x1 of point1 of object: ");
                int x1Obj = newScan.nextInt();
                System.out.println("Insert y1 of point1 of object: ");
                int y1Obj = newScan.nextInt();
                System.out.println("Insert z1 of point1 of object: ");
                int z1Obj = newScan.nextInt();
                // Point 2 of obj
                System.out.println("Insert x2 of point2 of object: ");
                int x2Obj = newScan.nextInt();
                System.out.println("Insert y2 of point2 of object: ");
                int y2Obj = newScan.nextInt();
                System.out.println("Insert z2 of point1 of object: ");
                int z2Obj = newScan.nextInt();
                // Point 3 of obj
                System.out.println("Insert x3 of point1 of object: ");
                int x3Obj = newScan.nextInt();
                System.out.println("Insert y3 of point1 of object: ");
                int y3Obj = newScan.nextInt();
                System.out.println("Insert z3 of point1 of object: ");
                int z3Obj = newScan.nextInt();
                // Point 4 of obj
                System.out.println("Insert x1 of point1 of object: ");
                int x4Obj = newScan.nextInt();
                System.out.println("Insert y1 of point1 of object: ");
                int y4Obj = newScan.nextInt();
                System.out.println("Insert z1 of point1 of object: ");
                int z4Obj = newScan.nextInt();
                // Height of obj
                System.out.println("Insert height of object: ");
                int hObj = newScan.nextInt();
                r.addObj(new Object(new Points(x1Obj, y1Obj, z1Obj), new Points(x2Obj, y2Obj, z2Obj), new Points(x3Obj, y3Obj, z3Obj), new Points(x4Obj, y4Obj, z4Obj), hObj));
            }
        default:
            System.out.println("Please choose 1 or 2!!");
            break;
    }  
            while(funct1 < 1 || funct1 >2);
    return r;       
}


//function 2:
public void doFunction2(Points p){
    if(p.isState()){
      System.out.print("Diem ");
      p.print();
      System.out.print(" da duoc chieu sang.\n");
  }
    else{
      System.out.print("Diem ");
      p.print();
      System.out.print(" khong duoc chieu sang.\n");
  }
}



//function 3:
public double doFunction3WithObj(Room r){
    return r.perLight();
}

public double doFunction3NoObj(Room r){
    return r.perLighter();
}


//function 4:
public void doFunction4(Room r){
    int x;
    do{
        System.out.println("Which plane of the room that you want to show projection:\n");
        System.out.println("1. Bot\n");     //1
        System.out.println("2. Left\n");    //2
        System.out.println("3. Right\n");   //3
        System.out.println("4. Front\n");   //5
        System.out.println("5. Behind\n");  //4
        System.out.println("Choose one: ");
        x = newScan.nextInt();
        switch(x){
            //get vision with per plane
            // getdraw
            case 1:
            getDraw g1 = new getDraw(r,1);
            g1.show();
            break;
            case 2:
            getDraw g2 = new getDraw(r,2);
            g2.show();
            break;
            case 3:
            getDraw g3 = new getDraw(r,3);
            g3.show();
            break;
            case 4:
            getDraw g4 = new getDraw(r,5);
            g4.show();
            break;
            case 5:
            getDraw g5 = new getDraw(r,4);
            g5.show();
            break;
            default:
            System.out.println("Please choose one plane from 1 to 5.");
        }
    }
    while(x < 1 || x >5);
}


//function 5: change number of cameras
public Room doFunction5(Room r){
    System.out.println("What do you want:");
    System.out.println("1. Add a camera.");
    System.out.println("1. Remove a camera.");
    System.out.println("Choose one:");
    int x = newScan.nextInt();
    int m, n;
    switch(x){
        case 1:
            // khong biet lam sao de nhet vong lap do while de check dieu kien
            System.out.println("Insert x of camera: ");
            double a = newScan.nextDouble();
            System.out.println("Insert y of camera: ");
            double b = newScan.nextDouble();
            System.out.println("Insert z of camera: ");
            double c = newScan.nextDouble();
            do{
                System.out.println("Insert wide angle of camera:");
                m = newScan.nextInt();
                if(m <= 0 || m > 90){
                    System.out.println("Wide angle of room is more than 0 and less than equal 90.");
                }
            }
            while(m <= 0 || m > 90);
            do{
            System.out.println("Insert high angle of camera:");
            n = newScan.nextInt();
            if(n <= 0 || n > 90){
                System.out.println("High angle of room is more than 0 and less than equal 90.");
            }
            }
            while(n <= 0 || n > 90);
            System.out.println("Insert lenght of camera, type \" n\" to use default lenght.");
            String s = newScan.nextLine();
            if(s.equals("n")){
                Camera cam = new Camera(a,b,c,m,n);
                r.addCam(cam);
            }
            else{
                double sLength = Double.parseDouble(s);
                Camera cam = new Camera(a,b,c,m,n,sLength);
                r.addCam(cam);
            }
            break;
        case 2:
            System.out.println("Nhap vao toa do x:");
            double p = newScan.nextDouble();
            System.out.println("Nhap vao toa do y:");
            double q = newScan.nextDouble();
            System.out.println("Nhap vao toa do z:");
            double l = newScan.nextDouble();
            // delete camera
            r.getCams().remove(new Camera(p,q,l));
            break;
        default:
            System.out.println("Error!!Please choose 1 or 2!!");
    }
    return r;
}


//function 6:
public void doFunction6(Room r){
    int m, n;
    do{
        System.out.println("Insert wide angle of camera:");
        m = newScan.nextInt();
        if(m <= 0 || m > 90){
            System.out.println("Wide angle of room is more than 0 and less than equal 90.");
        }
    }
    while(m <= 0 || m > 90);
        do{
        System.out.println("Insert high angle of camera:");
        n = newScan.nextInt();
        if(n <= 0 || n > 90){
            System.out.println("High angle of room is more than 0 and less than equal 90.");
        }
    }
    while(n <= 0 || n > 90);
    Max newMax = new Max(r, m, n);
    newMax.CameraUnlimited();
}

//function 7:
public void doFunction7(Room r){
    int m, n;
    System.out.println("Insert the number of camera: ");
    int numCamera = newScan.nextInt();
    do{
        System.out.println("Insert wide angle of camera:");
        m = newScan.nextInt();
        if(m <= 0 || m > 90){
            System.out.println("Wide angle of room is more than 0 and less than equal 90.");
        }
    }
    while(m <= 0 || m > 90);
    do{
        System.out.println("Insert high angle of camera:");
        n = newScan.nextInt();
        if(n <= 0 || n > 90){
            System.out.println("High angle of room is more than 0 and less than equal 90.");
        }   
    }
    while(n <= 0 || n > 90);
    Max newMax = new Max(r, m, n);
    newMax.CameraLimited(numCamera);
}

}