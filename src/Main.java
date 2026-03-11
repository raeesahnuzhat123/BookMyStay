import java.util.*;

class BookingService {

    Map<String, Integer> inventory = new HashMap<>();
    Map<String, Integer> counter = new HashMap<>();

    BookingService() {
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);

        counter.put("Single", 0);
        counter.put("Double", 0);
        counter.put("Suite", 0);
    }

    public synchronized void book(String guest, String type) {
        if (inventory.get(type) > 0) {

            int id = counter.get(type) + 1;
            counter.put(type, id);

            inventory.put(type, inventory.get(type) - 1);

            System.out.println("Booking confirmed for Guest: " + guest + ", Room ID: " + type + "-" + id);
        }
    }

    public void showInventory() {
        System.out.println();
        System.out.println("Remaining Inventory:");
        System.out.println("Single: " + inventory.get("Single"));
        System.out.println("Double: " + inventory.get("Double"));
        System.out.println("Suite: " + inventory.get("Suite"));
    }
}

class BookingThread extends Thread {

    BookingService service;
    String guest;
    String type;

    BookingThread(BookingService service, String guest, String type) {
        this.service = service;
        this.guest = guest;
        this.type = type;
    }

    public void run() {
        service.book(guest, type);
    }
}

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("Concurrent Booking Simulation");

        BookingService service = new BookingService();

        Thread t1 = new BookingThread(service, "Abhi", "Single");
        Thread t2 = new BookingThread(service, "Vanamathi", "Double");
        Thread t3 = new BookingThread(service, "Kural", "Suite");
        Thread t4 = new BookingThread(service, "Subha", "Single");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        service.showInventory();
    }
}