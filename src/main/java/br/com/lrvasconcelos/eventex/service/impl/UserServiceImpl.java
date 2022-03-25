package br.com.lrvasconcelos.eventex.service.impl;

import br.com.lrvasconcelos.eventex.domain.entity.User;
import br.com.lrvasconcelos.eventex.domain.repositoy.UsersRepository;
import br.com.lrvasconcelos.eventex.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository repository;

    @Override
    public User create(User user) {
        return repository.save(user);
    }

    public boolean isJSONValid(String json) {
        try {
            return new ObjectMapper().readTree(json) != null;
        } catch (IOException e) {
            return false;
        }
    }
}

