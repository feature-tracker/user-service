package com.sivalabs.ft.users.domain;

import java.util.Optional;
import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<User, Long> {
    Optional<User> findByUuid(String uuid);
}
