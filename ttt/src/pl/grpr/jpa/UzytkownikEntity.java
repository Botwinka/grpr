package pl.grpr.jpa;

import pl.grpr.jpa.bazowe.BazoweEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by E540 on 2017-01-03.
 */
@Table(name = "ad_uzytkownicy")
public class UzytkownikEntity extends BazoweEntity<Integer> implements Serializable{
    private static final long serialVersionUID = -4678612355856758526L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;

    private String login;

    private String haslo;

    private String email;

    private String imie;

    private String nazwisko;

    private Boolean aktywny;

    @Temporal(TemporalType.DATE)
    @Column(name = "dataOd")
    private Date dataOd;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_do")
    private Date dataDo;

    private String locale;

    @Column(name = "ilosc_prob_logowania")
    private Integer iloscProbLogowania;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_zmiany_hasla")
    private Date dataZmianyHasla;

    private Integer id_organizacji; //TODO polaczyc z organizacja jak juz bedzie

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Boolean getAktywny() {
        return aktywny;
    }

    public void setAktywny(Boolean aktywny) {
        this.aktywny = aktywny;
    }

    public Date getDataOd() {
        return dataOd;
    }

    public void setDataOd(Date dataOd) {
        this.dataOd = dataOd;
    }

    public Date getDataDo() {
        return dataDo;
    }

    public void setDataDo(Date dataDo) {
        this.dataDo = dataDo;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Integer getIloscProbLogowania() {
        return iloscProbLogowania;
    }

    public void setIloscProbLogowania(Integer iloscProbLogowania) {
        this.iloscProbLogowania = iloscProbLogowania;
    }

    public Date getDataZmianyHasla() {
        return dataZmianyHasla;
    }

    public void setDataZmianyHasla(Date dataZmianyHasla) {
        this.dataZmianyHasla = dataZmianyHasla;
    }

    public Integer getId_organizacji() {
        return id_organizacji;
    }

    public void setId_organizacji(Integer id_organizacji) {
        this.id_organizacji = id_organizacji;
    }

    public Integer getPrimaryKey() {
        return getId();
    }
}
