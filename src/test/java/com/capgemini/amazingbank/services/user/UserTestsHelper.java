package com.capgemini.amazingbank.services.user;

import com.capgemini.amazingbank.entity.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author junidryves@yahoo.fr
 * @version 0.1
 * @since 02-19-2021
 */
public class UserTestsHelper {
    public static List<User> generateFakeUsers(int nbUsers) {
        if (nbUsers < 1) {
            return Collections.emptyList();
        }
        String fakeIdentifier = "c3106be-c00d-4b24-8aa9-66cd7a6a9581";
        String fakeEmail = "myfakeEmail@cab.com";
        String fakeFirstName = "firstname";
        String fakeSurname = "SURNAME";

        List<User> users = new ArrayList<>();
        for (int i = 0; i < nbUsers; i++) {
            User user = new User();
            user.setIdentifier(i + fakeIdentifier);
            user.setEmail(i + fakeEmail);
            user.setFirstName(i + fakeFirstName);
            user.setSurname(i + fakeSurname);
            users.add(user);
        }
        return users;
    }
}
