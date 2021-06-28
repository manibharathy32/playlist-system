/**
 * 
 */
package com.best.doc.playlist.service;

import java.util.List;
import java.util.Optional;

import com.best.doc.playlist.model.Playlist;

/**
 * @author mchellapandian
 *
 */
public interface PlaylistService {

	List<Playlist> getAllPlaylists();

	void savePlaylist(Playlist playlist);

	Playlist getPlaylistById(long id);

	void deletePlaylistById(long id);
	
	Optional<Playlist> validatePlaylistName(String name);

}
