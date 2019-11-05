package com.rentmate.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity 
@EntityListeners(AuditingEntityListener.class)
@Table(name="image")
public class Image {
	@Id
	@Column(name = "imid") private Integer imid;
	@Column(name = "link") private String link;
	@Column(name = "aid") private Integer aid;
	
	public Image() {}
	
	public Image(int imid, String link, int aid) {
		this.imid = imid;
		this.link = link;
		this.aid = aid;
	}

	public Integer getImid() {
		return imid;
	}

	public void setImid(Integer imid) {
		this.imid = imid;
	}

	

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	
	
}
