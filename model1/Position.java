package GEO;
public class Position {
    private double x;
    private double y;
    public Position (double x,double y){
        this.x=x;
        this.y=y;
    }
    public double getx(){
        return this.x;
    }
    public double gety(){
        return this.y;
    }
    public void setx(double x){
        this.x=x;
    }
    public void sety(double y){
        this.y=y;
    }
    public double distanceTo(Position other){
        double dx=other.x - this.x;
        double dy=other.y - this.y;
        return Math.sqrt(dx*dx + dy*dy);
    }
    public void moveTo(Position distination,double step){
        double distance=this.distanceTo(distination);
        if(distance<=step){
            this.x=distination.x;
            this.y=distination.y;
            return;
        }
        double scale=step/distance;
        this.x=this.x+(distination.x - this.x)*scale;
        this.y=this.y+(distination.y - this.y)*scale;
    }
    public String toString(){
        return "position("+this.x+" , "+this.y+")";
    }
    public int equals(Position p){
        if(this.x==p.x && this.y==p.y){
            return 1;
        }else{
            return 0;
        }
    }
}
