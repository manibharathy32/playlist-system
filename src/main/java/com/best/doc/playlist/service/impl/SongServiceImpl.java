/**
 * 
 */
package com.best.doc.playlist.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.best.doc.playlist.common.exception.ResourceNotFoundException;
import com.best.doc.playlist.common.exception.model.ErrorCode;
import com.best.doc.playlist.model.Song;
import com.best.doc.playlist.repository.SongRepository;
import com.best.doc.playlist.service.SongService;

/**
 * @author mchellapandian
 *
 */
@Service
public class SongServiceImpl implements SongService {

	@Autowired
	SongRepository songRepository;

	@Override
	public List<Song> getAllSongs() {
		return songRepository.findAll();
	}

	@Override
	public void saveSong(Song song) {
		songRepository.save(song);
	}

	@Override
	public Song getSongById(long id) {
		Optional<Song> optionalSong = songRepository.findById(id);
		return optionalSong.orElseThrow(() -> new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND));
	}

	@Override
	public void deleteSongById(long id) {
		songRepository.deleteById(id);

	}

	@Override
	public Optional<Song> validateSong(String name) {
		Optional<Song> optionalSong = songRepository.findByName(name);
		return optionalSong;
	}

}
