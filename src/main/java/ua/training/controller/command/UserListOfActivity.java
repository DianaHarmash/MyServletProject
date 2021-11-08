package ua.training.controller.command;

import ua.training.model.entity.Users;
import ua.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import ua.training.service.ActivityService;
import ua.training.model.entity.Activities;
import java.util.List;

public class UserListOfActivity implements Command {

    private UserService userService = new UserService();
    private ActivityService activityService = new ActivityService();

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("activities", activityService.getAllActivities());
        request.setAttribute("user", userService.findUserByLogin((String) request.getSession().getAttribute("UserName")).get());
        return "/users/listOfActivity.jsp";
    }
}
