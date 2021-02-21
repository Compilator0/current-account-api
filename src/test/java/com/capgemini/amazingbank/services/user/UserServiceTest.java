package com.capgemini.amazingbank.services.user;

import com.capgemini.amazingbank.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.capgemini.amazingbank.services.user.UserTestsHelper.generateFakeUsers;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author junidryves@yahoo.fr
 * @version 0.1
 * @since 02-19-2021
 */
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    @DisplayName("Test get all users method.")
    void getUsersTest() {
        when(userRepository.findAll(any(Sort.class))).thenReturn(generateFakeUsers(3));
        List<User> users = userService.getUsers();
        Assertions.assertThat(users).isNotEmpty();
    }
}