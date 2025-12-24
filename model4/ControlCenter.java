package Control;
import java.util.List;
import java.util.ArrayList;
import GEO.Position;
import GEO.Map;
import DroneHierarchi.Drone;
import OrderSystem.Order;
public class ControlCenter {
  private List<Drone> fleet;
  private List<Order> pendingOrders;
  private List<Order> processedOrerds;
  private Position base;
  private Map map;
  private static int numberOfDeliveries=0;
  private static double totalDistance=0;
  private static double energyStandard=0;
  private static double energyExpress=0;
  private static double energyHeavy=0;

  public ControlCenter(Position base,Map map){
    this.base=base;
    this.map=map;
    this.fleet=new ArrayList<>();
    this.pendingOrders=new ArrayList<>();
    this.processedOrerds=new ArrayList<>();
  }
  public void addDrone(Drone d){
    this.fleet.add(d);
  }
  public Drone findDroneForOrder(Order order){
    for(Drone d : this.fleet){
        if(d.getstuts().compareTo("AVAILABLE")!=0){
            continue;
        }
        if(d.getcapacity()<order.getdeliverable().getWeight()){
            continue;
        }
        Position destination=order.getdeliverable().getDestination();
        if(!map.isAllowed(destination)){
            continue;
        }
        if(!d.canFlyTo(destination)){
            continue;
        }
        return d;
    }
    return null;
  }
  public double calculateDeliveryCost(Order order,Drone d){
    Position destination=order.getdeliverable().getDestination();
    double distance=d.getposition().distanceTo(destination)*2;
    double consumption=d.calculateConsumption(distance);
    double operationCost=(distance*0.1)+(consumption*0.02)+20;
    double insurance=Math.max(order.getcost()*0.02,10);
    if(order.geturgency().compareTo("EXPRESS")==0){
        insurance=insurance+20;
    }
    return operationCost+insurance;
  }
  public boolean assignOrder(Order order){
    Drone dr=findDroneForOrder(order);
    if(dr==null){
        this.pendingOrders.add(order);
        return false;
    }
    double cost=calculateDeliveryCost(order, dr)+order.getcost();
    order.setcost(cost);
    order.setstatus("IN PROGRESS");
    dr.setstatus("IN DELIVERY");
    this.processedOrerds.add(order);
    return true;
  }
  public void completedDelivery(Drone dr,Order order){
    dr.FlyTo(order.getdeliverable().getDestination());
    order.setstatus("DELIVERED");
    dr.setstatus("RETURN TO BASE");
    this.processedOrerds.remove(order);
    numberOfDeliveries++;
  }
  public void returnToBase(Drone dr){
    dr.FlyTo(this.base);
    dr.setstatus("AVAILABLE");
  }
  public void failOrder(Order order,Drone dr){
    dr.FlyTo(order.getdeliverable().getDestination());
    order.setstatus("FAILED");
    dr.setstatus("RETURN TO BASE");
    this.pendingOrders.add(order);
    this.processedOrerds.remove(order);
  }
  public List<Order> getpendingOrders(){
    return pendingOrders;
  }
  public int getnumberOfDeleveres(){
    return numberOfDeliveries;
  }
  public double gettotaldistance(){
    for(Drone d : this.fleet){
      totalDistance=totalDistance+d.gettotaldistance();
    }
    return totalDistance;
  }
  public double getenergyStandard(){
    for(Drone d : this.fleet){
      if(d.getmodel().compareTo("Standard")==0){
         energyStandard=energyStandard+d.calculateConsumption(d.gettotaldistance());
      }
    }
    return energyStandard;
  }
  public double getenergyExpress(){
    for(Drone d : this.fleet){
      if(d.getmodel().compareTo("Express")==0){
         energyExpress=energyExpress+d.calculateConsumption(d.gettotaldistance());
      }
    }
    return energyExpress;
  }
  public double getenergyHeavy(){
    for(Drone d : this.fleet){
      if(d.getmodel().compareTo("Heavy")==0){
         energyHeavy=energyHeavy+d.calculateConsumption(d.gettotaldistance());
      }
    }
    return energyHeavy;
  }
}
