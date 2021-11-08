package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

public class Exception implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/error.jsp";
    }
}
