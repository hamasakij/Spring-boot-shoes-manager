package com.coltware.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
@Data
public class BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;

    /**
     * 作成日
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdDate;

    /**
     * 更新日
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    /**
     * 削除日
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedDate;

    /**
	 * 削除
	 */
	private Boolean deleted;


    /**
     * データ作成時　作成日に現在日付を設定
     * `@PrePersist` を使うことにより作成処理前に自動でコールされる
     */
    @PrePersist
    public void addCreatedDate() {
        Date now = new Date();
        this.setCreatedDate(now);
        this.setUpdatedDate(now);
    }

    /**
     * データ更新時　更新日に現在日付を設定
     * `@PreUpdate` を使うことにより作成処理前に自動でコールされる
     */
    @PreUpdate
    public void addUpdatedDate() {
        Date now = new Date();
        this.setUpdatedDate(now);
    }

    /**
     * データ論理削除時　削除日に現在日付を設定
     */
    public void addDeletedDate() {
        Date now = new Date();
        this.setDeletedDate(now);
    }
    /**
     * データの論理削除
     */
    public void addDeleted() {
    	this.setDeleted(true);
    }
}

