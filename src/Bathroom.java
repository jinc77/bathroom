/* Bathroom abstract class that can store information about
 * the bathroom fixture: what number stall or urinal,
 * whether it's occupied, how many times it was used,
 * and who is currently using the fixture.
 * @author Uijin Cho
 */

import java.util.*;

public abstract class Bathroom {
    private String id;
    private boolean occupied;
    private int usageCount;
    private String currentUser;

    /* Constructor to create a Bathroom object in
     * child class, Stall or Urinal.
     * @param id           the fixture number
     */
    public Bathroom(String id) {
        this.id = id;
        this.occupied = false;
        this.usageCount = 0;
        this.currentUser = null;
    }

    /* Checks if fixture is occupied by a user
     * @return occupied    boolean for occupied or not
     */
    public boolean isOccupied() {
        return occupied;
    }

    /* Sets fixture to being used
     * @param userName     the name of the user using the fixture
     */
    public void use(String userName) {
        occupied = true;
        usageCount++;
        currentUser = userName;
    }

    /* Sets fixture to not being used
    */
    public void vacate() {
        occupied = false;
        currentUser = null;
    }

    /* Gets how many times a fixture has been used
     * @return usageCount   the number of times a fixture has been used
     */
    public int getUsageCount() {
        return usageCount;
    }

    /* Gets the fixture's id
     * @return id            the fixture's number id
     */
    public String getId() {
        return id;
    }

    /* Gets the fixture's current user's name
     * @return currentUser   the fixture's current user's name
     */
    public String getCurrentUser() {
        return currentUser;
    }
}

