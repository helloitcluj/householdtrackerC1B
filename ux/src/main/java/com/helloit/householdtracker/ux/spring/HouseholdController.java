package com.helloit.householdtracker.ux.spring;


import com.helloit.householdtracker.ux.common.services.IAccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
public class HouseholdController {

    private static final Logger LOGGER = LogManager.getLogger(HouseholdController.class);

    public static final String MESSAGE_VIEW_TAG = "messagebox";
    public static final String MESSAGE_PARAMETER_TAG = "message";
    public static final String CURRENT_PRINCIPAL_TAG = "currentPrincipal";

    @Autowired
    private IAccountService accountService;

    @RequestMapping(path = "account/register", method = RequestMethod.POST)
    public String register(final ModelMap model, final String username, final String password, final String confirmPassword) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Creating new account: " + username);
        }

        final IAccountService.CreationOutcomes outcome = accountService.register(username, password, confirmPassword);

        accountService.register(username, password, confirmPassword);


        switch (outcome) {
            case USER_SAVED: {
                model.addAttribute(MESSAGE_PARAMETER_TAG, "User successfully created.");
                break;
            }
            case CONFIRMATION_PASSWORD_DO_NOT_MATCH: {
                model.addAttribute(MESSAGE_PARAMETER_TAG, "Password and Confirm password did not match!");
                break;
            }
            case USERNAME_ALREADY_EXISTS: {
                model.addAttribute(MESSAGE_PARAMETER_TAG, "Account '" + username + "' already exists!");
                break;
            }
            default: {
                throw new UnsupportedOperationException("Not supported case!");
            }
        }

        return MESSAGE_VIEW_TAG;
    }


    @RequestMapping(path = "account/login", method = RequestMethod.POST)
      public String login(final String name, final String password, final ModelMap model, final HttpSession session) {
        LOGGER.info(name);

        final String result;

        final IAccountService.LoginOutcomes outcome = accountService.login(name, password);
        accountService.login(name, password);

        switch (outcome) {
            case INEXISTING_ACCOUNT: {
                result = MESSAGE_VIEW_TAG;
                model.addAttribute(MESSAGE_PARAMETER_TAG, "You don't have an account");
                break;
            }
            case INVALID_PASSWORD: {
                result = MESSAGE_VIEW_TAG;
                model.addAttribute(MESSAGE_PARAMETER_TAG, "Your Password is incorrect!");
                break;
            }
            case LOGIN_SUCCEED: {
                session.setAttribute(CURRENT_PRINCIPAL_TAG, name);

                result = "redirect:/";

                break;
            }
            default: {
                throw new UnsupportedOperationException("Not supported case!");
            }
        }

        return result;

    }



    @RequestMapping(path = "account/loginAjax", method = RequestMethod.POST)
    public @ResponseBody String loginAjax(final String name, final String password, final ModelMap model, final HttpSession session) {
        LOGGER.info(name);

        final String result;

        final IAccountService.LoginOutcomes outcome = accountService.login(name, password);
        accountService.login(name, password);

        switch (outcome) {
            case INEXISTING_ACCOUNT: {
                result = "You don't have an account";
            break;
            }
            case INVALID_PASSWORD: {
                result ="Your Password is incorrect!";
                break;
            }
            case LOGIN_SUCCEED: {
                session.setAttribute(CURRENT_PRINCIPAL_TAG, name);
                result = null;
                break;
            }
            default: {
                throw new UnsupportedOperationException("Not supported case!");
            }
        }

        return result;

    }


    @RequestMapping(path = "account/registerAjax", method = RequestMethod.POST)
    public @ResponseBody String  registerAjax(final ModelMap model, final String username, final String password, final String confirmPassword,final HttpSession session) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Creating new account: " + username);
        }

        final IAccountService.CreationOutcomes outcome = accountService.register(username, password, confirmPassword);
        accountService.register(username, password, confirmPassword);

        final String results;

        switch (outcome) {
            case USER_SAVED: {
                session.setAttribute(CURRENT_PRINCIPAL_TAG, username);
               // results ="User successfully created.";
                results= null;

                //model.addAttribute(MESSAGE_PARAMETER_TAG, "User successfully created.");
                break;
            }
            case CONFIRMATION_PASSWORD_DO_NOT_MATCH: {
                results ="Password and Confirm password did not match!";

                //model.addAttribute(MESSAGE_PARAMETER_TAG, );
                break;
            }
            case USERNAME_ALREADY_EXISTS: {
                results= "Account '" + username + "' already exists!";
               // model.addAttribute(MESSAGE_PARAMETER_TAG, );
                break;
            }
            default: {
                throw new UnsupportedOperationException("Not supported case!");
            }
        }

        return results;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home() {

        return "redirect:/index.html";

    }

    @RequestMapping(path = "account/logout", method = RequestMethod.POST)
    public @ResponseBody void logout (final HttpSession session){
        if (LOGGER.isDebugEnabled()){
            final Object username = session.getAttribute(CURRENT_PRINCIPAL_TAG);
            LOGGER.debug("Logging out user" + username);
        }
        session.invalidate();
    }

}


