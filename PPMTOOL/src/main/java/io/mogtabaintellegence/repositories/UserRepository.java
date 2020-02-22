package io.mogtabaintellegence.repositories;

import io.mogtabaintellegence.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Interface 3 on 2/21/2020.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
