package com.capgemini.amazingbank.services.user.model;

import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;

/**
 * @author junidryves@yahoo.fr
 * @version 0.1
 * @since 02-19-2021
 */
@Data
public class UserSearchCriteria {
    private String identifier;
    private String firstName;
    private String surname;
    private String email;

    public boolean isEmpty() {
        return ObjectUtils.isEmpty(identifier)
                && ObjectUtils.isEmpty(firstName)
                && ObjectUtils.isEmpty(surname)
                && ObjectUtils.isEmpty(email);
    }
}
