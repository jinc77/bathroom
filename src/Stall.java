/* Bathroom subclass Stall that can store information about
 * the bathroom fixture: what number stall,
 * whether it's occupied, how many times it was used,
 * and who is currently using the stall.
 * @author Uijin Cho
 */
public class Stall extends Bathroom {
    /* Constructor to create a Stall object
     * @param id           the fixture number
     */
    public Stall(String id) {
        super(id);
    }
}
