package com.joysistvi.univenrollmentapp.session;

import com.joysistvi.univenrollmentapp.model.User;

// Session Class
// Stores information about the currently logged-in user
public final class Session {

    // Prevent instantiation
    private Session() {
    }

    // Currently logged-in user
    private static User currentUser;

    // Log in a user
    public static void login(User user) {
        currentUser = user;
    }

    // Log out the current user
    public static void logout() {
        currentUser = null;
    }

    // Get the currently logged-in user
    public static User getCurrentUser() {
        return currentUser;
    }

    // Check whether a user is logged in
    public static boolean isLoggedIn() {
        return currentUser != null;
    }
}
