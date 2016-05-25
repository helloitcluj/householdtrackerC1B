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
@RequestMapping("register")
public class HouseholdController {

    private static final Logger LOGGER = LogManager.getLogger(HouseholdController.class);

    private static final String REGISTER_VIEW_TAG = "register";

    private static final String MESSAGE_PARAMETER_TAG = "message";
    private static final String USER_SAVED = "User saved";
    private static final String USERNAME_ALREADY_EXISTS = "Username already exists";

    @Resource
    private IUserRepository userRepository;

    //public static final String SAMPLE_TEXT = "Hello world!";

    //@Transactional
    @RequestMapping(method = RequestMethod.GET)
    public java.lang.String printWelcome(String username, @RequestParam("Password") String password,
                                         @RequestParam("ConfirmPassword") String confirmPassword, final ModelMap model) {
        LOGGER.info(username);

        final String message;

        List<User> existingUsers = userRepository.findByusername(username);
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
}
