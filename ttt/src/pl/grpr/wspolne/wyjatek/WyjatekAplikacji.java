package pl.grpr.wspolne.wyjatek;

import javax.ejb.ApplicationException;
import javax.xml.ws.WebFault;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

/**
 * Klasa wyjątku aplikacji.
 */
@WebFault(name = "wyjatekAplikacji")
@ApplicationException(rollback = true)
public class WyjatekAplikacji extends RuntimeException {

    private static final long serialVersionUID = -9215239955605001846L;
    private String id;

    /**
     * Konstruktor
     *
     */
    public WyjatekAplikacji(String aReason, Throwable aThrowable) {
        super(aReason, aThrowable);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " (sip:{" + getIP() + "})" + " (guid:{" + getId() + "})";
    }

    public String getSimpleMessage() { return super.getMessage(); }

    /**
     * Zwraca id (wartość referencyjne id).
     *
     * @return id
     */
    public String getId() {
        if (id == null)
            id = UUID.randomUUID().toString();
        return id;
    }

    /**
     * Zwraca iP (wartość referencyjne IP).
     *
     * @return iP
     */
    public String getIP() {
        String pAdres;
        try {
            pAdres = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException uhe) {
            pAdres = "?.?.?.?";
        } catch (NullPointerException npe) {
            pAdres = "?.?.?.?";
        }
        return pAdres;
    }
}
