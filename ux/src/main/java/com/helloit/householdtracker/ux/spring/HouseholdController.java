package com.helloit.householdtracker.ux.spring;


import com.helloit.householdtracker.ux.common.IAccountService;
import com.helloit.householdtracker.ux.common.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("account")
public class HouseholdController {

    public static final String ACCOUNT_ERROR = "account/error";
    private static final Logger LOGGER = LogManager.getLogger(HouseholdController.class);
    private static final String REGISTER_VIEW_TAG = "register";
    private static final String MESSAGE_PARAMETER_TAG = "message";
    private static final String USER_CREATED = "account/success";
    @Autowired
    private IAccountService accountService;

    @RequestMapping(path = "register", method = RequestMethod.POST)
    public String register(final ModelMap model, final String username, final String password, final String confirmPassword) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Creating new account: " + username);
        }

        final String result;

        final IAccountService.CreationOutcomes outcome = accountService.register(username, password, confirmPassword);

        accountService.register(username, password, confirmPassword);


        switch (outcome) {
            case USER_SAVED: {
                result = USER_CREATED;
                model.addAttribute(MESSAGE_PARAMETER_TAG, "User successfully created.");
                break;
            }
            case CONFIRMATION_PASSWORD_DO_NOT_MATCH: {
                result = ACCOUNT_ERROR;
                model.addAttribute(MESSAGE_PARAMETER_TAG, "Password and Confirm password did not match!");
                break;
            }
            case USERNAME_ALREADY_EXISTS: {
                result = ACCOUNT_ERROR;
                model.addAttribute(MESSAGE_PARAMETER_TAG, "Account '" + username + "' already exists!");
                break;
            }
            default: {
                throw new UnsupportedOperationException("Not supported case!");
            }
        }

        return result;

    }


    @RequestMapping(path = "login", method = RequestMethod.GET)
    public String login(String name, @RequestParam("Password") String password, final ModelMap model) {
        LOGGER.info(name);

        final String message;

        User existingAccount = null;//userRepository.findOneByUsername(name);
        if (existingAccount == null) {
            message = "You don't have an account.";

        } else if (!password.equals(existingAccount.getPassword())) {

            message = "Invalid credentials";
        } else {
            message = "Login successful!";
        }


        model.addAttribute(MESSAGE_PARAMETER_TAG, message);
        return REGISTER_VIEW_TAG;
    }

}


