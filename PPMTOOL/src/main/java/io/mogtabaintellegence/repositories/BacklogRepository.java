package io.mogtabaintellegence.repositories;

import io.mogtabaintellegence.domain.Backlog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Interface 3 on 2/15/2020.
 */
@Repository
public interface BacklogRepository extends CrudRepository<Backlog,Long>{
}
