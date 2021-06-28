/**
 * 
 */
package com.best.doc.playlist.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.best.doc.playlist.model.PlaylistSong;
import com.best.doc.playlist.model.Song;
import com.best.doc.playlist.model.DTO.PlaylistSongDTO;
import com.best.doc.playlist.repository.PlaylistSongRepository;
import com.best.doc.playlist.repository.SongRepository;
import com.best.doc.playlist.service.PlaylistSongService;

/**
 * @author mchellapandian
 *
 */
@Service
public class PlaylistSongServiceImpl implements PlaylistSongService {
	
	@Autowired
	PlaylistSongRepository playlistSongRepository;
	
	@Autowired
	SongRepository songRepository;

	@Override
	public List<PlaylistSongDTO> readAllPlaylistSong(long id) {
		List<PlaylistSong> playlistSongs = playlistSongRepository.findByPlaylistId(id);
		List<PlaylistSongDTO> playlistSongDTOList = new ArrayList<>();
		if(!playlistSongs.isEmpty()) {
			for (PlaylistSong playlistSong : playlistSongs) {
				constructPlaylistSongDTO(playlistSongDTOList, playlistSong);
			}
		}
		return playlistSongDTOList;
	}

	private void constructPlaylistSongDTO(List<PlaylistSongDTO> playlistSongDTOList, PlaylistSong playlistSong) {

		PlaylistSongDTO playlistSongDTO = new PlaylistSongDTO();
		playlistSongDTO.setPlaylistId(playlistSong.getPlaylist().getId());
		playlistSongDTO.setPlaylistName(playlistSong.getPlaylist().getName());
		playlistSongDTO.setSongName(playlistSong.getSong().getName());
		playlistSongDTO.setSingerName(playlistSong.getSong().getSinger());
		playlistSongDTO.setSongId(playlistSong.getSong().getId());
		playlistSongDTO.setPlaylistSongId(playlistSong.getId());
		playlistSongDTOList.add(playlistSongDTO);
	}

	@Override
	public void deletePlaylistSong(long playlistId, long songId) {
		playlistSongRepository.deleteByPlaylistIdAndSongId(playlistId, songId);
	}

	@Override
	public List<Song> readAllNonPlaylistSong(List<PlaylistSongDTO> playlistSongDTOList) {
		List<Long> songIds = playlistSongDTOList.stream().map(x -> x.getSongId()).collect(Collectors.toList());
		List<Song> songNotInPlaylist;
		if(songIds.isEmpty()) {
			songNotInPlaylist = songRepository.findAll();
		}else {
			songNotInPlaylist = songRepository.findByIdIsNotIn(songIds);
		}
		return songNotInPlaylist;
	}

	@Override
	public void savePlaylistSong(PlaylistSong playlistSong) {
		playlistSongRepository.save(playlistSong);
		
	}
}
