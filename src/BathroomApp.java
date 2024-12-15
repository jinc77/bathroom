/*
 * @author Pauline Kang
 * runs the main code as well as contains methods that needed to create functions
 */

 import java.util.*;

public class BathroomApp {
    private static final int stalls = 10;
    private static final int urinals = 3;
    private Map<String, Bathroom> fixtures;
    private Queue<User> waitList;
    private Map<String,Timer> timers;

    /*
     * constructor
     */
    public BathroomApp(){
        fixtures = new LinkedHashMap<>();
        waitList = new LinkedList();
        timers = new HashMap<>();

        //assigns the number of urinals
        for(int i = 1; i<= urinals; i++){
            fixtures.put("Urinals " + i, new Urinal ("Urinal " + i));
        }

        //assigns the number of stalls;
        for(int i = 1; i<= stalls; i++){
            fixtures.put("Stall" + i, new Stall ("Stall " + i));
        }
    }
    /*
     * find the fixtures that are available use with the usageType
     * @param usageType -> what use do they need bathroom for
     * @return available bathroom
     */

    private Bathroom findAvailableUse(int usageType){
        //checking if they need to (stall))
        if(usageType == 2 || usageType == 3){
        return fixtures.values().stream()
        .filter(f ->!f.isOccupied())
        .filter(f -> f.getId().contains("Stall"))
        .min(Comparator.comparingInt(Bathroom::getUsageCount))
        .orElse(null);
        }

        //checking for (urinal)
        else{
            return fixtures.values().stream()
        .filter(f ->!f.isOccupied())
        .min(Comparator.comparingInt(Bathroom::getUsageCount))
        .orElse(null);
        }

    }
    /*
     * adding a user to the waitlist or to a stall
     * @param user - the user in line
     */
    
    private void addUser(User user){
        waitList.add(user);
        System.out.println("All fixtures are occupied. " + user.getName() + " has been added to the wait list");
    }
    
    /*
     * generating wait time to wait for the people in line after using the timer
     */

    private void waitTimes(){
        int est = 0;
        System.out.println("Wait Times: ");
        for(User user : waitList){
            est += user.getUsageType();
            System.out.println(user.getName() + ": ~" + est + " minutes");
        }

    }

    /*
     * displaying the status of each stall/urinal
     * automatically prints everytime
     */

    private void displayStatus() {
        System.out.println("\nFixture Status:");
        fixtures.values().forEach(fixture -> {
            String status = fixture.isOccupied() ? "Occupied by " + fixture.getCurrentUser() : "Available";
            System.out.println(fixture.getId() +  " - " + status);
        });
        if (!waitList.isEmpty()) {
            System.out.println("\nQueue: ");
            waitList.forEach(user -> System.out.println(user.getName()));
        } else {
            System.out.println("\nQueue: Empty");
        }
    }

    /*
     * using a bathroom and occupying when needed or available
     * @param name - the name of the user
     * @param usageType - the usageType of the person who is using the bathroom
     */

    private void useBathroom (String name, int usageType){
        int usageTime = switch (usageType){
            case 1 -> 2;
            case 2 -> 5;
            case 3 -> 10;
            default -> throw new IllegalArgumentException("Invalid usage type.");
        };
        // generating next users and setting the time
        for(Bathroom fixture: fixtures.values()){
            if(name.equals(fixture.getCurrentUser())){
                System.out.println(name + " has finished using " + fixture.getId() + ".");
                fixture.vacate();
                timers.get(name).cancel();
                timers.remove(name);
                if(!waitList.isEmpty()){
                    User nextUser = waitList.poll();
                    useBathroom(nextUser.getName(), nextUser.getUsageType());
                }
                displayStatus();
                return;
            }
        }
        //finding an available bathroom and directing a user into the bathroom
        Bathroom availableFixture = findAvailableUse(usageType);
        if (availableFixture != null) {
            System.out.println(name + " directed to " + availableFixture.getId() + " for ~" + usageTime + " minutes.");
            availableFixture.use(name);

            Timer timer = new Timer();
            timers.put(name, timer);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    availableFixture.vacate();
                    System.out.println(availableFixture.getId() + " is now available.");
                    timers.remove(name);
                    if (!waitList.isEmpty()) {
                        User nextUser = waitList.poll();
                        useBathroom(nextUser.getName(), nextUser.getUsageType());
                    }
                }
            }, usageTime * 60 * 1000L); // convert minutes to milliseconds
        } else {
            addUser(new User(name, usageType, usageTime));
        }

        displayStatus();
        waitTimes();
    }

    /*
     * main method
     * prints the beginning page
     */
    public static void main(String[] args) {
        BathroomApp app = new BathroomApp();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Jin's Bathroom App!");

        while (true) {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();

            // Check if the name is already in use in any fixture
            boolean wasInUse = false;
            for (Bathroom fixture : app.fixtures.values()) {
                if (name.equals(fixture.getCurrentUser())) {
                    System.out.println(name + " is currently using " + fixture.getId() + ". Signing them out.");
                    fixture.vacate();
                    app.timers.get(name).cancel();
                    app.timers.remove(name);
                    wasInUse = true;

                    // Auto-assign the next person in queue, if any
                    if (!app.waitList.isEmpty()) {
                        User nextUser = app.waitList.poll();
                        System.out.println("Assigning " + nextUser.getName() + " to " + fixture.getId());
                        app.useBathroom(nextUser.getName(), nextUser.getUsageType());
                    }
                    break; // Exit the loop after signing out the user
                }
            }

            // If the name was already in use, skip asking for usage type and continue
            if (wasInUse) {
                app.displayStatus();
                continue;
            }

            // Ask for usage type only if the name was not already in use
            System.out.print("Enter 1 to pee, 2 to poo, or 3 for other purposes: ");
            int usageType = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            app.useBathroom(name, usageType);
        }
    }

    }

    
