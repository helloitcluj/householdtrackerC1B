package com.helloit.householdtracker.ux.spring;


//import aron.sinoai.springmvcjpa.common.entities.Shop;
//import aron.sinoai.springmvcjpa.common.repository.IShopRepository;

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


@Service
@Controller
@RequestMapping("register")
public class HouseholdController {

	public static final java.lang.String HELLO_VIEW_TAG = "hello";
    public static final java.lang.String WARNING_VIEW_TAG = "warning";
    private static final Logger LOGGER = LogManager.getLogger(HouseholdController.class);

	//public static final String MESSAGE_PARAMETER_TAG = "message";
	@Resource
	private IUserRepository userRepository;

	//public static final String SAMPLE_TEXT = "Hello world!";

    //@Transactional
	@RequestMapping(method = RequestMethod.GET)
	public java.lang.String printWelcome(String username, @RequestParam("Password") String password,
										 @RequestParam("ConfirmPassword") String confirmPassword, final ModelMap model) {
		LOGGER.info(username);

        if (password == confirmPassword) {

            final User entity = new User();
            entity.setUsername(username);
            entity.setPassword(password);
            final User savedEntity = userRepository.save(entity);
            return HELLO_VIEW_TAG;

        } else {
            //model.addAttribute(MESSAGE_PARAMETER_TAG, SAMPLE_TEXT);
            return WARNING_VIEW_TAG;
        }
    }
}