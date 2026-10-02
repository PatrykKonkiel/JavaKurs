package pd5;

public class Main {
    public static void main(String[] args) {
        EntityStorage<Long, User> userStorage = new EntityStorage<>();

        userStorage.save(new User(67L, "Piotrek"));
        userStorage.save(new User(63L, "Kacper"));
        userStorage.save(new User(2L, "Paweł"));
        EntityStorage<String, Product> productStorage = new EntityStorage<>();

        productStorage.save(new Product("5","Telewizor"));
        productStorage.save(new Product("2","Laptop"));
        UserService userService = new UserService(userStorage);

        userService.changeUserName(67L, "Marcin");
        userStorage.deleteById(63L);
        productStorage.deleteById("5");
        userStorage.findAll();
        productStorage.findAll();
    }
}
