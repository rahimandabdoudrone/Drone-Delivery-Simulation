package DroneHierarchi;
import GEO.Position;
public class StandardDrone extends Drone {
 public StandardDrone(Position pos){
    super(pos,"Standard",30,1.0);
 }
 public double calculateConsumption(double distance){
    return distance*3;
 }
}
