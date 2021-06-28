/**
 * 
 */
package com.best.doc.playlist.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.best.doc.playlist.model.Playlist;

/**
 * @author mchellapandian
 *
 */
@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long>{

	Optional<Playlist> findByName(String name);
}
