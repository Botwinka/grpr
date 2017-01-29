package pl.grpr.controller;

import pl.grpr.bazowe.BazoweWspolne;
import pl.grpr.biz.UzytkownikBiz;
import pl.grpr.jpa.UzytkownikEntity;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by E540 on 2017-01-03.
 */
@Named
@ViewScoped
public class UzytkownikController extends BazoweWspolne implements Serializable {
    private static final long serialVersionUID = 5301048198605218332L;

    private UzytkownikEntity uzytkownik;
    private List<UzytkownikEntity> listaUzytkownikow;

    @Inject
    private UzytkownikBiz uzytkownikBiz;

    @PostConstruct
    public void init(){
        listaUzytkownikow = uzytkownikBiz.pobierzListeAktywnychUzytkownikow();
    }

    public UzytkownikEntity getUzytkownik() {
        return uzytkownik;
    }

    public void setUzytkownik(UzytkownikEntity uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    public List<UzytkownikEntity> getListaUzytkownikow() {
        return listaUzytkownikow;
    }

    public void setListaUzytkownikow(List<UzytkownikEntity> listaUzytkownikow) {
        this.listaUzytkownikow = listaUzytkownikow;
    }

}
