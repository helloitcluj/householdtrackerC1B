package com.helloit.householdtracker.ux.common.services;

import org.jetbrains.annotations.NotNull;

/**
 */
public interface IAccountService {

    CreationOutcomes register(@NotNull String username, @NotNull String password, @NotNull String confirmPassword);

    LoginOutcomes login(@NotNull String name, @NotNull String password);


    enum CreationOutcomes {
        USER_SAVED,
        CONFIRMATION_PASSWORD_DO_NOT_MATCH,
        USERNAME_ALREADY_EXISTS
    }

    enum LoginOutcomes {
        INEXISTING_ACCOUNT,
        INVALID_PASSWORD,
        LOGIN_SUCCEED
    }

}

