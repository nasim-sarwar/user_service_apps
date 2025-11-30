package com.javadeveloperblogs.app.ws.io.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.io.Serializable;

@Entity
@Table(name="authorities")
@Getter
@Setter
public class AuthorityEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1965147024258008168L;
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

}
