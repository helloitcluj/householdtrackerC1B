package com.helloit.householdtracker.ux.spring;

import com.helloit.householdtracker.ux.common.IAccountService;
import com.helloit.householdtracker.ux.common.entities.User;
import com.helloit.householdtracker.ux.common.repository.IUserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 */

@Service
public class AccountService implements IAccountService {

    private static final Logger LOGGER = LogManager.getLogger(AccountService.class);

    private final IUserRepository userRepository;

    public AccountService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CreationOutcomes register(@NotNull String username, @NotNull String password, @NotNull String confirmPassword) {
        CreationOutcomes result;


        List<User> existingUsers = userRepository.findByUsername(username);
        if (existingUsers.isEmpty()) {

            if (password.equals(confirmPassword)) {
                final User entity = new User();
                entity.setUsername(username);
                entity.setPassword(password);
                final User savedEntity = userRepository.save(entity);
                result = CreationOutcomes.USER_SAVED;

            } else {
                result = CreationOutcomes.CONFIRMATION_PASSWORD_DO_NOT_MATCH;
            }

        } else {
            result = CreationOutcomes.USERNAME_ALREADY_EXISTS;
        }

        return result;
    }

}