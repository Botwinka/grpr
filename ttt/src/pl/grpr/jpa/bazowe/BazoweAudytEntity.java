package pl.grpr.jpa.bazowe;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by E540 on 2017-01-05.
 */
@MappedSuperclass
public abstract class BazoweAudytEntity extends BazoweEntity implements Serializable {

    private static final long serialVersionUID = 1866495337456909939L;
    @Column(name = "created_by")
    protected Integer createdBy;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_date")
    protected Date creationDate;

    @Column(name = "updated_by")
    protected Integer lastUpdatedBy;

    @Temporal(TemporalType.DATE)
    @Column(name = "updated_date")
    protected Date lastUpdateDate;


    @PrePersist
    public void prePersist() {
        Date current = new Date();
        setCreationDate(current);
        setLastUpdateDate(getCreationDate());
        setLastUpdatedBy(getCreatedBy());
    }

    @PreUpdate
    public void preUpdate() {
        setLastUpdateDate(new Date());
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public String toString() {
        return super.toString() + " Audit:{\ncreatedBy:" + createdBy + "\ncreationDate:" + creationDate +
                "\nlastUpdateBy:\" + lastUpdatedBy + \"\\nlastUpdateDate:\" + lastUpdateDate + \"\n}";
    }
}
