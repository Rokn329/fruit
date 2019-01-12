package com.fruit.sorb.common.po;

import java.io.Serializable;
import java.util.Date;

public class BasePojo implements Serializable {

    private Date created;

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    private Date updated;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
