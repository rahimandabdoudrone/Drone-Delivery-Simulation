import java.util.ArrayList;
import java.util.List;

public class Map {

    private List<DeliveryZone> deliveryZones;
    private List<NoFlyZone> noFlyZones;

    public Map() {
        deliveryZones = new ArrayList<>();
        noFlyZones = new ArrayList<>();
    }

    public void addDeliveryZone(DeliveryZone z) {
        deliveryZones.add(z);
    }

    public void addNoFlyZone(NoFlyZone z) {
        noFlyZones.add(z);
    }

    public boolean isAllowed(Position p) {
        for (NoFlyZone z : noFlyZones) {
            if (z.contains(p)) {
                return false;
            }
        }
        return true;
    }

    public boolean isForbidden(Position p) {
        for (NoFlyZone z : noFlyZones) {
            if (z.contains(p)) {
                return true;
            }
        }
        return false;
    }
}
