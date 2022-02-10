package ru.rsreu.officetechnics.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutCommand extends FrontCommand{
    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        session.invalidate();

        redirect("login");
    }
}
