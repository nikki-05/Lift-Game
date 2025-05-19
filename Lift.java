public class Lift {
    private int currentFloor = 0;
    private int speed = 1000; 

    public void moveTo(int targetFloor) throws InterruptedException {
        if (targetFloor == currentFloor) {
            System.out.println("\uD83D\uDE92 Lift is already on floor " + targetFloor);
            return;
        }

        System.out.println("\n\uD83D\uDE92 Lift moving from floor " + currentFloor + " to " + targetFloor);

        while (currentFloor != targetFloor) {
            if (currentFloor < targetFloor) {
                currentFloor++;
            } else {
                currentFloor--;
            }
            System.out.println("\u2B06 Currently at floor: " + currentFloor);
            Thread.sleep(speed);
        }
        System.out.println("\u2705 Arrived at floor: " + currentFloor);
    }

    public void simulateDoors() throws InterruptedException {
        System.out.println("\n\uD83D\uDEAA Doors opening...");
        Thread.sleep(500);
        System.out.println("\uD83D\uDEAA Doors open. Please enter or exit.");
        Thread.sleep(1000);
        System.out.println("\uD83D\uDEAA Doors closing...");
        Thread.sleep(500);
    }

    public void setSpeed(int ms) {
        this.speed = ms;
        System.out.println("\uD83D\uDD04 Lift speed updated to " + speed + " ms per floor");
    }

    public int getCurrentFloor() {
        return currentFloor;
    }
}
