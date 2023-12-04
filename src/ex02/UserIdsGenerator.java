public class UserIdsGenerator {
    static private int lastId = 1;
    private static UserIdsGenerator instance;
    private UserIdsGenerator() {}
    public static UserIdsGenerator getInstance() {
        if (instance == null) instance = new UserIdsGenerator();
        return instance;
    }
    int generateId() {
        return lastId++;
    }
    int getId() { return lastId; }
}
