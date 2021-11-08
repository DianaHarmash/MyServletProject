package ua.training.controller.command;

import ua.training.service.RequestService;
import ua.training.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class AdminListOfRequestAllow implements Command {

    private UserService userService = new UserService();
    private RequestService request = new RequestService();

    @Override
    public String execute(HttpServletRequest request) {
        this.request.allow(Integer.parseInt(request.getParameter("id")));
        return "/admins/listOfRequests.jsp";
    }
}
