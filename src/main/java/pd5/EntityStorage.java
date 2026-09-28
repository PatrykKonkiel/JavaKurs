package pd5;

import java.util.*;

public class EntityStorage<ID, E extends Entity<ID>>
        implements EntityManager<ID, E> {

    private final Map<ID, E> entities = new HashMap<>();

    @Override
    public void save(E entity) {
        entities.put(entity.getId(), entity);
    }

    @Override
    public Optional<E> findById(ID id) {
        Optional<E> entity = Optional.ofNullable(entities.get(id));
        if (entity.isEmpty()) {
            System.err.println("There is no user with id: " + id);
        }
        return entity;
    }

    @Override
    public void deleteById(ID id) {
        if (entities.containsKey(id) && id != null) {
            entities.remove(id);
        } else {
            throw new IllegalArgumentException("There is no user with id: " + id);
        }

    }

    @Override
    public List<E> findAll() {
         List<E> result = new ArrayList<>(entities.values());
         result.forEach(System.out::println);
         return result;
    }
}
