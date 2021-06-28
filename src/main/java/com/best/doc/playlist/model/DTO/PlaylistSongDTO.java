/**
 * 
 */
package com.best.doc.playlist.model.DTO;

import lombok.Data;

/**
 * @author mchellapandian
 *
 */
@Data
public class PlaylistSongDTO {

	private long playlistId;
	private String playlistName;
	private long songId;
	private String songName;
	private String singerName;
	private long playlistSongId;
}
