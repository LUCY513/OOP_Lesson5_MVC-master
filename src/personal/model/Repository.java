package personal.model;

import java.util.List;

public interface Repository {
    List<User> getAllUsers();
    String CreateUser(User user);
    User readUser(String userId) throws Exception;
    User updateUser(User user) throws Exception;
    void deleteUSer(User user);


}
