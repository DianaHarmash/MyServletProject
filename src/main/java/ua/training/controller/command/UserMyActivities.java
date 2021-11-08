package ua.training.controller.command;

import ua.training.model.entity.Users;
import ua.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import ua.training.service.UserActivityService;

public class UserMyActivities implements Command {

    private UserService userService = new UserService();
    private UserActivityService userActivity = new UserActivityService();

    @Override
    public String execute(HttpServletRequest request) {
        Users user = userService.findUserByLogin((String) request.getSession().getAttribute("UserName")).get();
        request.setAttribute("user", user);
        request.setAttribute("myActivities", userActivity.getAllActivitiesOfUser(user.getLogin()));
        return "/users/listMyActivity.jsp";
    }
}
