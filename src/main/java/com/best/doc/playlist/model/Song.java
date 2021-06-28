/**
 * 
 */
package com.best.doc.playlist.model;

import static com.best.doc.playlist.common.EntityConstant.CREATED_DATE;
import static com.best.doc.playlist.common.EntityConstant.ID;
import static com.best.doc.playlist.common.EntityConstant.MODIFIED_DATE;
import static com.best.doc.playlist.common.EntityConstant.NAME;
import static com.best.doc.playlist.common.EntityConstant.SINGER;
import static com.best.doc.playlist.common.EntityConstant.SONG;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author mchellapandian
 *
 */
@Entity
@Table(name = SONG)
@Data
public class Song {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = ID)
	@ApiModelProperty(notes = "The database generated Song ID")	
	private long id;
	
	@Column(name = NAME, length = 100, nullable = false, unique = true)
	@ApiModelProperty(notes = "The database generated Song ID", required = true)	
	private String name;
	
	@Column(name = SINGER, length = 100, nullable = false)
	@ApiModelProperty(notes = "The Singer Name", required = true)
	private String singer;
	
	@CreationTimestamp
    @Column(name = CREATED_DATE, updatable = false)
    private Date createdDate;
	
    @UpdateTimestamp
    @Column(name = MODIFIED_DATE)
    private Date modifiedDate;
    
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = SONG)
    private List<PlaylistSong> playlistSong;
}
