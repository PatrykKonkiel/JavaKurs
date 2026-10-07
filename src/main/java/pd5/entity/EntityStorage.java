package pd5.entity;

import java.util.*;

public class EntityStorage<ID, E extends Indentifiable<ID>>
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
            System.err.println("There is no entity with id: " + id);
        }
        return entity;
    }

    @Override
    public void deleteById(ID id) {
        if (id != null || entities.containsKey(id)) {
            entities.remove(id);
        } else {
            throw new IllegalArgumentException("There is no entity with id: " + id);
        }
    }

    @Override
    public List<E> findAll() {
        List<E> result = new ArrayList<>(entities.values());
        result.forEach(System.out::println);
        return result;
    }
}
