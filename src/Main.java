import java.util.*;

public class Main {

    // Queue for booking requests (FIFO)
    private Queue<String> bookingQueue = new LinkedList<>();

    // Inventory of room types
    private Map<String, Integer> inventory = new HashMap<>();

    // Map room type -> allocated room IDs
    private Map<String, Set<String>> allocatedRooms = new HashMap<>();

    // Set to store all allocated room IDs (ensures uniqueness)
    private Set<String> usedRoomIds = new HashSet<>();

    // Counter for generating unique IDs
    private int roomCounter = 1;

    // Add booking request
    public void addBookingRequest(String roomType) {
        bookingQueue.offer(roomType);
    }

    // Process booking requests
    public void processBookings() {
        while (!bookingQueue.isEmpty()) {

            String roomType = bookingQueue.poll();
            System.out.println("Processing booking for: " + roomType);

            if (!inventory.containsKey(roomType) || inventory.get(roomType) <= 0) {
                System.out.println("No rooms available for type: " + roomType);
                continue;
            }

            // Generate unique room ID
            String roomId = generateRoomId(roomType);

            // Record allocated room
            allocatedRooms.putIfAbsent(roomType, new HashSet<>());
            allocatedRooms.get(roomType).add(roomId);

            // Decrement inventory
            inventory.put(roomType, inventory.get(roomType) - 1);

            System.out.println("Reservation Confirmed. Room Allocated: " + roomId);
        }
    }

    // Generate unique room ID
    private String generateRoomId(String roomType) {
        String roomId;

        do {
            roomId = roomType.substring(0, 1).toUpperCase() + roomCounter++;
        } while (usedRoomIds.contains(roomId));

        usedRoomIds.add(roomId);
        return roomId;
    }

    // Display allocation details
    public void displayAllocations() {
        System.out.println("\nRoom Allocation Summary:");
        for (String type : allocatedRooms.keySet()) {
            System.out.println(type + " -> " + allocatedRooms.get(type));
        }
    }

    public static void main(String[] args) {

        Main service = new Main();

        // Initialize inventory
        service.inventory.put("Standard", 2);
        service.inventory.put("Deluxe", 1);
        service.inventory.put("Suite", 1);

        // Add booking requests
        service.addBookingRequest("Standard");
        service.addBookingRequest("Deluxe");
        service.addBookingRequest("Standard");
        service.addBookingRequest("Suite");
        service.addBookingRequest("Deluxe"); // should fail if inventory empty

        // Process bookings
        service.processBookings();

        // Show allocation result
        service.displayAllocations();
    }
}