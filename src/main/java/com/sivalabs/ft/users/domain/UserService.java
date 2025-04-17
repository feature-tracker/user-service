package com.sivalabs.ft.users.domain;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Long createOrUpdateUser(SyncUserCommand cmd) {
        var user = userRepository.findByUuid(cmd.uuid()).orElse(new User());
        user.setUuid(cmd.uuid());
        user.setEmail(cmd.email());
        user.setFullName(cmd.fullName());
        user.setRole(cmd.role());
        userRepository.save(user);
        return user.getId();
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
