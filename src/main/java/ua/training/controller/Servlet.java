package ua.training.controller;

import ua.training.controller.command.*;
import ua.training.controller.command.Exception;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Servlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();

    public void init(){
        commands.put("/logout", new LogOut());
        commands.put("/login", new Login());
        commands.put("/registration", new Registration());
        commands.put("/exception" , new Exception());
        commands.put("/user", new User());
        commands.put("/admin", new Admin());
        commands.put("/user/listOfActivity", new UserListOfActivity());
        commands.put("/user/myActivities", new UserMyActivities());
        commands.put("/admin/listOfActivity", new AdminListOfActivity());
        commands.put("/admin/listOfRequest", new AdminListOfRequest());
        commands.put("/admin/listOfActivity/addAnActivity", new AdminAddAnActivity());
        commands.put("/admin/listOfRequest/allow", new AdminListOfRequestAllow());
        commands.put("/admin/listOfActivity/edit", new AdminEditAnActivity());
        commands.put("/admin/listOfActivity/delete", new AdminDeleteAnActivity());
        commands.put("/user/listOfActivity/addAnActivity", new UserAddAnActivity());
        commands.put("/user/myActivities/editAnActivity", new UserMyActivitiesEditAnActivity());
        commands.put("/user/myActivities/delete", new UserMyActivitiesDeleteAnActivity());
        commands.put("/admin/listOfActivity/sortByName", new AdminListOfActivitySortByName());
        commands.put("/admin/listOfActivity/sortByCategory", new AdminListOfActivitySortByCategory());
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {



        String path = request.getRequestURI();
        if ("/favicon.ico".equals(path)) return;
        Command command = commands.get(path);
        if (command==null){
            command = (x) -> "/index.jsp";
        }
        String page = command.execute(request);
        if(page.contains("redirect:")){
            response.sendRedirect(page.replace("redirect:", ""));
        } else{
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}