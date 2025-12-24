package simulator;
import Control.ControlCenter;
import DroneHierarchi.Drone;
import OrderSystem.Order;
import GEO.DeliveryZone;
import GEO.Map;
import GEO.NoFlyZone;
import GEO.Position;
public class Similation {
  private ControlCenter coc;
  private static int day_duration=480;

  public Similation(ControlCenter cc){
    this.coc=cc;
  }
  public void run(){
    for(int minute=0;minute<480;minute++){
        for(Order order : this.coc.getpendingOrders()){
            Position destination=order.getdeliverable().getDestination();
            if(this.coc.getmap().isForbidden(destination)){
                System.out.println("order of "+destination.toString()+" is forbiden");
                this.coc.getpendingOrders().remove(order);
            }else{
                boolean b=this.coc.assignOrder(order);
                Drone drone=order.getdrone();
                if(b==true){
                    if(drone.getposition().equals(destination)==1){
                        this.coc.completedDelivery(drone,order);
                    }else{
                        if(drone.getmodel().compareTo("Express")==0){
                            drone.FlyTo(drone.getposition().moveTo(destination,drone.getspeed()/60));
                        }
                        if(drone.getmodel().compareTo("Heavy")==0){
                            drone.FlyTo(drone.getposition().moveTo(destination,drone.getspeed()/60));
                        }
                        if(drone.getmodel().compareTo("Standard")==0){
                            drone.FlyTo(drone.getposition().moveTo(destination,drone.getspeed()/60));
                        }
                    }
                }
                if(b==false && drone!=null && drone.getstuts().compareTo("IN DELIVERY")==0){
                    if(drone.getposition().equals(destination)==1){
                        this.coc.completedDelivery(drone,order);
                    }else{
                        if(drone.getmodel().compareTo("Express")==0){
                            drone.FlyTo(drone.getposition().moveTo(destination,drone.getspeed()/60));
                        }
                        if(drone.getmodel().compareTo("Heavy")==0){
                            drone.FlyTo(drone.getposition().moveTo(destination,drone.getspeed()/60));
                        }
                        if(drone.getmodel().compareTo("Standard")==0){
                            drone.FlyTo(drone.getposition().moveTo(destination,drone.getspeed()/60));
                        }
                    }
                }
                if(b==false && drone!=null && drone.getstuts().compareTo("RETURN TO BASE")==0){
                    if(drone.getposition().equals(this.coc.getbase())==1){
                        this.coc.returnToBase(drone);
                    }else{
                        if(drone.getmodel().compareTo("Express")==0){
                           drone.FlyTo(drone.getposition().moveTo(this.coc.getbase(),drone.getspeed()/60));
                        }
                        if(drone.getmodel().compareTo("Heavy")==0){
                           drone.FlyTo(drone.getposition().moveTo(this.coc.getbase(),drone.getspeed()/60));
                        }
                        if(drone.getmodel().compareTo("Standard")==0){
                            drone.FlyTo(drone.getposition().moveTo(this.coc.getbase(),drone.getspeed()/60));
                        }
                    }
                }
            }
        }
    }
    System.out.println("the number of deliveries of today : "+this.coc.getnumberOfDeleveres()+" the total distance of today: "+this.coc.gettotaldistance()+" the energy of drons standard: "+this.coc.getenergyStandard()+" the energy of drons express:"+this.coc.getenergyExpress()+" the energy of drons heavy:"+this.coc.getenergyHeavy());
  }
}
