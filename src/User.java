/* Create a User object and store information about
 * the user: user's name, what they're using the
 * fixture (stall or urinal) for, and a predicted
 * amount of time they will use the restroom.
 * @author Uijin Cho
 */
import java.util.*;
public class User {
    private String name;
    private int usageType;
    private int usageTime;
    
    /* Constructor to create a User object
     * @param name         the user's name
     * @param usageType    what the user is using the bathroom for (1 - pee, 2 - poo, 3 - other purposes)
     * @param usageTime    how long the user is predicted to use the bathroom for based on usageType
     */
    public User(String name, int usageType, int usageTime) {
        this.name = name;
        this.usageType = usageType;
        this.usageTime = usageTime;
    }

    /* Getter that gets user's name
     * @return name         the user's name
     */
    public String getName() {
        return name;
    }

    /* Setter that sets user's name
     * @param name         the user's name
     */
    public void setName(String str) {
        name = str;
    }

    /* Getter that gets what the user is using the bathroom for
     * @return usageType   1, 2, or 3 what they're using the bathroom for
     */
    public int getUsageType() {
        return usageType;
    }

    /* Setter that sets what the user is using the bathroom for
     * @param usageType   1, 2, or 3 what they're using the bathroom for
     */
    public void setUsageType(int num) {
        usageType = num;
    }

    /* Getter that gets the predicted time the user will use the bathroom for
     * @return usageTime  predicted time for how long the user will use the bathroom for
     */
    public int getUsageTime()  {
        return usageTime;
    }

    /* Setter that sets the predicted time the user will use the bathroom for
     * @param usageTime  predicted time for how long the user will use the bathroom for
     */
    public void setUsageTime(int num) {
        usageTime = num;
    }
}