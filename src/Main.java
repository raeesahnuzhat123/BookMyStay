import java.util.*;

public class Main {

    Map<String,Integer> inventory = new HashMap<>();
    Map<String,String> reservations = new HashMap<>();
    Stack<String> rollbackStack = new Stack<>();

    void cancelBooking(String reservationId) {

        if(!reservations.containsKey(reservationId)) {
            System.out.println("Invalid reservation.");
            return;
        }

        String roomType = reservations.get(reservationId);

        rollbackStack.push(reservationId);

        inventory.put(roomType, inventory.get(roomType) + 1);

        reservations.remove(reservationId);

        System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);
        System.out.println();
        System.out.println("Rollback History (Most Recent First):");

        while(!rollbackStack.isEmpty()) {
            System.out.println("Released Reservation ID: " + rollbackStack.pop());
        }

        System.out.println();
        System.out.println("Updated " + roomType + " Room Availability: " + inventory.get(roomType));
    }

    public static void main(String[] args) {

        Main m = new Main();

        m.inventory.put("Single",5);

        m.reservations.put("Single-1","Single");

        System.out.println("Booking Cancellation");

        m.cancelBooking("Single-1");
    }
}