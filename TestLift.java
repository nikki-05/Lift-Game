import java.util.*;

public class TestLift {
    static Scanner sc = new Scanner(System.in);
    static Building building = new Building();
    static String ANSI_RESET = "\u001B[0m";
    static String ANSI_GREEN = "\u001B[32m";
    static String ANSI_RED = "\u001B[31m";
    static String ANSI_YELLOW = "\u001B[33m";
    static String ANSI_BLUE = "\u001B[34m";
    static String ANSI_PURPLE = "\u001B[35m";

    public static void main(String[] args) throws InterruptedException {
        building.setup();
        showMenu();
    }

    static void showMenu() throws InterruptedException {
        while (true) {
            System.out.println("\n" + ANSI_BLUE + "============== 🚀 Lift System Menu ============== " + ANSI_RESET);
            System.out.println("1️⃣  Request Lift");
            System.out.println("2️⃣  Admin Panel 🔐");
            System.out.println("3️⃣  Help / Info 🆘");
            System.out.println("4️⃣  Emergency Call 📞");
            System.out.println("5️⃣  Exit ❌");
            System.out.print("\nEnter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    handleLiftRequest();
                    break;
                case 2:
                    enterAdminPanel();
                    break;
                case 3:
                    showHelp();
                    break;
                case 4:
                    triggerEmergencyCall();
                    break;
                case 5:
                    System.out.println(ANSI_RED + "\uD83D\uDEAA Exiting system. Stay safe!" + ANSI_RESET);
                    return;
                default:
                    System.out.println(ANSI_YELLOW + "⚠️ Invalid choice! Try again." + ANSI_RESET);
            }
        }
    }

    static void handleLiftRequest() throws InterruptedException {
        System.out.print("\nEnter your destination floor (" + building.getMinFloor() + " to " + building.getMaxFloor() + "): ");
        int floor = sc.nextInt();
        if (!building.isValidFloor(floor)) {
            System.out.println(ANSI_RED + "❌ Invalid floor!" + ANSI_RESET);
            return;
        }
        building.getLift().moveTo(floor);
        building.getLift().simulateDoors();
    }

    static void enterAdminPanel() {
        System.out.print("\n🔐 Enter admin password: ");
        String pwd = sc.next();
        if (!pwd.equals("admin123")) {
            System.out.println(ANSI_RED + "❌ Incorrect password!" + ANSI_RESET);
            return;
        }

        while (true) {
            System.out.println("\n" + ANSI_GREEN + "====== Admin Panel ======" + ANSI_RESET);
            System.out.println("1. Set floor range 🏢");
            System.out.println("2. Set lift speed ⚙️");
            System.out.println("3. Shutdown lift system 🔻");
            System.out.println("4. Back to main menu 🔙");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    building.setFloorRange();
                    break;
                case 2:
                    building.setLiftSpeed();
                    break;
                case 3:
                    System.out.println(ANSI_RED + "\uD83D\uDEA8 EMERGENCY: Lift system shutting down..." + ANSI_RESET);
                    System.exit(0);
                case 4:
                    return;
                default:
                    System.out.println("❗ Invalid admin option.");
            }
        }
    }

    static void showHelp() {
        System.out.println("\n📖 " + ANSI_YELLOW + "Help Center" + ANSI_RESET);
        System.out.println("- Use option 1 to call a lift.");
        System.out.println("- Admin panel allows changing floors and speed.");
        System.out.println("- Use emergency call in case of danger or malfunction.");
    }

    static void triggerEmergencyCall() {
        System.out.println("\n" + ANSI_RED + "\u0007🚨 EMERGENCY CALL INITIATED! 🚨" + ANSI_RESET);

        System.out.println(ANSI_RED + "\n" +
            "+--------------------------------------+\n" +
            "|              🚨 ALERT 🚨              |\n" +
            "|          SYSTEM IS IN DANGER!        |\n" +
            "|     Help has been notified ⛑️         |\n" +
            "|   Location: Floor " + building.getLift().getCurrentFloor() + "                 |\n" +
            "+--------------------------------------+" + ANSI_RESET);

        System.out.print("\007");
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}
        System.out.print("\007");

        System.out.println("\n" + ANSI_YELLOW + "📍 Location: Lift Building, Floor " + building.getLift().getCurrentFloor() + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "⏰ Timestamp: " + new Date() + ANSI_RESET);
        System.out.println(ANSI_GREEN + "🆘 Alert sent successfully. Help is on the way." + ANSI_RESET);
    }
}