package com.sixmac.entity;

import com.sixmac.annotation.Exclude;
import com.sixmac.utils.EntityListener;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@MappedSuperclass
@EntityListeners(EntityListener.class)
public abstract class BaseEntity implements Serializable{

	private static final long serialVersionUID = 834933802102253846L;

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name="create_date")
	private Long createDate;

	@Exclude
	@Column(name="modify_date")
	private Long updateDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public Long getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Long updateDate) {
		this.updateDate = updateDate;
	}
}
