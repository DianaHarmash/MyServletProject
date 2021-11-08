package ua.training.controller.command;

import ua.training.model.entity.Users;
import ua.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import ua.training.service.RequestService;

public class AdminListOfRequest implements Command {

    private UserService userService = new UserService();
    private RequestService request = new RequestService();

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("user", userService.findUserByLogin((String) request.getSession().getAttribute("UserName")).get());
        request.setAttribute("requests", this.request.getRequests());
        return "/admins/listOfRequests.jsp";
    }
}
