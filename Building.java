//import your package here 
import java.util.ArrayList;
import java.util.List;

public class Building {
  private final int NO_Of_LIFTS= 3;
  private List<Lift> list ;

    public Building() {
        list = new ArrayList<>();
        System.out.println("^ _ ^ ");
        for (int i = 0; i < NO_Of_LIFTS; i++) {
            list.add(new Lift(0)); //A is on 0th/Ground Floor
            list.add(new Lift(4)); //B is on 4th floor
            list.add(new Lift(7)); //C is on 7th floor

        }
    }

        public Lift getNearestLift(int currentFloor){
            Lift near = list.get(0);
            int mini = near.distanceTo(currentFloor);

            for (Lift lift : list) {
                int distance = lift.distanceTo(currentFloor);
                if (distance < mini) {
                    near = lift;
                    mini = distance;
                }
            }
            return near;
        }

        public void requestLift(int currentFloor, int destinationFloor) throws InterruptedException {
            Lift nearestLift = getNearestLift(currentFloor);
            System.out.println("Nearest lift is at floor " + nearestLift.getCurrentfloor());
            nearestLift.move(currentFloor);
            nearestLift.move(destinationFloor);
    }
}
