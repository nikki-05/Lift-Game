import java.util.Scanner;

public class TestLift {
    public static void main(String[] args) throws InterruptedException {
    Scanner sc= new Scanner(System.in);
        System.out.println("Welcome");
        Building building = new Building();

        while(true) {
            System.out.print("Enter your current floor: ");
            int currentFloor = sc.nextInt();

            if (currentFloor < 0) {
                System.out.println("There's no floor in negative :( try again");
                continue;
            }
            System.out.print("Enter your destination floor: ");
            int destinationFloor = sc.nextInt();
            if(currentFloor==destinationFloor){
                System.out.println("You are at the same floor ");
            }

            if (destinationFloor > 30) {
                System.out.println("Wanna jump out of top floor? Enter again :)");
                continue;
            }

            building.requestLift(currentFloor, destinationFloor);
        }
    }
}
