public interface UsersList {
    void addUser(User user);
    User getUserById(int id);
    User getUserByIndex(int index) throws UserNotFoundException;
    int getCountUsers();
}
