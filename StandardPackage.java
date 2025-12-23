package OrderSystem;
import GEO.Position;
public class StandardPackage implements Deliverable {
  private double weight;
  private Position destination;
  public StandardPackage(double weight,Position destination){
    this.weight=weight;
    this.destination=destination;
  }
  public double getWeight(){
    return this.weight;
  }
  public Position getDestination(){
    return this.destination;
  }
  public String toString(){
    return "Standard Package [weight= "+this.weight+", destination= "+this.destination+"]";
  }
}
