/**
 * 
 */
package com.easyms.training.sampleapp.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ebouhdadi
 *
 */

@Entity
@Table(name = "CLIENT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {

	@Id
	@GeneratedValue
	@Column(name = "Id", nullable = false)
	private Long id;
	
	@Column(name = "First_Name", length = 64, nullable = false)
	private String firstname;
	
	@Column(name = "Last_Name", length = 64, nullable = false)
    private String lastname;

}
