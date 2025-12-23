import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Drone {

    private static int counter = 0;

    protected int id;
    protected Position position;
    protected double battery;
    protected String model;
    protected double speed;
    protected double capacity;
    protected String status;
    protected double totalDistance;
    protected List<Position> positionHistory;

    public Drone(Position position, String model, double speed, double capacity) {
        this.id = ++counter;
        this.position = position;
        this.model = model;
        this.speed = speed;
        this.capacity = capacity;
        this.battery = 100.0;
        this.status = "AVAILABLE";
        this.totalDistance = 0.0;
        this.positionHistory = new ArrayList<>();
        this.positionHistory.add(position);
    }

    public abstract double calculateConsumption(double distance);

    public boolean canFlyTo(Position destination) {
        double distance = position.distanceTo(destination) * 2;
        double consumption = calculateConsumption(distance);
        return battery >= consumption;
    }

    public void flyTo(Position destination) {
        double distance = position.distanceTo(destination);
        double consumption = calculateConsumption(distance);

        if (battery < consumption) {
            return;
        }

        battery -= consumption;
        totalDistance += distance;
        position = destination;
        positionHistory.add(destination);
    }

    public void recharge(double percentage) {
        battery += percentage;
        if (battery > 100) {
            battery = 100;
        }
    }

    public double getBattery() {
        return battery;
    }

    public double getCapacity() {
        return capacity;
    }

    public Position getPosition() {
        return position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    @Override
    public String toString() {
        return "Drone{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", battery=" + battery +
                ", speed=" + speed +
                ", capacity=" + capacity +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Drone)) return false;
        Drone drone = (Drone) o;
        return id == drone.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
