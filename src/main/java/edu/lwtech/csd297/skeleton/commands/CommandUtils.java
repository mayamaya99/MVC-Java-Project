package edu.lwtech.csd297.skeleton.commands;

import java.io.*;
import java.util.*;
import javax.servlet.http.*;

import freemarker.template.*;
import org.apache.logging.log4j.*;

public class CommandUtils {

    private static final Logger logger = LogManager.getLogger(CommandUtils.class);

    private CommandUtils() {
        // Prevent instantiation
    }

    // Get the user's session variables (if they exist)
    public static void getSessionVariables(HttpServletRequest request, Map<String, Object> templateFields) {
        int ownerID = 0;
        boolean loggedIn = false;
        HttpSession session = request.getSession(false);            // false == don't create a new session if one doesn't exist
        if (session != null) {
            try {
                ownerID = (Integer)session.getAttribute("owner");
            } catch (NumberFormatException e) {
                ownerID = -1;
            }
            loggedIn = (ownerID > 0);
        }
        templateFields.put("owner", ownerID);
        templateFields.put("loggedIn", loggedIn);
    }

    public static String mergeTemplate(String template, Map<String, Object> templateFields, Configuration config) {
        StringWriter out = new StringWriter();
        try {
            Template view = config.getTemplate(template);
            view.process(templateFields, out);
        } catch (IOException | TemplateException e) {
            logger.error("Unexpected error processing Freemarker template: {}", template, e);
        }
        return out.toString();
    }

    public static int parseInt(String s) {
        int i = -1;
        if (s != null) {
            try {
                i = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                logger.error("Unable to parse integer from {}", s);
            }
        }
        return i;
    }

}
