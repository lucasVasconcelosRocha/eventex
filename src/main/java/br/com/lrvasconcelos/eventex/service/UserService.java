package br.com.lrvasconcelos.eventex.service;

import br.com.lrvasconcelos.eventex.domain.entity.User;

import java.util.Optional;

public interface UserService {

    User create(User user);

    Optional<User> findById(Long id);

}
