package hope.server.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity(name="user")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false,length=20)
	private String name;
	
	@Column(length=20)
	private String phone;
	
	@Column(length=50)
	private String email;
	
	@Column(nullable=false,length=20)
	@JsonIgnore
	private String password;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date gmt_create;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date gmt_modified;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date last_login_time;
	
	@ManyToOne(optional=true)
	@JoinColumn(name="role_id")
	@JsonIgnore
	private Role role;
	
	protected User(){}
	
	public User(String name,String phone,String email,String password,
				Role role){
		this.name=name;
		this.phone=phone;
		this.email=email;
		this.password=password;
		this.role=role;
		this.gmt_create=new Date();
		this.gmt_modified=new Date();
		this.last_login_time=new Date();
	}
	
	public Role getRole(){
		return this.role;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public String getPhone(){
		return this.phone;
	}
	
	public String getPassword(){
		return this.password;
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
	
	public Date getLastLoginTime(){
		return this.last_login_time;
	}
	
	
	
	
	
}
