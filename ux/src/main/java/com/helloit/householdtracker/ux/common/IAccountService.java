package com.helloit.householdtracker.ux.common;

import org.jetbrains.annotations.NotNull;

/**
 */
public interface IAccountService {

    CreationOutcomes register(@NotNull String username, @NotNull String password, @NotNull String confirmPassword);

    enum CreationOutcomes {
        USER_SAVED,
        CONFIRMATION_PASSWORD_DO_NOT_MATCH,
        USERNAME_ALREADY_EXISTS

    }
}

