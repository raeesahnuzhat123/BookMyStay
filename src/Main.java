import java.util.*;

class Service {
    String name;
    double cost;

    Service(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }
}

public class Main {

    Map<String, List<Service>> reservationServices = new HashMap<>();

    public void addService(String reservationId, Service service) {
        reservationServices.putIfAbsent(reservationId, new ArrayList<>());
        reservationServices.get(reservationId).add(service);
    }

    public double calculateTotalCost(String reservationId) {
        double total = 0;
        List<Service> services = reservationServices.get(reservationId);
        if (services != null) {
            for (Service s : services) {
                total += s.cost;
            }
        }
        return total;
    }

    public void displayServices(String reservationId) {
        List<Service> services = reservationServices.get(reservationId);
        if (services == null) {
            System.out.println("No services selected");
            return;
        }

        for (Service s : services) {
            System.out.println(s.name + " - " + s.cost);
        }

        System.out.println("Total Add-On Cost: " + calculateTotalCost(reservationId));
    }

    public static void main(String[] args) {
        Main manager = new Main();

        String reservationId = "R101";

        manager.addService(reservationId, new Service("Breakfast", 500));
        manager.addService(reservationId, new Service("Airport Pickup", 1200));
        manager.addService(reservationId, new Service("Spa Access", 2000));

        manager.displayServices(reservationId);
    }
}