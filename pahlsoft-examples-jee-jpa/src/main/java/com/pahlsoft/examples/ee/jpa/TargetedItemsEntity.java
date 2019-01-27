package com.pahlsoft.examples.ee.jpa;

import javax.persistence.*;

/**
 * Created by aj on 3/19/2014.
 */
@Entity
@Table(name = "targeted_items", schema = "", catalog = "megaevent")
public class TargetedItemsEntity {
    private int targetedItemsId;
    private String assetName;
    private Integer assetTypeId;

    @Id
    @Column(name = "targeted_itemsID", nullable = false, insertable = true, updatable = true)
    public int getTargetedItemsId() {
        return targetedItemsId;
    }

    public void setTargetedItemsId(int targetedItemsId) {
        this.targetedItemsId = targetedItemsId;
    }

    @Basic
    @Column(name = "asset_name", nullable = true, insertable = true, updatable = true, length = 45)
    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    @Basic
    @Column(name = "asset_typeID", nullable = true, insertable = true, updatable = true)
    public Integer getAssetTypeId() {
        return assetTypeId;
    }

    public void setAssetTypeId(Integer assetTypeId) {
        this.assetTypeId = assetTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TargetedItemsEntity that = (TargetedItemsEntity) o;

        if (targetedItemsId != that.targetedItemsId) return false;
        if (assetName != null ? !assetName.equals(that.assetName) : that.assetName != null) return false;
        if (assetTypeId != null ? !assetTypeId.equals(that.assetTypeId) : that.assetTypeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = targetedItemsId;
        result = 31 * result + (assetName != null ? assetName.hashCode() : 0);
        result = 31 * result + (assetTypeId != null ? assetTypeId.hashCode() : 0);
        return result;
    }
}
