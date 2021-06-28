/**
 * 
 */
package com.best.doc.playlist.model;

import static com.best.doc.playlist.common.EntityConstant.CREATED_DATE;
import static com.best.doc.playlist.common.EntityConstant.ID;
import static com.best.doc.playlist.common.EntityConstant.MODIFIED_DATE;
import static com.best.doc.playlist.common.EntityConstant.NAME;
import static com.best.doc.playlist.common.EntityConstant.PLAYLIST;

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
@Table(name = PLAYLIST)
@Data
public class Playlist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = ID)
	@ApiModelProperty(notes = "The database generated Playlist ID")	
	private long id;
	
	@Column(name = NAME, length = 50, nullable = false, unique = true)
	@ApiModelProperty(notes = "The Playlist name", required = true)	
	private String name;
	
	@CreationTimestamp
    @Column(name = CREATED_DATE, updatable = false)
	@ApiModelProperty(notes = "The Playlist created date")	
    private Date createdDate;
	
    @UpdateTimestamp
    @Column(name = MODIFIED_DATE)
    @ApiModelProperty(notes = "The Playlist modified date")	
    private Date modifiedDate;
    
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = PLAYLIST)
    private List<PlaylistSong> playlistSong;
}
