
package com.mp3.models;

import javax.servlet.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class UserContextListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Putting Account object in ServletContext so its data is not
        // lost with every session
        ServletContext context = sce.getServletContext();
        context.setAttribute("account", new Account());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
    
}
