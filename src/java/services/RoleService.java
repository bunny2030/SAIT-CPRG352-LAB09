package services;

import dataaccess.RoleDB;
import java.util.List;
import models.Role;

public class RoleService {

    public List<Role> getAll() throws Exception {
        RoleDB roleDB = new RoleDB();
        List<Role> roles = roleDB.getAll();
        return roles;
    }
    
    public Role get(int role_id) throws Exception {
        RoleDB roleDB = new RoleDB();
        Role role = roleDB.get(role_id);
        return role;
    }
}