package com.anontech.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.anontech.utils.Utils;
import com.em.validation.client.constraints.NotEmpty;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends Persistent {

	private static final long serialVersionUID = -3831149837939617011L;

    private Long id;
    private String name;
    private String login;
    private String password;
    private Integer role;
    private RoleEnum roleEnum;
    private boolean disabled;
    private boolean loggedIn;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "users")
    @javax.persistence.SequenceGenerator(name = "users",
    sequenceName = "user_seq")
    public Long getId() {
        return this.id;
    }

    public void setId(Long argId) {
        this.id = argId;
    }

    @NotEmpty
    @Column(name = "name", nullable = false, length = 128)
    public String getName() {
        return this.name;
    }

    public void setName(String argName) {
        this.name = Utils.getStrValue(argName).trim();
    }
    
    @NotNull
    @Column(name = "login", nullable = false, length = 16)
    public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@NotEmpty
	@Column(name = "password", nullable = false, length = 32)
    public String getPassword(){
        return this.password;
    }

    public void setPassword(String argPassword) {
        this.password = Utils.getStrValue(argPassword).trim();
    }
    
    @Column(name = "role", nullable = false, columnDefinition = "smallint")
    public Integer getRole() {
        if (roleEnum != null) {
            role = roleEnum.toInt();
        }
        return role;
    }

    public void setRole(Integer argRoleInt) {
        this.role = argRoleInt;
        roleEnum = RoleEnum.fromInt(role);
    }

    @Transient
    public RoleEnum getRoleEnum() {
        return this.roleEnum;
    }

    public void setRoleEnum(RoleEnum argRole) {
        this.roleEnum = argRole;
    }

    @Column(name = "disabled", nullable = false, columnDefinition = "boolean default false")
    public boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @Transient
	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
}