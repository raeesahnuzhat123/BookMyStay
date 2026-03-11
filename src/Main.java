import java.util.*;

class Reservation {
    String id;
    String guest;
    String roomType;

    Reservation(String id, String guest, String roomType) {
        this.id = id;
        this.guest = guest;
        this.roomType = roomType;
    }
}

public class Main {

    List<Reservation> history = new ArrayList<>();

    void addReservation(String id, String guest, String roomType) {
        history.add(new Reservation(id, guest, roomType));
    }

    void showHistory() {
        for (Reservation r : history) {
            System.out.println(r.id + " " + r.guest + " " + r.roomType);
        }
    }

    void generateReport() {
        Map<String, Integer> report = new HashMap<>();

        for (Reservation r : history) {
            report.put(r.roomType, report.getOrDefault(r.roomType, 0) + 1);
        }

        for (String type : report.keySet()) {
            System.out.println(type + " " + report.get(type));
        }
    }

    public static void main(String[] args) {

        Main m = new Main();

        m.addReservation("R101", "Aman", "Standard");
        m.addReservation("R102", "Rahul", "Deluxe");
        m.addReservation("R103", "Neha", "Standard");
        m.addReservation("R104", "Priya", "Suite");

        m.showHistory();
        m.generateReport();
    }
}