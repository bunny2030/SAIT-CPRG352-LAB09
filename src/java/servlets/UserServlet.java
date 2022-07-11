/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;
import services.RoleService;
import services.UserService;

/**
 *
 * @author Vaibhav
 */
public class UserServlet extends HttpServlet {
    String outmessage = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        try {
            List<User> users = new UserService().getAll();
            request.setAttribute("usersList", users);
        } catch (Exception e) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, e);
            request.setAttribute("message", "error");
        }
        
        
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        return;
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if(request.getParameter("action").equals("edit")){
            String ogEmail = request.getParameter("selected");
            UserService editUser = new UserService(); 
            User User = new User();
            
            try {
                User = editUser.get(ogEmail);
                request.setAttribute("editEmail", User.getEmail());
                request.setAttribute("editFName", User.getFirstName());
                request.setAttribute("editLName", User.getLastName());
                request.setAttribute("editPassword", User.getPassword());
                request.setAttribute("ogEmail", ogEmail);
            } catch (Exception e) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, e);
            }

            try {
                List<User> users = new UserService().getAll();
                request.setAttribute("usersList", users);
            }
            catch (Exception e) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, e);
                request.setAttribute("message", "error");
            }
            
            getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
            return;  
        }
        
        else if (request.getParameter("action").equals("delete")){
            request.setAttribute("message", "deleted");
            
            try {
                String ogEmail = request.getParameter("selected");
                UserService editUser = new UserService();
                new UserService().delete(ogEmail);
                
                try {
                    List<User> users = new UserService().getAll();
                    request.setAttribute("usersList", users);
                } catch (Exception e) {
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, e);
                    request.setAttribute("message", "error");
                }
                
                getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
                return;
            }
            catch (Exception e) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, e);
            }
            
            try {
            List<User> users = new UserService().getAll();
            request.setAttribute("usersList", users);
        } catch (Exception e) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, e);
            request.setAttribute("message", "error");
        }
        
            // LOAD THE JSP
            getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
            return;
        }
        
        else if (request.getParameter("action").equals("add")){
            
            String email = request.getParameter("newEmail");
            String firstName = request.getParameter("newFName");
            String lastName = request.getParameter("newLName");
            String password = request.getParameter("newPassword");
            String userType = request.getParameter("newUserType");
            Boolean active = null ;
            if(request.getParameter("active").equals("active"))
            active = true;
            else if (request.getParameter("active").equals("inactive"))
            active = false;
            
            if(email == null || email.equals("") || firstName == null || firstName.equals("")|| lastName == null || lastName.equals("")||password == null || password.equals("") ){
                request.setAttribute("message", "please enter valid details");                 
            }

            else{
                int type = 0;
                if (userType.equals("System Administrator"))
                {
                    type = 1;
                }
                else if (userType.equals("Regular User"))
                {
                    type = 2;
                }
                else if (userType.equals("Company Administrator"))
                {
                    type = 3;
                }



                try {
                    new UserService().insert(email, active, firstName, lastName, password,type );
                    
                    } catch (Exception e) {
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, e);
                }
                
                
                
            }
            
            try {
                    
                List<User> userList = new UserService().getAll();
                    request.setAttribute("usersList", userList);
                } catch (Exception e) {
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, e);
                }

                    getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
                return;
  
        }
        
        else if (request.getParameter("action").equals("updateUser")){
            try{
                
            String ogEmail = request.getParameter("selected");
            UserService editUser = new UserService(); 
            User User = new User(); 
            User = editUser.get(ogEmail);      
            String email = request.getParameter("editEmail") ;
            String firstName = request.getParameter("editFName");
            String lastName = request.getParameter("editLName") ;
            String password = request.getParameter("editLName");
                
        if(email == null || email.equals("") || firstName == null || firstName.equals("")|| lastName == null || lastName.equals("")||password == null || password.equals("") )
            {
                request.setAttribute("message", "please enter valid details");                 
            }
                 
        else{
            
            try {
                
                int type = 0;
            if (request.getParameter("editUserType").equals("System Administrator"))
            {
                type = 1;
            }
            else if (request.getParameter("editUserType").equals("Regular User"))
            {
                type = 2;
            }
            else if (request.getParameter("editUserType").equals("Company Administrator"))
            {
                type = 3;
            }
            
            
                Boolean active = null ;
                if(request.getParameter("editactive").equals("active"))
                active = true;
                else if (request.getParameter("editactive").equals("inactive"))
                active = false;
                
                new UserService().update(request.getParameter("editEmail"),active,request.getParameter("editFName") , request.getParameter("editLName"),request.getParameter("editPassword"),type);
                
                  
            } catch (Exception e) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, e);
            }
        }
            }catch (Exception e) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, e);
    }

        try {
            List<User> users = new UserService().getAll();
            request.setAttribute("usersList", users);
        } catch (Exception e) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, e);
            request.setAttribute("message", "error");
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        return;
        
    }

   
}
}