package cn.edu.bjtu.stringAPP;

public class StringView{
    private final static double TWOPI = 2 * 3.14159;
    
    private static Point[] point;
    

//    @SuppressWarnings("unused")
//	private void calcPos() {
//        int x = 540;
//        int y = 960;
//        int i = 0;
//        for(double ta = 0; ta <= TWOPI; ta += 0.1) {
//
//            point[i] = new Point();
//            point[i].x = (int) (x * Math.cos(ta) + x);
//            point[i].y = (int) (x * Math.sin(ta) + y);
//            
//            i += 1;
//        }
//    }
//    
    public static void main(String[] args){
        int x = 540;
        int y = 960;
        int i = 0;
        point = new Point[1000];
        for(double ta = 0; ta <= TWOPI; ta += 0.1) {
            point[i] = new Point();
            point[i].x = (int) (x * Math.cos(ta) + x);
            System.out.println(point[i].x);
            
            point[i].y = (int) (x * Math.sin(ta) + y);
            System.out.println(point[i].y);
            
            System.out.println(i);
            i += 1;
        }
    }
}


