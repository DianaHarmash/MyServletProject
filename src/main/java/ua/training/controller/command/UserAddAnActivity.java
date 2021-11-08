package ua.training.controller.command;

import ua.training.model.entity.Activities;
import ua.training.model.entity.Users;
import ua.training.service.ActivityService;
import ua.training.service.RequestService;
import ua.training.model.entity.Types;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserAddAnActivity implements Command {

    private ActivityService activityService = new ActivityService();
    private RequestService requestService = new RequestService();

    private Activities activity;
    private int id;

    @Override
    public String execute(HttpServletRequest request) {
        String hours = request.getParameter("hours");
        if (hours == null || hours.length() == 0) {
            this.id = Integer.parseInt(request.getParameter("id"));
            activity = activityService.findActivity(this.id);
            request.getSession().setAttribute("activity", activity);
            return "/users/addAnActivity.jsp";
        }
        requestService.addRequest(Integer.parseInt(String.valueOf(request.getSession().getAttribute("idOfUser"))), id, hours, String.valueOf(Types.ADD));
        return "redirect:/user/listOfActivity";
    }
}
