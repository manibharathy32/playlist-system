/**
 * 
 */
package com.best.doc.playlist.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.best.doc.playlist.model.PlaylistSong;

/**
 * @author mchellapandian
 *
 */
@Repository
public interface PlaylistSongRepository extends JpaRepository<PlaylistSong, Long>{
	
	@Transactional
	void deleteByPlaylistIdAndSongId(long playlistId, long songId);

	List<PlaylistSong> findByPlaylistId(long id);
}
