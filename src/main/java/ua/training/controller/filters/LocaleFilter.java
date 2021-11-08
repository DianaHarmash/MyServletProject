package ua.training.controller.filters;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class LocaleFilter implements Filter {
    static class LocaleOverridingRequest extends HttpServletRequestWrapper {

        private final Locale locale;

        public LocaleOverridingRequest(HttpServletRequest request, Locale locale) {
            super(request);
            this.locale = locale;
        }

        @Override
        public Locale getLocale() {
            return locale != null ? locale : super.getLocale();
        }

        @Override
        @SuppressWarnings({ "rawtypes", "unchecked" })
        public Enumeration getLocales() {
            if (locale == null) {
                return super.getLocales();
            }
            else {
                List<Locale> locales = Collections.list(super.getLocales());
                if (locales.contains(locale)) {
                    locales.remove(locale);
                }
                locales.add(0, locale);
                return Collections.enumeration(locales);
            }
        }
    }

    private ResourceBundle locale = ResourceBundle.getBundle("myLocales", new Locale("eng"));

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        Locale appliedLocale = new Locale("eng");;
        if ("eng".equals(servletRequest.getParameter("language"))) {
            appliedLocale = new Locale("eng");
            session.setAttribute("language_name", "eng");
        } else if ("rus".equals(servletRequest.getParameter("language"))) {
            session.setAttribute("language_name", "rus");
            appliedLocale = new Locale("rus");
        } else {
            String locale_name = (String) session.getAttribute("language_name");
            if ((locale_name!=null) && (locale_name.length()>0)){
                appliedLocale = new Locale(locale_name);
            }
        }
        if (appliedLocale!=null) {
            LocaleOverridingRequest overridenRequest = new LocaleOverridingRequest(httpServletRequest, appliedLocale);
            filterChain.doFilter(new LocaleOverridingRequest(overridenRequest, appliedLocale), servletResponse);
        } else {
            filterChain.doFilter(httpServletRequest, servletResponse);
        }
    }
    @Override
    public void destroy() {
    }
}
