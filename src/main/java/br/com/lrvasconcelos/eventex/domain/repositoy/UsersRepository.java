package br.com.lrvasconcelos.eventex.domain.repositoy;

import br.com.lrvasconcelos.eventex.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long>, UsersRepositoryCustom {
}
