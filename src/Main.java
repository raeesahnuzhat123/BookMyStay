import java.util.*;

class InvalidRoomTypeException extends Exception {
    InvalidRoomTypeException(String msg) {
        super(msg);
    }
}

public class Main {

    static void validateRoom(String roomType) throws InvalidRoomTypeException {
        if(!roomType.equals("Single") && !roomType.equals("Double") && !roomType.equals("Suite")) {
            throw new InvalidRoomTypeException("Booking failed: Invalid room type selected.");
        }
        System.out.println("Booking successful.");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Booking Validation");

        System.out.print("Enter guest name: ");
        String name = sc.nextLine();

        System.out.print("Enter room type (Single/Double/Suite): ");
        String roomType = sc.nextLine();

        try {
            validateRoom(roomType);
        }
        catch (InvalidRoomTypeException e) {
            System.out.println(e.getMessage());
        }

        sc.close();
    }
}