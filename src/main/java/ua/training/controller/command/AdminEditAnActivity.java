package ua.training.controller.command;

import ua.training.service.ActivityService;

import javax.servlet.http.HttpServletRequest;
import ua.training.model.entity.Activities;
import java.util.Optional;

public class AdminEditAnActivity implements Command {

    private ActivityService activityService = new ActivityService();


    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        int id = Integer.parseInt(request.getParameter("id"));
        if (name == null || name.length() == 0 || category == null || category.length() == 0) {
            request.setAttribute("activity", activityService.findActivity(id));
            return "/admins/editAnActivity.jsp";
        }
        activityService.updateAnActivity(id, name, category);
        return "redirect:/admin/listOfActivity?page=next";
    }
}
