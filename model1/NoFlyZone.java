package GEO;
public class NoFlyZone{
    private Position center;
    private double radius;

    public NoFlyZone(Position center,double radius){
        this.center=center;
        this.radius=radius;
    }
    public boolean contains(Position p){
        return this.center.distanceTo(p)<=this.radius;
    }
}
