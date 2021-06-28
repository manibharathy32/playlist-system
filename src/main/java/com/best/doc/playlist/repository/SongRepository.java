/**
 * 
 */
package com.best.doc.playlist.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.best.doc.playlist.model.Song;

/**
 * @author mchellapandian
 *
 */
@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

	List<Song> findByIdIsNotIn(List<Long> songIds);

	Optional<Song> findByName(String name);

}
