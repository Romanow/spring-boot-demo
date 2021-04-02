package ru.digitalhabbits.spring.service;

import ru.digitalhabbits.spring.model.CreateUserRequest;
import ru.digitalhabbits.spring.model.UserResponse;

import javax.annotation.Nonnull;
import java.util.List;

public interface UserService {
    @Nonnull
    List<UserResponse> findUsers();

    @Nonnull
    UserResponse findById(@Nonnull Integer userId);

    @Nonnull
    UserResponse create(@Nonnull CreateUserRequest request);

    @Nonnull
    UserResponse update(@Nonnull Integer userId, @Nonnull CreateUserRequest request);

    void delete(@Nonnull Integer userId);
}
