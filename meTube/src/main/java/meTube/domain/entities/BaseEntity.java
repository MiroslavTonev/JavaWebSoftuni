package meTube.domain.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class BaseEntity {
	
	private String id;
	
	public BaseEntity() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(generator = "uuid-string")
	@GenericGenerator(name = "uuid-string", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", nullable = false, unique = true, updatable = false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
