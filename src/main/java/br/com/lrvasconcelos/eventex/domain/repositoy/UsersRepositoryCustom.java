package br.com.lrvasconcelos.eventex.domain.repositoy;

import br.com.lrvasconcelos.eventex.domain.entity.User;

import java.util.List;
import java.util.Set;

public interface UsersRepositoryCustom {

    List<User> findUsers(Set<String> emails);
}
