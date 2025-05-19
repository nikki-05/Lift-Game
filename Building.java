import java.util.*;

public class Building {
    private int minFloor = 0;
    private int maxFloor = 10;
    private Lift lift;
    private Scanner sc = new Scanner(System.in);

    public Building() {
        this.lift = new Lift();
    }

    public void setup() {
        System.out.println("\u2699 Default floor range is " + minFloor + " to " + maxFloor);
        System.out.println("\uD83D\uDD04 You can change it later in the Admin Panel.");
    }

    public boolean isValidFloor(int floor) {
        return floor >= minFloor && floor <= maxFloor;
    }

    public void setFloorRange() {
        System.out.print("\nEnter new minimum floor: ");
        minFloor = sc.nextInt();
        System.out.print("Enter new maximum floor: ");
        maxFloor = sc.nextInt();
        System.out.println("\u2705 Floor range updated: " + minFloor + " to " + maxFloor);
    }

    public void setLiftSpeed() {
        System.out.print("\nEnter new lift speed in ms per floor (e.g., 500): ");
        int newSpeed = sc.nextInt();
        lift.setSpeed(newSpeed);
    }

    public int getMinFloor() {
        return minFloor;
    }

    public int getMaxFloor() {
        return maxFloor;
    }

    public Lift getLift() {
        return lift;
    }
}