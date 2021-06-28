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
import com.best.doc.playlist.model.Playlist;
import com.best.doc.playlist.repository.PlaylistRepository;
import com.best.doc.playlist.service.PlaylistService;

/**
 * @author mchellapandian
 *
 */
@Service
public class PlaylistServiceImpl implements PlaylistService{
	
	@Autowired
	PlaylistRepository playlistRepository;

	@Override
	public List<Playlist> getAllPlaylists() {
		return playlistRepository.findAll();
	}

	@Override
	public void savePlaylist(Playlist playlist) {
		playlistRepository.save(playlist);
	}

	@Override
	public Playlist getPlaylistById(long id) {
		Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);
		return optionalPlaylist.orElseThrow(() -> new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND));
	}

	@Override
	public void deletePlaylistById(long id) {
		playlistRepository.deleteById(id);
		
	}

	@Override
	public Optional<Playlist> validatePlaylistName(String name) {
		Optional<Playlist> optionalPlaylist =  playlistRepository.findByName(name);
		return optionalPlaylist;
	}
}
