package pl.grpr.dao.bazowe;

import pl.grpr.jpa.bazowe.BazoweEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by E540 on 2017-01-03.
 */
public abstract class BazoweDao <K, C extends BazoweEntity<K>> implements IBazoweDao<K,C> {
    protected final Class<C> clazz;

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    protected EntityManager em;

    public BazoweDao() {
        Type superclass = getClass().getGenericSuperclass();
        if (getClass().getName().contains("$Proxy")) {
            this.clazz =(Class) ((ParameterizedType) ((Class) superclass).getGenericSuperclass()).getActualTypeArguments()[1];
        } else {
            this.clazz = (Class) ((ParameterizedType) superclass).getActualTypeArguments()[1];
        }
    }

    public C find(K key) {
        return find(key, null);
    }

    public C find(K key, Map<String, Object> hints) {
        if (hints == null || hints.isEmpty())
            return em.find(clazz, key);
        return em.find(clazz, key, hints);
    }

    public void persist(C en) {
        em.persist(en);
    }

    public void delete(C en) {
        if (en.isNew())
            throw  new IllegalArgumentException("Try to remove object without id");
        if (em.contains(en))
            em.remove(en);
        else
            em.remove(merge(en));
    }

    public C merge(C en) {
        return em.merge(en);
    }

    public void refresh(C en) {
        em.refresh(en);
    }

    public long count() {
        return em.createQuery(String.format(COUNT_QUERY, clazz.getName()), Long.class).getSingleResult();
    }

    public void deleteAll() {
        em.createQuery(String.format(DELETE_ALL_QUERY_STRING, clazz.getName())).executeUpdate();
    }

    public void delete(Iterable<C> ids) {
        for(C c : ids){
            delete(c);
        }
    }

    public boolean exists(K id) {
        return find(id) != null;
    }

    public Class<C> domainClass() {
        return clazz;
    }

    public void detach(C object) {
        em.detach(object);
    }

    public void detach(Iterable<C> objects) {
        for(C c : objects){
            detach(c);
        }
    }

    public List<C> findAll(Iterable<K> ids) {
        if(ids != null && ids.iterator().hasNext())
            return em.createQuery("select e from " + clazz.getName() + " e WHERE e.id in (:ids)").setParameter("ids", ids).getResultList();
        return new ArrayList<C>();
    }

    public List<C> findAll() {
        TypedQuery<C> query = em.createQuery("from " + clazz.getName(), clazz);

        return query.getResultList();
    }

    protected TypedQuery<C> createNamedQuery(String query) {
        return em.createNamedQuery(query, clazz);
    }

    /**
     * Zapisuje obiekt. Po zapisie należy używać tylko obiektu zwróconego przez tą metodę - będzie miał poprawnie
     * ustawione pola id i version.
     * @param anEntity obiekt do zapisania
     * @return zapisany obiekt
     */
    public C save(C anEntity) {
        if (anEntity.getId() == null) {
            em.persist(anEntity);
            em.flush(); // żeby poszedł insert
            em.refresh(anEntity); // żeby wróciło id i nr wersji
        } else {
            anEntity = em.merge(anEntity);
        }
        return anEntity;
    }

    protected EntityManager getEntityManager() {
        return em;
    }


}