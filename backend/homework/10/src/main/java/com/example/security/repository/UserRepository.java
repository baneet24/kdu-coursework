package com.example.security.repository;;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import com.example.security.model.User;
@Repository
public class UserRepository {

    List<User> userList = new ArrayList<>(Arrays.asList(new User("john", "pass123", "john@gmail.com"),
            new User("Baneet", "Baneet@123", "baneet.singh@kickdrumtech.com")));

        public List<User> getAllUsers() {
            return userList;
        }

        public Optional<User> getUserByName(String userName) {
            return userList.stream()
                    .filter(user -> user.getUserName().equals(userName))
                    .findFirst();
        }

        public void addUser(User user) {
            userList.add(user);
        }
}
