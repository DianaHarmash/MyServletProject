package ua.training.config;

import ua.training.model.dao.impl.JDBCActivityDao;

import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.JspWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomTag extends TagSupport {
    private static final Logger logger = Logger.getLogger(String.valueOf(CustomTag.class));

    @Override
    public int doStartTag() {
        JspWriter out=pageContext.getOut();
        try{
            out.print("Hello, ");
        }catch(Exception e){
            logger.log(Level.WARNING, e.getLocalizedMessage());
        }
        return SKIP_BODY;
    }
}
