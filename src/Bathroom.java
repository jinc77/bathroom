import java.util.*;

public abstract class Bathroom {
    private String id;
    private boolean occupied;
    private int usageCount;
    private String currentUser;

    public Bathroom(String id) {
        this.id = id;
        this.occupied = false;
        this.usageCount = 0;
        this.currentUser = null;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void use(String userName) {
        occupied = true;
        usageCount++;
        currentUser = userName;
    }

    public void vacate() {
        occupied = false;
        currentUser = null;
    }

    public int getUsageCount() {
        return usageCount;
    }

    public String getId() {
        return id;
    }

    public String getCurrentUser() {
        return currentUser;
    }
}

