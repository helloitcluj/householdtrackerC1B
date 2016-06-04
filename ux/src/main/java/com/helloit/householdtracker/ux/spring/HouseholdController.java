package com.helloit.householdtracker.ux.spring;


import com.helloit.householdtracker.ux.common.entities.User;
import com.helloit.householdtracker.ux.common.repository.IUserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;


@Service
@Controller
public class HouseholdController {

    public static final String REGISTER_VIEW_TAG = "register";
    public static final String USERNAME_ALREADY_EXISTS = "Username already exists";
    private static final Logger LOGGER = LogManager.getLogger(HouseholdController.class);
    private static final String MESSAGE_PARAMETER_TAG = "message";
    private static final String USER_SAVED = "User saved";
    @Resource
    public IUserRepository userRepository;


    @RequestMapping(path = "register", method = RequestMethod.GET)
    public String register(String username, @RequestParam("Password") String password,
                           @RequestParam("ConfirmPassword") String confirmPassword, final ModelMap model) {
        LOGGER.info(username);

        final String message;

        List<User> existingUsers = userRepository.findByUsername(username);
        if (existingUsers.isEmpty()) {

            if (password.equals(confirmPassword)) {

                final User entity = new User();
                entity.setUsername(username);
                entity.setPassword(password);
                final User savedEntity = userRepository.save(entity);

                message = USER_SAVED;

            } else {
                message = "Your password and confirmation password do not match";
            }

        } else {
            message = USERNAME_ALREADY_EXISTS;
        }

        model.addAttribute(MESSAGE_PARAMETER_TAG, message);

        return REGISTER_VIEW_TAG;
    }


    @RequestMapping(path = "login", method = RequestMethod.GET)
    public String login(String name, @RequestParam("Password") String password, final ModelMap model) {
        LOGGER.info(name);

        final String message;

        User existingAccount = userRepository.findOneByUsername(name);
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


