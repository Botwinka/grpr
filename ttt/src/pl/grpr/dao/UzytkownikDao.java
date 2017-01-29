package pl.grpr.dao;

import pl.grpr.dao.bazowe.BazoweDao;
import pl.grpr.jpa.UzytkownikEntity;
import pl.grpr.wspolne.stereotypes.DAO;

import java.io.Serializable;
import java.util.List;

/**
 * Klasa UzytkownikDao odpowiedzialna za komunikacje z bazą danych
 * @author JK on 2017-01-03.
 */
@DAO
public class UzytkownikDao extends BazoweDao<Integer, UzytkownikEntity> implements Serializable {
    private static final long serialVersionUID = 1616518014639545901L;

    /**
     * Pobiera liste aktywnych z bazy danych
     * @return lista aktywnych uzytkownikow
     */
    public List<UzytkownikEntity> pobierzListeAktywnychUzytkownikow() {
        String query = "SELECT u FROM UzytkownicyEntity " +
                " WHERE u.aktywny = :pAktywny";

        return getEntityManager().createQuery(query)
                .setParameter("pAktywny", true)
                .getResultList();
    }

}
