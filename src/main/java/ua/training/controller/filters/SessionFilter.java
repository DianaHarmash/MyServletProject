package ua.training.controller.filters;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class SessionFilter implements Filter {
    private final Set<String> excludedPatterns = new HashSet<>();
    static final HashMap<String, String> sessions = new HashMap<String, String>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String excluded = filterConfig.getInitParameter("excludePatterns");
        if ((excluded!=null) && (excluded.length()>0)){
            String[] patterns = excluded.split(",");
            for (String pattern: patterns) {
                excludedPatterns.add(pattern.trim().toLowerCase(Locale.ROOT));
            }
        }
    }

    private boolean validateSession(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null) {
            session = request.getSession(true);
        }
        String uName = (String) request.getSession().getAttribute("UserName");
        if ((uName==null) || (uName.length()==0)) {
            return true;
        }
        String id = request.getSession().getId();
        String registeredSession = sessions.get(uName);
        Boolean logged = (Boolean) request.getSession().getAttribute("LoggedIn");
        if ((logged!=null) && (logged == true)){
            request.getSession().removeAttribute("LoggedIn");
            sessions.put(uName, id);
            return true;
        }
        if ((registeredSession==null) || (registeredSession.length()==0)){
            sessions.put(uName, id);
            return true;
        }
        if (!registeredSession.equals(id)){
            request.getSession().removeAttribute("UserName");
            request.getSession().removeAttribute("UserRole");
            request.getSession().getServletContext().removeAttribute("UserRole");
            request.getSession().getServletContext().removeAttribute("UserName");
            return false;
        }
        return true;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse)servletResponse;

            String path = ((HttpServletRequest) servletRequest).getRequestURI();
            if (!"/favicon.ico".equals(path)) {
                if (!validateSession(request)){
                    response.sendRedirect("/");
                    return;
                }

                if (!excludedPatterns.contains(path)) {
                    HttpSession session = ((HttpServletRequest) servletRequest).getSession(false);
                    if (session != null) {
                        Object role = session.getAttribute("UserRole");
                        if (role instanceof String) {
                            if (((String) role).length() == 0) {
                                return;
                            }
                        } else {
                            return;
                        }
                        if ("USER".equals(role)) {
                            if (path.toLowerCase(Locale.ROOT).contains("admin")) return;
                        }
                        if ("ADMIN".equals(role)) {
                            if (path.toLowerCase(Locale.ROOT).contains("user")) return;
                        }
                    }
                }
            }
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
    }
}
