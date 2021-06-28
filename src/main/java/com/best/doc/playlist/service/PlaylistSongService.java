/**
 * 
 */
package com.best.doc.playlist.service;

import java.util.List;

import com.best.doc.playlist.model.PlaylistSong;
import com.best.doc.playlist.model.Song;
import com.best.doc.playlist.model.DTO.PlaylistSongDTO;

/**
 * @author mchellapandian
 *
 */
public interface PlaylistSongService {

	void deletePlaylistSong(long playlistId, long songId);

	List<PlaylistSongDTO> readAllPlaylistSong(long id);

	List<Song> readAllNonPlaylistSong(List<PlaylistSongDTO> playlistSongDTOList);

	void savePlaylistSong(PlaylistSong playlistSong);
}
