import java.util.*;
public class User {
    private String name;
    private int usageType;
    private int usageTime;

    public User(String name, int usageType, int usageTime) {
        this.name = name;
        this.usageType = usageType;
        this.usageTime = usageTime;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String str) {
        name = str;
    }

    public int getUsageType() {
        return usageType;
    }

    public void setUsageType(int num) {
        usageType = num;
    }
    
    public int getUsageTime()  {
        return usageTime;
    }
    
    public void setUsageTime(int num) {
        usageTime = num;
    }
}