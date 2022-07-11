package services;

import dataaccess.RoleDB;
import dataaccess.UserDB;
import java.util.List;
import models.Role;
import models.User;

public class UserService {
    
    public List<User> getAll() throws Exception {
        UserDB userDB = new UserDB();
        List<User> users = userDB.getAll();
        return users;
    }

    public User get(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        return user;
    }

    public void insert(String email, boolean active, String first_name, String last_name, String password, int role) throws Exception {
        User user = new User(email, active, first_name, last_name, password);
        
        RoleDB roleDB = new RoleDB();
        Role roleObj = roleDB.get(role);
        
        user.setRole(roleObj);
        UserDB userDB = new UserDB();
        userDB.insert(user);
    }

    public void update(String email, boolean active, String first_name, String last_name, String password, int role) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        
        user.setActive(active);
        user.setFirstName(first_name);
        user.setLastName(last_name);
        user.setPassword(password);
        
        RoleDB roleDB = new RoleDB();
        user.setRole(roleDB.get(role));
        userDB.update(user);
    }

    public void delete(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        userDB.delete(user);
    }
}