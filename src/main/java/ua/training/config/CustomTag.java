package ua.training.config;

import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.JspWriter;

public class CustomTag extends TagSupport {

    @Override
    public int doStartTag() {
        JspWriter out=pageContext.getOut();
        try{
            out.print("Hello, ");
        }catch(Exception e){
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
