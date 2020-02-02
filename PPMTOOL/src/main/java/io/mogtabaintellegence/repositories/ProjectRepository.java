package io.mogtabaintellegence.repositories;

import io.mogtabaintellegence.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Interface 3 on 2/2/2020.
 */
@Repository
public interface ProjectRepository extends CrudRepository<Project,Long>{
    @Override
    Iterable<Project> findAllById(Iterable<Long> iterable);
}
