package com.capgemini.amazingbank.services.user;

import com.capgemini.amazingbank.entity.User;
import com.capgemini.amazingbank.entity.User_;
import com.capgemini.amazingbank.exception.ExceptionsManager;
import com.capgemini.amazingbank.services.user.model.UserSearchCriteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

import static com.capgemini.amazingbank.exception.FunctionalExceptions.Error.USER_NOT_FOUND;
import static com.capgemini.amazingbank.exception.FunctionalExceptions.RelatedResource.USER;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;
import static org.springframework.util.ObjectUtils.isEmpty;
import static org.springframework.util.StringUtils.capitalize;

/**
 * @author junidryves@yahoo.fr
 * @version 0.1
 * @since 02-19-2021
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final ExceptionsManager exceptionsManger;

    /**
     * Get all the users
     *
     * @return The list of all users
     */
    public List<User> getUsers() {
        log.info("Requesting list of users.");
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, User_.SURNAME));
    }

    /**
     * Search users given search criteria
     *
     * @param searchCriteria search criteria
     * @return the list of users matching the given criteria
     */
    public List<User> searchUsers(UserSearchCriteria searchCriteria) {
        log.info("Search users given criteria");
        if (searchCriteria.isEmpty()) {
            userRepository.findAll(Sort.by(Sort.Direction.ASC, User_.SURNAME));
        }
        return userRepository.findAll((root, query, cb) -> transformSearchCriteriaToPredicate(root, query, cb, searchCriteria), Sort.by(Sort.Direction.ASC, User_.SURNAME));
    }

    private Predicate transformSearchCriteriaToPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb, UserSearchCriteria searchCriteria) {
        Predicate searchCriteriaPredicate = cb.conjunction();
        if (isNotEmpty(searchCriteria.getEmail())) {
            searchCriteriaPredicate.getExpressions().add(cb.equal(root.get(User_.email), searchCriteria.getEmail().toLowerCase()));
        }
        if (isNotEmpty(searchCriteria.getFirstName())) {
            searchCriteriaPredicate.getExpressions().add(cb.equal(root.get(User_.firstName), capitalize(searchCriteria.getFirstName().toLowerCase())));
        }
        if (isNotEmpty(searchCriteria.getSurname())) {
            searchCriteriaPredicate.getExpressions().add(cb.equal(root.get(User_.surname), searchCriteria.getSurname().toUpperCase()));
        }
        if (isNotEmpty(searchCriteria.getIdentifier())) {
            searchCriteriaPredicate.getExpressions().add(cb.equal(root.get(User_.identifier), searchCriteria.getIdentifier().toLowerCase()));
        }
        return searchCriteriaPredicate;
    }


    /**
     * Get a user given an identifier parameter
     *
     * @param userIdentifier a user identifier
     * @return a user entity
     */
    public User getUser(String userIdentifier) {
        log.info("Getting user given a user identifier");
        User user = userRepository.findById(userIdentifier).orElse(null);
        if (isEmpty(user)) {
            exceptionsManger.logAndThrowHttpClientErrorException(USER_NOT_FOUND.getLabel(), USER.getLabel(), userIdentifier);
        }
        return user;
    }
}
