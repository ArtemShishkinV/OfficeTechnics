package ru.rsreu.officetechnics.commands;

import javax.servlet.ServletException;
import java.io.IOException;

public class ProfileCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        forward("profile");
    }
}
