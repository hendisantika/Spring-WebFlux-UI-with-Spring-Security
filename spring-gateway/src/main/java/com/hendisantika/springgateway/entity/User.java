package com.hendisantika.springgateway.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Project : Spring-WebFlux-UI-with-Spring-Security
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 20/09/20
 * Time: 16.43
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 120)
    private String password;

    private boolean enabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<UserRole> userRoles = new HashSet<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public void addUserRoles(UserRole userRole) {
        this.userRoles.add(userRole);
        userRole.setUser(this);
    }

    public void removeUserRoles(UserRole userRole) {
        this.userRoles.remove(userRole);
        userRole.setUser(null);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (username == null) {
            return other.username == null;
        } else return username.equals(other.username);
    }

}

