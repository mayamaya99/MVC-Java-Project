package edu.lwtech.csd297.skeleton.commands;

import java.util.*;
import javax.servlet.http.*;

import edu.lwtech.csd297.skeleton.SkeletonServlet;
import edu.lwtech.csd297.skeleton.pojos.*;

// Handle the "login" command
public class LoginHandler implements CommandHandler<SkeletonServlet> {

    @Override
    public String handle(HttpServletRequest request, SkeletonServlet servlet) {

        boolean loggedIn = false;
        String message = "";
        String template = "confirm.ftl";
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Map<String, Object> templateFields = new HashMap<>();
        CommandUtils.getSessionVariables(request, templateFields);

        Member member = servlet.getMembersDAO().retrieveByName(username);
        if (member == null) {
            message = "We do not have a member with that username on file. Please try again.<br /><a href='?cmd=showLogin'>Log In</a>";
            templateFields.put("message", message);
            templateFields.put("loggedIn", false);
            return CommandUtils.mergeTemplate(template, templateFields, servlet.getFreeMarkerConfig());
        }

        if (member.getPassword().equals(password)) {
            int ownerID = member.getRecID();
            loggedIn = true;
            HttpSession session = request.getSession(true);         // true == Create a new session for this user
            session.setAttribute("owner", ownerID);
            message = "You have been successfully logged in to your account.<br /><a href='?cmd=home'>Home</a>";
        } else {
            message = "Your password did not match what we have on file. Please try again.<br /><a href='?cmd=showLogin'>Log In</a>";
        }
        templateFields.put("loggedIn", loggedIn);
        templateFields.put("message", message);
        return CommandUtils.mergeTemplate(template, templateFields, servlet.getFreeMarkerConfig());
    }

}
