package br.com.lrvasconcelos.eventex.service;

import br.com.lrvasconcelos.eventex.domain.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {

    User create(User user);

    Optional<User> findById(Long id);

    void deleteUser(User user);

    List<User> findByEmails(Set<String> emails);
}
