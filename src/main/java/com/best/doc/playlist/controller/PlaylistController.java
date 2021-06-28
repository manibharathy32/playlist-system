/**
 * 
 */
package com.best.doc.playlist.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.best.doc.playlist.common.exception.ResourceExistException;
import com.best.doc.playlist.common.exception.model.ErrorCode;
import com.best.doc.playlist.common.exception.model.ErrorResponse;
import com.best.doc.playlist.model.Playlist;
import com.best.doc.playlist.service.PlaylistService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author mchellapandian
 *
 */
@Controller
public class PlaylistController {

	@Autowired
	private PlaylistService playlistService;

	@ApiOperation(value = "readAllPlaylist", 
				  notes = "read all playlist")
	@ApiResponses({ @ApiResponse(code = 200, 
	 							 message = "OK"),
					@ApiResponse(code = 500, 
								 message = "Internal server error", 
								 response = ErrorResponse.class)})
	@GetMapping("/playlist")
	public String readAllPlaylist(Model model) {
		model.addAttribute("playlists", playlistService.getAllPlaylists());
		return "index";
	}

	@GetMapping("/playlistForm")
	public String playlistForm(Model model) {
		// create model attribute to bind form data
		Playlist playlist = new Playlist();
		model.addAttribute("playlist", playlist);
		return "create_playlist";
	}

	@ApiOperation(value = "createPlaylist", 
			      notes = "Create a Playlist")
	@ApiResponses({ @ApiResponse(code = 201, 
	 							 message = "OK"),
					@ApiResponse(code = 500, 
								 message = "Internal server error", 
								 response = ErrorResponse.class),
					@ApiResponse(code = 409, 
					 			 message = "Conflict", 
					 			 response = ErrorResponse.class)})
	@PostMapping("/playlist")
	public String createPlaylist(@ModelAttribute("playlist") Playlist playlist) {
		
		Optional<Playlist> optionalPlaylist = playlistService.validatePlaylistName(playlist.getName());
		if (optionalPlaylist.isPresent()) {
			throw new ResourceExistException(ErrorCode.RESOURCE_EXITS);
		} else {
			// save playlist to database
			playlistService.savePlaylist(playlist);
		}
		return "redirect:/playlist";
	}
	
	@ApiOperation(value = "updatePlaylist", 
				  notes = "Create a Playlist")
	@ApiResponses({ @ApiResponse(code = 200, 
								 message = "OK"),
					@ApiResponse(code = 500, 
								 message = "Internal server error", 
								 response = ErrorResponse.class),
					@ApiResponse(code = 409, 
								 message = "Conflict", 
								 response = ErrorResponse.class) })
	@PutMapping("/playlist")
	public String updatePlaylist(@ModelAttribute("playlist") Playlist playlist) {

		// save playlist to database
		playlistService.savePlaylist(playlist);
		return "redirect:/playlist";
	}

	@ApiOperation(value = "readPlaylist", 
		      	 notes = "read a Playlist")
	@ApiResponses({ @ApiResponse(code = 200, 
								 message = "OK"),
					@ApiResponse(code = 500, 
							 	 message = "Internal server error", 
							 	 response = ErrorResponse.class)})
	@GetMapping("/playlist/{id}")
	public String readPlaylist(@PathVariable(value = "id") long id, Model model) {

		// get playlist from the service
		Playlist playlist = playlistService.getPlaylistById(id);

		// set playlist as a model attribute to pre-populate the form
		model.addAttribute("playlist", playlist);
		return "update_playlist";
	}

	@ApiOperation(value = "deletePlaylist", 
				  notes = "delete a Playlist")
	@ApiResponses({ @ApiResponse(code = 200, 
								 message = "OK"),
					@ApiResponse(code = 500, 
								 message = "Internal server error. An unexpected error occurred", 
								 response = ErrorResponse.class)})
	@DeleteMapping("/playlist/{id}")
	public String deletePlaylist(@PathVariable(value = "id") long id) {

		// call delete playlist method
		playlistService.deletePlaylistById(id);
		return "redirect:/playlist";
	}
}
