package ua.training.controller.command;

import ua.training.model.entity.Types;

import javax.servlet.http.HttpServletRequest;
import ua.training.model.entity.Activities;
import ua.training.service.ActivityService;
import ua.training.service.RequestService;
import ua.training.service.UserActivityService;
import ua.training.model.entity.UserActivity;

public class UserMyActivitiesEditAnActivity implements Command {

    private ActivityService activityService = new ActivityService();
    private UserActivityService userService = new UserActivityService();
    private RequestService requestService = new RequestService();

    private int id;
    private Activities activity;
    private UserActivity userActivity;

    @Override
    public String execute(HttpServletRequest request) {
        String hours = request.getParameter("hours");
        if (hours == null || hours.length() == 0) {
            this.id = Integer.parseInt(request.getParameter("id"));
            this.userActivity = userService.findById(this.id).get();
            this.activity = activityService.findActivity(userActivity.getIdOfActivity());
            request.setAttribute("hours", userActivity.getHours());
            request.setAttribute("id", userActivity.getId());
            request.getSession().setAttribute("activity", activity);
            return "/users/editAnActivity.jsp";
        }
        requestService.addRequest(userActivity.getIdOfUser(), userActivity.getIdOfActivity(), hours, String.valueOf(Types.EDIT));
        return "redirect:/user/myActivities";
    }
}
