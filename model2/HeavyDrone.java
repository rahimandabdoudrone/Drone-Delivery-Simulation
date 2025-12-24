package DroneHierarchi;
import GEO.Position;
public class HeavyDrone extends Drone {
  public HeavyDrone(Position p){
    super(p,"Heavy",20,3.0);
  }
  public double calculateConsumption(double distance){
    return distance*5;
  }
}
