public class Main {

    public static void main(String[] args) {

        Position base = new Position(0, 0);

        Drone d1 = new StandardDrone(base);
        Drone d2 = new ExpressDrone(base);
        Drone d3 = new HeavyDrone(base);

        Position p1 = new Position(10, 5);
        Position p2 = new Position(20, 10);

        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d3);

        if (d1.canFlyTo(p1)) {
            d1.flyTo(p1);
        }

        if (d2.canFlyTo(p2)) {
            d2.flyTo(p2);
        }

        if (d3.canFlyTo(p1)) {
            d3.flyTo(p1);
        }

        System.out.println("After flight:");
        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d3);
    }
}