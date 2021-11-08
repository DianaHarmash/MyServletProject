package ua.training.controller.command;

import ua.training.config.PasswordEncode;
import ua.training.model.dao.impl.JDBCUsersDao;
import ua.training.model.entity.Roles;
import ua.training.model.entity.Users;
import ua.training.service.UserService;
import ua.training.config.ValidationClass;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.logging.Level;

public class Registration implements Command {

    private static final UserService service = new UserService();

    @Override
    public String execute(HttpServletRequest request) {

        String login = request.getParameter("username");
        String password = request.getParameter("password");
        String password1 = request.getParameter("password1");
        if( login == null || login.equals("") || password == null || password.equals("")){
            return "/registration.jsp";
        }
        String userRole = "UNKNOWN";
        if(password.equals(password1)) {
            if (!new ValidationClass().checkInputLogin(login) || !new ValidationClass().checkInputPassword(password)) {
                request.setAttribute("message", "Illegal format. Please, try again");
                return "/registration.jsp";
            }
            userRole = service.saveUser(login, new PasswordEncode().encode(password));
        } else {
            request.setAttribute("message", "No matches of passwords are found. Please, try again");
            return "/registration.jsp";
        }
      request.getSession().setAttribute("UserRole", userRole);
      request.getSession().setAttribute("UserName", login);
      request.getSession().setAttribute("idOfUser", service.findUserByLogin(login).get().getId());
      request.getSession().setAttribute("LoggedIn", true);
      request.getSession().getServletContext().setAttribute("UserRole", userRole);
      request.getSession().getServletContext().setAttribute("UserName", login);
      if (userRole.equals(Roles.USER.toString())) {
          return "redirect:/user";
      } else if (userRole.equals(Roles.ADMIN.toString())){
          return "redirect:/admin";
      } else {
          return "redirect:/index.jsp";
      }
    }
}
