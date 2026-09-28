package pd5;

import lombok.RequiredArgsConstructor;


import java.util.Optional;

@RequiredArgsConstructor
public class UserService {
    private final EntityStorage<Long, User> userStorage;

    public void changeUserName(Long userId, String newName) {
        Optional<User> user = userStorage.findById(userId);

        user.ifPresentOrElse(u -> u.setName(newName), () -> {
            throw new IllegalArgumentException("User with id: " + userId + " does not exist");
        });
        System.out.println("Name has been changed to: " + newName);
    }


}
