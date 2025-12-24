package GEO;
import java.util.List;
import java.util.ArrayList;
public class Map {
    private List<DeliveryZone> deliveryZones;
    private List<NoFlyZone> noFlyZones;
    
    public Map(){
        this.deliveryZones=new ArrayList<>();
        this.noFlyZones=new ArrayList<>();
    }
    public void addDelzone(DeliveryZone zone){
        this.deliveryZones.add(zone);
    }
    public void addNFzone(NoFlyZone zone){
        this.noFlyZones.add(zone);
    }
    public boolean isForbidden(Position p){
        int i;
        for(i=0;i<this.noFlyZones.size();i++){
            if(this.noFlyZones.get(i).contains(p)){
                return true;
            }
        }
        return false;
    }
    public boolean isAllowed(Position p){
        return !this.isForbidden(p);
    }
}
