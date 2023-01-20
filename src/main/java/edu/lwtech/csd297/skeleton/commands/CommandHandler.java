package edu.lwtech.csd297.skeleton.commands;

import javax.servlet.http.*;

public interface CommandHandler<T> {
    String handle(HttpServletRequest request, T servlet);
}
