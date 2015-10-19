package personal.user_management;

import personal.user_management.controllers.UserManagementController;
import personal.user_management.services.UserManagementService;

public class UserManagement 
{
    public static void main( String[] args )
    {
    	new UserManagementController(new UserManagementService());
    }
}
