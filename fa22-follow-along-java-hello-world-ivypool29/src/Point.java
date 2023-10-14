import edu.princeton.cs.algs4.StdOut;

public class Point {
    private int x;
    private int y;

    public Point(){
        x = 123456;
        y = 987654;
    }

    public Point(int x){
        this.x = x;
    }

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return y;
    }

    public void setY(int y){
        this.y = y;
    }
}
