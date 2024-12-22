package org.community.user.application.Interfaces;

import org.community.user.domain.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(long id);
}
