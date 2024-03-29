public class Program {
    public static void main(String[] args) {
        User a = new User("Matvey", 1000);
        User b = new User( "Aboba", 500);
        User c = new User( "Mama", 500);
        User d = new User( "Lala", 500);


        UsersArrayList arrayList = new UsersArrayList();

        arrayList.addUser(a);
        arrayList.addUser(b);
        arrayList.addUser(c);
        arrayList.addUser(d);

        System.out.println(arrayList.getUserById(1));
        System.out.println(arrayList.getUserById(2));
        System.out.println(arrayList.getUserById(3));
        System.out.println(arrayList.getUserById(4));

        try {
            System.out.println(arrayList.getUserByIndex(0));
            System.out.println(arrayList.getUserByIndex(1));
            System.out.println(arrayList.getUserByIndex(2));
            System.out.println(arrayList.getUserByIndex(3));
            System.out.println(arrayList.getUserByIndex(4));          //Unhandled exception: UserNotFoundException
        } catch (UserNotFoundException s) {System.out.println(s.getMessage());}
//        System.out.println(arrayList.getCountUsers());
    }
}
