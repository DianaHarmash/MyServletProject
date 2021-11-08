package ua.training.controller.command;

import ua.training.model.entity.Activities;
import ua.training.service.ActivityService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminAddAnActivity implements Command {

    private ActivityService activityService = new ActivityService();

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        if (name == null || name.length() == 0 || category == null || category.length() == 0) {
            return "/admins/addAnActivity.jsp";
        }
        activityService.saveActivity(name, category);
        return "redirect:/admin/listOfActivity?page=next";
    }
}
