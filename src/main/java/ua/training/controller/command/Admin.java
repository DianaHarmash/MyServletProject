package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;
import ua.training.service.UserService;
import ua.training.model.entity.Users;

public class Admin implements Command {

    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("user", userService.findUserByLogin((String) request.getSession().getAttribute("UserName")).get());
        return "/admins/adminMain.jsp";
    }
}
