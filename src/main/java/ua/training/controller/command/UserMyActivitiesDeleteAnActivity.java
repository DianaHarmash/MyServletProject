package ua.training.controller.command;

import ua.training.model.entity.Activities;
import ua.training.model.entity.Types;
import ua.training.model.entity.UserActivity;
import ua.training.service.ActivityService;
import ua.training.service.RequestService;
import ua.training.service.UserActivityService;

import javax.servlet.http.HttpServletRequest;

public class UserMyActivitiesDeleteAnActivity implements Command {

    private ActivityService activityService = new ActivityService();
    private UserActivityService userService = new UserActivityService();
    private RequestService requestService = new RequestService();

    private int id;
    private Activities activity;
    private UserActivity userActivity;

    @Override
    public String execute(HttpServletRequest request) {
        this.id = Integer.parseInt(request.getParameter("id"));
        this.userActivity = userService.findById(this.id).get();
        requestService.addARequest(userActivity.getIdOfUser(), userActivity.getIdOfActivity(), String.valueOf(Types.DELETE));
        return "redirect:/user/myActivities";
    }
}
