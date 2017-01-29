package pl.grpr.jpa.bazowe;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * Klasa bazowa encji.
 */
@MappedSuperclass
public abstract class BazoweEntity<K> implements Serializable {

    private static final long serialVersionUID = -4683386985527826561L;
    private static int creationId = 0;

    @Transient
    protected int myCreationId = creationId++;

    public abstract K getId();

    public abstract void setId(K id);

    /**
     * Sprawdza czy obiekt jest nowy (nieutrwalony).
     *
     * @return True, jeśli obiekt jest nowy i nie został jeszcze utrwalony.
     */
    public boolean isNew() {
        return getId() == null;
    }

    /**
     * Zwraca hashCode dla obiektu.
     *
     * @return hashCode wyliczony na podstawie klucza obiektu.
     */
    @Override
    public int hashCode() {
        return isNew() ? super.hashCode() : getId().hashCode();
    }

    /**
     * Zapewnia podstawową implementację equals dla obiektów encji.
     *
     * @param object Drugi obiekt do porównania.
     * @return True, jeśli porównywane obiekty są identyczne.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null)
            return false;
        if (getClass() != object.getClass())
            return false;

        BazoweEntity other = (BazoweEntity) object;
        if (this.getId() != other.getId() && (this.getId() == null
                || !this.getId().equals(other.getId()))) {
            return false;
        }
        if (this.getId() == null && other.getId() == null) {
            // dla niezapisanych sprawdzamy po myCreationId
            return this.myCreationId == other.myCreationId;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + (isNew() ? "creationId=" + myCreationId : "entityId=" + getId());
    }
}

