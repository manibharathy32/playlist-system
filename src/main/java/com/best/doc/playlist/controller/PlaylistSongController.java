/**
 * 
 */
package com.best.doc.playlist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.best.doc.playlist.common.exception.model.ErrorResponse;
import com.best.doc.playlist.model.Playlist;
import com.best.doc.playlist.model.PlaylistSong;
import com.best.doc.playlist.model.Song;
import com.best.doc.playlist.model.DTO.PlaylistSongDTO;
import com.best.doc.playlist.service.PlaylistService;
import com.best.doc.playlist.service.PlaylistSongService;
import com.best.doc.playlist.service.SongService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author mchellapandian
 *
 */
@Controller
public class PlaylistSongController {

	@Autowired
	private PlaylistSongService playlistSongService;
	
	@Autowired
	private SongService songService;
	
	@Autowired
	private PlaylistService playlistService;

	@ApiOperation(value = "playlistSongs", 
				  notes = "display playlist songs/ non-playlist songs")
	@ApiResponses({@ApiResponse(code = 200, 
							    message = "OK"),
			       @ApiResponse(code = 500, 
			       				message = "Internal server error", 
			       				response = ErrorResponse.class)})
	@GetMapping("/playlist/{playlistId}/songs")
	public String playlistSongs(@PathVariable(value = "playlistId") long playlistId,
			@RequestParam(value="isPlaylist") boolean isPlaylist, Model model) {

		List<PlaylistSongDTO> playlistSongDTOList = playlistSongService.readAllPlaylistSong(playlistId);
		Playlist playlist = playlistService.getPlaylistById(playlistId);

		if (isPlaylist) {
			if (!playlistSongDTOList.isEmpty()) {
				model.addAttribute("playlistName", playlist.getName());
				model.addAttribute("playlistId", playlist.getId());
				model.addAttribute("playlistSongDTOList", playlistSongDTOList);
			}
		} else {
			if (!playlistSongDTOList.isEmpty()) {
				List<Song> songNotInPlaylists = playlistSongService.readAllNonPlaylistSong(playlistSongDTOList);
				constructModelForSongNotInPlaylists(playlistId, model, playlist, songNotInPlaylists);
			}else {
				List<Song> songNotInPlaylists = songService.getAllSongs();
				constructModelForSongNotInPlaylists(playlistId, model, playlist, songNotInPlaylists);
			}
			return "readAll_non_playlist_songs";
		}

		return "readAll_song_playlist";
	}

	private void constructModelForSongNotInPlaylists(long playlistId, Model model, Playlist playlist,
			List<Song> songNotInPlaylists) {
		if (!songNotInPlaylists.isEmpty()) {
			model.addAttribute("songNotInPlaylists", songNotInPlaylists);
			model.addAttribute("playlistId", playlistId);
			model.addAttribute("playlistName", "Add Songs to " + playlist.getName());
		}
	}

	@ApiOperation(value = "deletePlaylistSong", 
				  notes = "delete a playlist song")
	@ApiResponses({@ApiResponse(code = 200, 
								message = "OK"),
				  @ApiResponse(code = 500, 
				  			   message = "Internal server error", 
				  			   response = ErrorResponse.class)})
	@DeleteMapping("/playlist/{playlistId}/song/{songId}")
	public RedirectView deletePlaylistSong(@PathVariable(value = "playlistId") long playlistId,
			@PathVariable(value = "songId") long songId) {
		playlistSongService.deletePlaylistSong(playlistId, songId);
		RedirectView redirectView = constructRedirectView(playlistId);
		return redirectView;
	}

	private RedirectView constructRedirectView(long playlistId) {
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/playlist-system/playlist/"+playlistId+"/songs?isPlaylist=true");
		return redirectView;
	}

	@GetMapping("/playlist/{playlistId}/song/{songId}")
	public RedirectView createPlaylistSong(@PathVariable(value = "playlistId") long playlistId,
			@PathVariable(value = "songId") long songId) {
		PlaylistSong playlistSong = constructPlaylistSong(playlistId, songId);
		playlistSongService.savePlaylistSong(playlistSong);
		RedirectView redirectView = constructRedirectView(playlistId);
		return redirectView;
	}

	private PlaylistSong constructPlaylistSong(long playlistId, long songId) {
		PlaylistSong playlistSong = new PlaylistSong();
		Playlist playlist = new Playlist();
		playlist.setId(playlistId);
		playlistSong.setPlaylist(playlist);
		Song song = new Song();
		song.setId(songId);
		playlistSong.setSong(song);
		return playlistSong;
	}
}
