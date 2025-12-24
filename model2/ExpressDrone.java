package DroneHierarchi;
import GEO.Position;
public class ExpressDrone extends Drone {
  public ExpressDrone(Position p){
    super(p,"Express",45,1.0);
  }
  public double calculateConsumption(double distance){
    return distance*4;
  }
  public double getspeed(){
    if(this.getbattery()<20){
      return 16;
    }else{
        return 45;
    }
  }
}
