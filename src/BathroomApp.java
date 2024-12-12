import java.util.*;

public class BathroomApp {
    private static final int stalls = 10;
    private static final int urinals = 3;
    private Map<String, Bathroom> fixtures;
    private Queue<User> waitList;
    private Map<String,Timer> timers;

    public BathroomApp(){
        fixtures = new LinkedHashMap<>();
        waitList = new LinkedList();
        timers = new HashMap<>();

        for(int i = 1; i<= urinals; i++){
            fixtures.put("Urinals " + i, new Urinal ("Urinal " + i));
        }

        for(int i = 1; i<= stalls; i++){
            fixtures.put("Stall" + i, new Stall ("Stall " + i));
        }
    }

    private Bathroom findAvailableUse(int usageType){
        if(usageType == 2){
        return fixtures.values().stream()
        .filter(f ->!f.isOccupied())
        .filter(f -> f.getId().contains("Stall"))
        .min(Comparator.comparingInt(Bathroom::getUsageCount))
        .orElse(null);
        }
        else{
            return fixtures.values().stream()
        .filter(f ->!f.isOccupied())
        .min(Comparator.comparingInt(Bathroom::getUsageCount))
        .orElse(null);
        }

    }
    
    private void addUser(User user){
        waitList.add(user);
        System.out.println("All fixtures are occupied. " + user.getName() + " has been added to the wait list");
    }

    private void waitTimes(){
        int est = 0;
        System.out.println("Wait Times: ");
        for(User user : waitList){
            est += user.getUsageType();
            System.out.println(user.getName() + ": ~" + est + " minutes");
        }

    }

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

    private void useBathroom (String name, int usageType){
        int usageTime = switch (usageType){
            case 1 -> 2;
            case 2 -> 5;
            case 3 -> 10;
            default -> throw new IllegalArgumentException("Invalid usage type.");
        };

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
            }, usageTime * 60 * 1000L); // Convert minutes to milliseconds
        } else {
            addUser(new User(name, usageType, usageTime));
        }

        displayStatus();
        waitTimes();
    }

    public static void main(String[] args){
        BathroomApp app = new BathroomApp();
        Scanner scanner = new Scanner(System.in);
        int usageType = 1;
        String name = "";
        System.out.println("Welcome to Jin's Bathroom App!");
        while(true){
            System.out.print("Enter your name: ");
            name = scanner.nextLine();
            System.out.print("Enter 1 to pee, 2 to poo, or 3 for other purposes: ");
            usageType = scanner.nextInt();
            scanner.nextLine();

            app.useBathroom(name, usageType);
            
        }

    }

    

    }

    
