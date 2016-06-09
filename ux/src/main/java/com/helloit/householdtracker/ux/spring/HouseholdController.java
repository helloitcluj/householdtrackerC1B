package com.helloit.householdtracker.ux.spring;


import com.helloit.householdtracker.ux.common.IAccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HouseholdController {

    private static final Logger LOGGER = LogManager.getLogger(HouseholdController.class);
    private static final String REGISTER_VIEW_TAG = "register";
    public static final String MESSAGE_PARAMETER_TAG = "message";

    @Autowired
    private IAccountService accountService;

    @RequestMapping(path = "register", method = RequestMethod.POST)
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

        return REGISTER_VIEW_TAG;
    }


    @RequestMapping(path = "login", method = RequestMethod.GET)
    public String login(String name, @RequestParam("Password") String password, final ModelMap model) {
        LOGGER.info(name);

        final String result;

        final IAccountService.LoginOutcomes outcome = accountService.login(name, password);
        accountService.login(name, password);

        switch (outcome) {
            case INEXISTING_ACCOUNT: {
                result = REGISTER_VIEW_TAG;
                model.addAttribute(MESSAGE_PARAMETER_TAG, "You don't have an account");
                break;
            }
            case INVALID_PASSWORD: {
                result = REGISTER_VIEW_TAG;
                model.addAttribute(MESSAGE_PARAMETER_TAG, "Your Password is incorrect!");
                break;
            }
            case LOGIN_SUCCEED: {
                result = "redirect:/";

                break;
            }
            default: {
                throw new UnsupportedOperationException("Not supported case!");
            }
        }

        return result;

    }

}


