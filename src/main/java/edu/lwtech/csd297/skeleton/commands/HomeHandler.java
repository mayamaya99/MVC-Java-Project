package edu.lwtech.csd297.skeleton.commands;

import java.util.*;
import javax.servlet.http.*;

import edu.lwtech.csd297.skeleton.SkeletonServlet;

// Handle the "home" command
public class HomeHandler implements CommandHandler<SkeletonServlet> {

    @Override
    public String handle(HttpServletRequest request, SkeletonServlet servlet) {
        String template = "home.ftl";
        Map<String, Object> templateFields = new HashMap<>();
        CommandUtils.getSessionVariables(request, templateFields);

        // TODO: Add template variables to the templateFields map

        return CommandUtils.mergeTemplate(template, templateFields, servlet.getFreeMarkerConfig());
    }

}
