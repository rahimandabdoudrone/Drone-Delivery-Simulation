public class StandardDrone extends Drone {

    public StandardDrone(Position position) {
        super(position, "StandardDrone", 30, 1.0);
    }

    @Override
    public double calculateConsumption(double distance) {
        return distance * 3; // 3% per km
    }
}
