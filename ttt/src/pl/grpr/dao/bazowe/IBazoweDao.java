package pl.grpr.dao.bazowe;

import pl.grpr.jpa.bazowe.BazoweEntity;

import java.util.List;
import java.util.Map;

/**
 * Interfejs Bazowy DAO.
 */
public interface IBazoweDao<K, C extends BazoweEntity<K>> {
    String COUNT_QUERY = "select count(e) from %s e";
    String DELETE_ALL_QUERY_STRING = "delete from %s x";

    C find(K key);

    C find(K key, Map<String, Object> hints);

    void persist(C en);

    void delete(C en);

    C merge(C en);

    void refresh(C en);

    long count();

    void deleteAll();

    void delete(Iterable<C> ids);

    boolean exists(K id);

    Class<C> domainClass();

    void detach(C object);

    void detach(Iterable<C> objects);

    List<C> findAll();

    List<C> findAll(Iterable<K> ids);
}
