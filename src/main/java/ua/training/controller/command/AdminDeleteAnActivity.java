package ua.training.controller.command;

import ua.training.service.ActivityService;

import javax.servlet.http.HttpServletRequest;

public class AdminDeleteAnActivity implements Command {

    private ActivityService activityService = new ActivityService();


    @Override
    public String execute(HttpServletRequest request) {
        activityService.deleteAnActivity(activityService.findActivity(Integer.parseInt(request.getParameter("id"))).getActivity());
        return "redirect:/admin/listOfActivity?page=next";
    }
}
