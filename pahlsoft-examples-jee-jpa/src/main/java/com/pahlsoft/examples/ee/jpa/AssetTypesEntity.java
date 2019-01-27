package com.pahlsoft.examples.ee.jpa;

import javax.persistence.*;

/**
 * Created by aj on 3/19/2014.
 */
@Entity
@Table(name = "asset_types", schema = "", catalog = "megaevent")
public class AssetTypesEntity {
    private short assetTypeId;
    private String assetType;

    @Id
    @Column(name = "asset_typeID", nullable = false, insertable = true, updatable = true)
    public short getAssetTypeId() {
        return assetTypeId;
    }

    public void setAssetTypeId(short assetTypeId) {
        this.assetTypeId = assetTypeId;
    }

    @Basic
    @Column(name = "asset_type", nullable = true, insertable = true, updatable = true, length = 45)
    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssetTypesEntity that = (AssetTypesEntity) o;

        if (assetTypeId != that.assetTypeId) return false;
        if (assetType != null ? !assetType.equals(that.assetType) : that.assetType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) assetTypeId;
        result = 31 * result + (assetType != null ? assetType.hashCode() : 0);
        return result;
    }
}
