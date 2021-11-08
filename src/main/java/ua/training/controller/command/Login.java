package ua.training.controller.command;

import ua.training.config.PasswordEncode;
import ua.training.model.dao.impl.ConnectionPoolHolder;
import ua.training.model.dao.impl.JDBCUsersDao;
import ua.training.model.entity.Roles;
import ua.training.model.entity.Users;
import ua.training.service.UserService;

import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Login implements Command {

    private UserService service = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        if( name == null || name.equals("") || password == null || password.equals("")  ){
            return "/login.jsp";
        }
        Optional<Users> user = service.findUserByLogin(name);
        if (user.isPresent()) {
            String userRole = (user.get().getPassword().equals(new PasswordEncode().encode(password))) ? user.get().getRole() : Roles.UNKNOWN.toString();
            request.getSession().setAttribute("UserRole", userRole);
            request.getSession().setAttribute("UserName", name);
            request.getSession().setAttribute("idOfUser", user.get().getId());
            request.getSession().getServletContext().setAttribute("UserRole", userRole);
            request.getSession().getServletContext().setAttribute("UserName", name);
            if (userRole.equals(Roles.UNKNOWN.toString())) {
                request.setAttribute("message", "No passwords matches are found. Please, try again");
                return "/login.jsp";
            } else if (userRole.equals(Roles.USER.toString())) {
                request.getSession().setAttribute("LoggedIn", true);
                return "redirect:/user";
            } else {
                request.getSession().setAttribute("LoggedIn", true);
                return "redirect:/admin";
            }
        }
        request.setAttribute("message", "No user with such login");
            return "/login.jsp";
        }
}
