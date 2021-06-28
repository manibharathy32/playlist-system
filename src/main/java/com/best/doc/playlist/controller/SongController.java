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
import com.best.doc.playlist.model.Song;
import com.best.doc.playlist.service.SongService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author mchellapandian
 *
 */
@Controller
public class SongController {

	@Autowired
	private SongService songService;

	@GetMapping("/songForm")
	public String songForm(Model model) {
		// create model attribute to bind form data
		Song song = new Song();
		model.addAttribute("song", song);
		return "create_song";
	}

	@ApiOperation(value = "createSong", 
			  	  notes = "create a song")
	@ApiResponses({ @ApiResponse(code = 201, 
								 message = "created"),
					@ApiResponse(code = 409, 
								 message = "Conflict", 
								 response = ErrorResponse.class),
		    		@ApiResponse(code = 500, 
		    			 	 	 message = "Internal server error", 
		    			 	 	 response = ErrorResponse.class)})
	@PostMapping("/song")
	public String createSong(@ModelAttribute("song") Song song) {
		Optional<Song> optionalSong = songService.validateSong(song.getName());
		if (optionalSong.isPresent()) {
			throw new ResourceExistException(ErrorCode.RESOURCE_EXITS);
		} else {
			// save song to database
			songService.saveSong(song);
		}
		return "redirect:/playlist";
	}

	@ApiOperation(value = "updateSong", 
				  notes = "update a song")
	@ApiResponses({ @ApiResponse(code = 200, 
								 message = "OK"),
			    	@ApiResponse(code = 500, 
			    			 	 message = "Internal server error", 
			    			 	 response = ErrorResponse.class)})
	@PutMapping("/song")
	public String updateSong(@ModelAttribute("song") Song song) {
		// save song to database
		songService.saveSong(song);
		return "redirect:/playlist";
	}

	@ApiOperation(value = "readSong", 
		      	  notes = "read a song")
	@ApiResponses({ @ApiResponse(code = 200, 
								 message = "OK"),
				    @ApiResponse(code = 500, 
				    			 message = "Internal server error", 
				    			 response = ErrorResponse.class)})
	@GetMapping("/song/{id}")
	public String readSong(@PathVariable(value = "id") long id, Model model) {

		// get song from the service
		Song song = songService.getSongById(id);

		// set song as a model attribute to pre-populate the form
		model.addAttribute("song", song);
		return "update_song";
	}
	
	@ApiOperation(value = "readAllSong", 
			      notes = "read all song")
	@ApiResponses({ @ApiResponse(code = 200, 
							     message = "OK"),
					@ApiResponse(code = 500, 
								 message = "Internal server error", 
								 response = ErrorResponse.class)})
	@GetMapping("/song")
	public String readAllSong(Model model) {
		model.addAttribute("songlist", songService.getAllSongs());
		return "readAll_song";
	}

	@ApiOperation(value = "deleteSong", 
		          notes = "delete a song")
	@ApiResponses({ @ApiResponse(code = 200, 
						     message = "OK"),
					@ApiResponse(code = 500, 
							 message = "Internal server error", 
							 response = ErrorResponse.class)})
	@DeleteMapping("/song/{id}")
	public String deleteSong(@PathVariable(value = "id") long id) {
		// delete song by id 
		songService.deleteSongById(id);
		return "redirect:/song";
	}

}
