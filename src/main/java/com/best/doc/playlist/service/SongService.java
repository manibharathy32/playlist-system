/**
 * 
 */
package com.best.doc.playlist.service;

import java.util.List;
import java.util.Optional;

import com.best.doc.playlist.model.Song;

/**
 * @author mchellapandian
 *
 */
public interface SongService {

	List<Song> getAllSongs();

	void saveSong(Song song);

	Song getSongById(long id);

	void deleteSongById(long id);
	
	Optional<Song> validateSong(String name);

}
