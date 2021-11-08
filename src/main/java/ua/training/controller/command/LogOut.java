package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOut implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session=request.getSession(false);
        session.invalidate();
        return "redirect:/";
    }
}
