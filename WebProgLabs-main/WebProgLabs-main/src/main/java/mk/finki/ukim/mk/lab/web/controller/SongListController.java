package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.service.AlbumService;
import mk.finki.ukim.mk.lab.service.ArtistService;
import mk.finki.ukim.mk.lab.service.SongService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

@Controller
public class SongListController {
    private final SongService songService;
    private final ArtistService artistService;
    private final AlbumService albumService;

    public SongListController(SongService songService, ArtistService artistService, AlbumService albumService) {
        this.songService = songService;
        this.artistService = artistService;
        this.albumService = albumService;
    }
    @GetMapping("/songs")
    public String getSongsPage(@RequestParam(required = false) String error, Model model) {
        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        model.addAttribute("songs", songService.listSongs());
        model.addAttribute("albums1",albumService.findAll());
        return "listSongs"; // Refers to listSongs.html in the templates directory
    }

    @PostMapping("/songs")
    public String redirectToArtists() {
        return "redirect:/artists";
    }

    @GetMapping("/songDetails")
    public String getSongDetails(@RequestParam String trackId,
                                 @RequestParam String artistId,
                                 Model model) {
        // Fetch the song and artist
        Song song = songService.findByTrackId(trackId).orElse(null);
        Artist artist = artistService.ArtistfindById(Long.valueOf(artistId)).orElse(null);

        // Add artist to the song
        if (song != null && artist != null) {
            songService.addArtistToSong(artist, song);
        }

        model.addAttribute("song", song);

        return "songDetails"; // Refers to songDetails.html in the templates directory
    }
    @GetMapping("/add-form")
    public String addSongPage(Model model) {
        model.addAttribute("albums1",albumService.findAll());
        return "add-song";
    }

    @PostMapping("/songs/add")
    public String saveSong(@RequestParam String trackId,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Integer releaseYear,
                           @RequestParam Long albumId,
                           @RequestParam Long id) {
        this.songService.saveSong(trackId,title,genre,releaseYear,albumId,id);
        return "redirect:/songs";
    }
    @GetMapping("/songs/delete/{id}")
    public String deleteSong(@PathVariable Long id){
        this.songService.deleteById(id);
        return "redirect:/songs";
    }

    @GetMapping("songs/edit-form/{id}")
    public String getEditSongForm(@PathVariable Long id,Model model){
        if(this.songService.findById(id).isPresent()){
          Song song=this.songService.findById(id).get();
          model.addAttribute("song",song);
          model.addAttribute("albums1",albumService.findAll());
          return "add-song";
        }
        return "redirect:/songs?error=SongNotFound";
    }
    @PostMapping("songs/edit/{id}")
    public String editSong(@PathVariable Long id,
                           @RequestParam String trackId,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam int releaseYear,
                           @RequestParam Long albumId){
        this.songService.saveSong(trackId,title,genre,releaseYear,albumId,id);
        return "redirect:/songs";
    }


}
