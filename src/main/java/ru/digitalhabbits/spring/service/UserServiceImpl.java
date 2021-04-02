package ru.digitalhabbits.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.digitalhabbits.spring.domain.Address;
import ru.digitalhabbits.spring.domain.User;
import ru.digitalhabbits.spring.model.AddressInfo;
import ru.digitalhabbits.spring.model.CreateUserRequest;
import ru.digitalhabbits.spring.model.UserResponse;
import ru.digitalhabbits.spring.repository.UserRepository;

import javax.annotation.Nonnull;
import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserServiceImpl
        implements UserService {
    private final UserRepository userRepository;

    @Nonnull
    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> findUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::buildUserResponse)
                .collect(toList());
    }

    @Nonnull
    @Override
    @Transactional(readOnly = true)
    public UserResponse findById(@Nonnull Integer userId) {
        return userRepository.findById(userId)
                .map(this::buildUserResponse)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Nonnull
    @Override
    @Transactional
    public UserResponse create(@Nonnull CreateUserRequest request) {
        final AddressInfo addressInfo = request.getAddress();
        final Address address = new Address()
                .setBuilding(addressInfo.getBuilding())
                .setCity(addressInfo.getCity())
                .setStreet(addressInfo.getStreet());

        final User user = new User()
                .setLogin(request.getLogin())
                .setFirstName(request.getFirstName())
                .setMiddleName(request.getMiddleName())
                .setLastName(request.getLastName())
                .setAge(request.getAge())
                .setAddress(address);

        userRepository.save(user);

        return buildUserResponse(user);
    }

    @Nonnull
    @Override
    @Transactional
    public UserResponse update(@Nonnull Integer userId, @Nonnull CreateUserRequest request) {
        final User user = userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);

        ofNullable(request.getLogin()).ifPresent(user::setLogin);
        ofNullable(request.getFirstName()).ifPresent(user::setFirstName);
        ofNullable(request.getMiddleName()).ifPresent(user::setMiddleName);
        ofNullable(request.getLastName()).ifPresent(user::setLastName);
        ofNullable(request.getAge()).ifPresent(user::setAge);

        final Address address = user.getAddress();
        final AddressInfo addressInfo = request.getAddress();
        ofNullable(addressInfo.getCity()).ifPresent(address::setCity);
        ofNullable(addressInfo.getBuilding()).ifPresent(address::setBuilding);
        ofNullable(addressInfo.getStreet()).ifPresent(address::setStreet);

        return buildUserResponse(user);
    }

    @Override
    @Transactional
    public void delete(@Nonnull Integer userId) {
        userRepository.deleteById(userId);
    }

    @Nonnull
    private UserResponse buildUserResponse(@Nonnull User user) {
        final Address address = user.getAddress();
        return new UserResponse()
                .setId(user.getId())
                .setLogin(user.getLogin())
                .setFirstName(user.getFirstName())
                .setMiddleName(user.getMiddleName())
                .setLastName(user.getLastName())
                .setAge(user.getAge())
                .setAddress(new AddressInfo()
                        .setCity(address.getCity())
                        .setBuilding(address.getBuilding())
                        .setStreet(address.getStreet())
                );
    }
}
