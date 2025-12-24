package DroneHierarchi;
import GEO.Position;
import java.util.List;
import java.util.ArrayList;
public abstract class Drone {
  private int id;
  private Position position;
  private double battery;
  private String model;
  private double speed;
  private double capacity;
  private String status;
  private double totalDistance;
  private List<Position>positionHistory;
  private static int counter=1;
 public Drone(Position p,String m,double s,double c){
    this.id=counter++;
    this.position=p;
    this.battery=100;
    this.capacity=c;
    this.speed=s;
    this.model=m;
    this.status="AVAILABLE";
    this.totalDistance=0;
    this.positionHistory=new ArrayList<>();
 } 
 public abstract double calculateConsumption(double distance);
 
 public boolean canFlyTo(Position destination){
    double dis=this.position.distanceTo(destination)*2;
    double needed=this.calculateConsumption(dis);
    return this.battery>=needed;
 }
 public void FlyTo(Position destination){
    double dis=this.position.distanceTo(destination);
    double consumption=this.calculateConsumption(dis);
    this.battery=this.battery-consumption;
    this.totalDistance=this.totalDistance+dis;
    this.position=destination;
    this.positionHistory.add(destination);
 }
 public void recharge(double percentage){
    this.battery=Math.min(100,this.battery+percentage);
 }
 public String toString(){
    return "Drone: "+this.id+" model: "+this.model+" battery= "+this.battery+"%";
 }
 public int equals(Drone d){
    if(this.id==d.id){
        return 1;
    }else{
        return 0;
    }
 }
 public double getspeed(){
    return this.speed;
 }
 public double getbattery(){
    return this.battery;
 }
 public String getstuts(){
   return this.status;
 }
 public double getcapacity(){
   return this.capacity;
 }
 public Position getposition(){
   return this.position;
 }
 public String getmodel(){
   return this.model;
 }
 public void setstatus(String stut){
   this.status=stut;
 }
 public double gettotaldistance(){
   return this.totalDistance;
 }
}
