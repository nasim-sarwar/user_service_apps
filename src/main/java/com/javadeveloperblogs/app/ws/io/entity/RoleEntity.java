package com.javadeveloperblogs.app.ws.io.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.io.Serializable;
@Entity
@Table(name = "roles")
@Getter
@Setter
public class RoleEntity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3626115357009129227L;
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
}
