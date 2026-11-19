package pd15.repository;

import java.util.Optional;

public interface InMemoryRepository<T> {
    Optional<T> findById(Long id);

}
