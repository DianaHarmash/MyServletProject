package ua.training.controller.command;

import ua.training.model.entity.Activities;
import ua.training.service.ActivityService;
import ua.training.service.UserActivityService;
import ua.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminListOfActivitySortByName implements Command {

    private ActivityService activityService = new ActivityService();
    private UserService userService = new UserService();
private UserActivityService userActivity = new UserActivityService();

    int page = -1;
    int limit = 5;
    int offset = 0;

    @Override
    public String execute(HttpServletRequest request) {

        page = ("prev".equals(request.getParameter("page")) ? page-1 : page + 1);

        if(page < 0) {
            page = ((int) Math.ceil(activityService.quantityOfActivity() * 1.0  / limit)) -1;
        }
        if (page >= (int) Math.ceil(activityService.quantityOfActivity() * 1.0  / limit)) {
            page = 0;
            offset = 0;
        }
        offset = page * limit;

        request.setAttribute("user", userService.findUserByLogin((String) request.getSession().getAttribute("UserName")).get());
        request.setAttribute("activities", activityService.getRecordsSortedByName(offset,limit));
        request.setAttribute("users", userActivity.getAllUsersOfActivity());
        return "/admins/listOfActivities.jsp";
    }
}
