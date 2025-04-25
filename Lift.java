//your package here 
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Lift {
    private int currentfloor;
    private boolean isMoving;

    public Lift(int currentfloor) {
        this.currentfloor = currentfloor;
        this.isMoving = isMoving;
    }

    public int getCurrentfloor() {
        return currentfloor;
    }

    public void setCurrentfloor(int currentfloor) {
        this.currentfloor = currentfloor;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    List<Lift> list = new ArrayList<>();

    public void move(int destination) throws InterruptedException {
        System.out.println("Lift moving from floor " + currentfloor + " to " + destination);
        this.isMoving = true;

        // Move towards the destination floor
        if (destination > currentfloor) {
            for (int floor = currentfloor + 1; floor <= destination; floor++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Lift is at floor ↑" + floor );
                this.currentfloor = floor;
            }
        } else {
            for (int floor = currentfloor - 1; floor >= destination; floor--) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Lift is at floor  ↓" + floor);
                this.currentfloor = floor;
            }
        }

        this.isMoving = false;
        System.out.println("Lift arrived at your floor: " + currentfloor);
        System.out.println("Have Safe Working \uD83D\uDE0A");
    }


        public int distanceTo(int floor) {
            return Math.abs(currentfloor - floor);
        }
}
