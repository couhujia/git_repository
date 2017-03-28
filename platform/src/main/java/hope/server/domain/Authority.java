package hope.server.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="authority")
public class Authority implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue
	@Id
	private Long Id;
	
	@Column(length=80)
	private String name;
	
	@Column(nullable=false,length=200)
	private String url;
	
	@Column(length=200)
	private String description;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date gmt_create;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date gmt_modified;
	
	@ManyToOne
	@JoinColumn(name="role_id")
	@JsonIgnore
	private Role role;
	
	protected Authority(){}
	
	public Authority(String name,String url,String description,Role role){
		this.name=name;
		this.url=url;
		this.description=description;
		this.role=role;
		this.gmt_create=new Date();
		this.gmt_modified=new Date();
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public void setUrl(String url){
		this.url=url;
	}
	
	public void setDescription(String description){
		this.description=description;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getUrl(){
		return this.url;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public Role getRole(){
		return this.role;
	}
	
	public void setgmtModifies(Date gmt_modified){
		this.gmt_modified=gmt_modified;
	}
	
	public Date getgmtModifies(){
		return this.gmt_modified;
	}
	
	public Date getgmtCreate(){
		return this.gmt_create;
	} 

}
