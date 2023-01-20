package edu.lwtech.csd297.skeleton.commands;

import java.util.*;
import javax.servlet.http.*;

import edu.lwtech.csd297.skeleton.SkeletonServlet;

// Handle the "showLogin" command
public class ShowLoginHandler implements CommandHandler<SkeletonServlet> {

    @Override
    public String handle(HttpServletRequest request, SkeletonServlet servlet) {
        String template = "login.ftl";
        Map<String, Object> templateFields = new HashMap<>();
        CommandUtils.getSessionVariables(request, templateFields);
        return CommandUtils.mergeTemplate(template, templateFields, servlet.getFreeMarkerConfig());
    }

}
