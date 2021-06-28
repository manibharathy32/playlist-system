/**
 * 
 */
package com.best.doc.playlist.model;

import static com.best.doc.playlist.common.EntityConstant.ID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author mchellapandian
 *
 */
@Entity
@Table(name = "playlist_song")
@Data
public class PlaylistSong {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = ID)
	@ApiModelProperty(notes = "The database generated PlaylistSong ID")	
	private long id;

	@ManyToOne
	@JoinColumn(name = "playlist_id")
	@ApiModelProperty(notes = "The Playlist")	
	private Playlist playlist;
	
	@ManyToOne
	@JoinColumn(name = "song_id")
	@ApiModelProperty(notes = "The Song")	
	private Song song;
}
