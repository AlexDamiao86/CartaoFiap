package br.com.fiap.cartao.entity;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "usuarios")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	@Column(nullable = false, unique = true)
	private String email; 
	@Column(nullable = false)
	private String senha;
	@ManyToOne(optional = true)
	private Cliente cliente;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Perfil> perfis = new HashSet<>();
	
	public Usuario(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}
	
	@Column(nullable = false, updatable = false, name = "data_criacao")
	@CreatedDate
	private Date dataCriacao;
	
	@Column(nullable = false, updatable = true, name = "data_alteracao")
	@LastModifiedDate
	private Date dataUltimaAlteracao;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.perfis;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
