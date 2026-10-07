package pd5.entity;

import java.util.List;
import java.util.Optional;

public interface EntityManager<ID, E> {

    void save(E entity);

    Optional<E> findById(ID id);

    void deleteById(ID id);

    List<E> findAll();

}
