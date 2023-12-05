public class UsersArrayList implements UsersList{
    private int size = 0;
    private int capacity = 10;
    User[] usersArrayList = new User[capacity];

    public  UsersArrayList() {}

    @Override
    public void addUser(User user) {
        if(size == capacity) {
            capacity *= 2;
            User[] usersArrayListTemp = new User[capacity];
            for(int i = 0; i < size; ++i) {
                usersArrayListTemp[i] = usersArrayList[i];
            }
            usersArrayList = usersArrayListTemp;
        }
        usersArrayList[size] = user;
        size++;
    }

    @Override
    public User getUserById(int id) {
        for(User user : usersArrayList) {
            if (user != null && user.getIdentifier() == id) return user;
        }
        return null;
    }

    @Override
    public User getUserByIndex(int index) throws UserNotFoundException {
        if (index >= 0 && index < size) return usersArrayList[index];
        throw new UserNotFoundException("Index out of bounds!");
    }

    @Override
    public int getCountUsers() { return size; }
}
