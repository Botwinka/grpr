package pl.grpr.biz;

import pl.grpr.bazowe.BazoweWspolne;
import pl.grpr.dao.UzytkownikDao;
import pl.grpr.jpa.UzytkownikEntity;
import pl.grpr.wspolne.stereotypes.Service;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Klasa UzytkownikBiz odpowiedzialna za logike aplikacji
 * @author JK  on 2017-01-03.
 */
@Service
public class UzytkownikBiz extends BazoweWspolne implements Serializable {
    private static final long serialVersionUID = 7387986239825374428L;

    @Inject
    private UzytkownikDao uzytkownikDao;

    /**
     * Pobiera liste aktywnych z bazy danych
     * @return lista aktywnych uzytkownikow
     */
    public List<UzytkownikEntity> pobierzListeAktywnychUzytkownikow() {
        return uzytkownikDao.pobierzListeAktywnychUzytkownikow();
    }
}
