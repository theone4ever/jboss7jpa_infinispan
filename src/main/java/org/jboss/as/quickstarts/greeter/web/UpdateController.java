package org.jboss.as.quickstarts.greeter.web;

import org.jboss.as.quickstarts.greeter.domain.User;
import org.jboss.as.quickstarts.greeter.domain.UserDao;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class UpdateController {
    @Inject
    private FacesContext facesContext;

    @Inject
    private UserDao userDao;

    @Named
    @Produces
    @RequestScoped
    private User updateUser = new User();

    public void update() {
        try {
            userDao.updateUser(updateUser);

            String message = "A user with id " + updateUser.getId() + " has been updated";
            facesContext.addMessage(null, new FacesMessage(message));
        } catch (Exception e) {
            String message = "An error has occured while update the user (see log for details)";
            facesContext.addMessage(null, new FacesMessage(message));
        }
    }
}
