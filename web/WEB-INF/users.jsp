<%-- 
    Document   : users
    Created on : 30-Jun-2022, 12:27:38 PM
    Author     : Vaibhav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="mystyle.css">
        <title>Users</title>
    </head>
    <body>
        
        <div class="parentContainer">
         
         
         <div id="left" class="container addContainer side" >
            <h2>Add User</h2>
            <form action="" method="post" id="addUserForm">
                    <input type="text" name="newEmail" placeholder="Email" id="newEmail"><br>
                    <input type="text" name="newFName" placeholder="First Name" id="newFName"><br>
                    <input type="text" name="newLName" placeholder="Last Name" id="newLName"><br>
                    <select name="active" id="active">
                        <option value="active" name="active">Active</option>
                        <option value="inactive" name="inactive">Inactive</option>
                    </select><br>
                    <input type="text" name="newPassword" placeholder="Password" id="newPassword"><br>
                    <select name="newUserType" id="newUserType">
                        <option value="System Administrator">System Administrator</option>
                        <option value="Regular User">Regular User</option>
                        <option value="Company Administrator">Company Administrator</option>
                    </select><br>
                    <input type="submit" value="addUser">
                    <input type="hidden" name="action" value="add">
            </form>
        </div>
            <div class="container midContainer">
                
                <h2>Manage Users</h2>
            
            
            <table>
                
                <tr>
                    <th>Email</th>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Role</th>
                    <th>Active</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                <c:forEach items="${usersList}" var="user">
                    
                    
                    <tr><td>${user.getEmail()}</td>
                        <td>${user.getFirstName()}</td>
                        <td>${user.getLastName()}</td>        
                        <td><c:if test="${user.getRole().getRoleId() eq 1}">System Admin</c:if>
                                <c:if test="${user.getRole().getRoleId() eq 2}">Regular user</c:if>
                                <c:if test="${user.getRole().getRoleId() eq 3}">Company Admin</c:if></td>
                        <td>${user.active}</td>
                        
                        <td><form action='' method='post'>
                        <input type='submit' value='edit' name='edit'>
                        <input type='hidden' name='selected' value='${user.getEmail()}'>
                        <input type='hidden' name='action' id='action' value='edit'>
                        </form></td>
                        
                        <td><form action='' method='post'>
                        <input type='submit' value='delete' name='delete'>
                        <input type='hidden' name='selected' value='${user.getEmail()}'>
                        <input type='hidden' name='action' id='action' value='delete'>
                        </form></td></tr>
                    
                </c:forEach>
            </table>
            </div>
         <div id="right" class="container editContainer side">
                <h2>Edit User</h2>
            <form action="" method="post" id="editUserForm">
                <input type="hidden" name="editEmail" value="${editEmail}" id="editEmail">
                <input type="hidden" name="selected" value="${ogEmail}">
                <input type="text" name="editFName" value="${editFName}" id="editFName"><br>
                <input type="text" name="editLName" value="${editLName}" id="editLName"><br>
                <input type="text" name="editPassword" value="${editPassword}" id="editPassword"><br>
                <label for="editUserType"></label>
                <select name="editUserType" id="editUserType">
                    <option value="System Administrator">System Administrator</option>
                    <option value="Regular User">Regular User</option>
                    <option value="Company Administrator">Company Administrator</option>
                </select>
                <select name="editactive" id="editactive">
                    <option value="active" name="editactive">Active</option>
                    <option value="inactive" name="editactive">Inactive</option>
                </select><br>
                <input type="submit" value="editUser">
                <input type="hidden" name="action" value="updateUser">
            </form>
        </div>
                
        </div>
                
                
                
                
                <div class="messages"><h1 id="output">${message}</h1></div>
                    
    </body>
</html>
