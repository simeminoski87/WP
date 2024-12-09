package mk.finki.ukim.wp.wplab.web.controller;

import mk.finki.ukim.wp.wplab.model.Album;
import mk.finki.ukim.wp.wplab.model.Artist;
import mk.finki.ukim.wp.wplab.model.Song;
import mk.finki.ukim.wp.wplab.service.AlbumService;
import mk.finki.ukim.wp.wplab.service.ArtistService;
import mk.finki.ukim.wp.wplab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;
    private final AlbumService albumService;
    private final ArtistService artistService;

    public SongController(SongService songService, AlbumService albumService, ArtistService artistService) {
        this.songService = songService;
        this.albumService = albumService;
        this.artistService = artistService;
    }

    @GetMapping
    public String getSongsPage(@RequestParam(required = false) String error, Model model) {
        List<Song> songs = songService.listSongs();
        model.addAttribute("error", error);
        model.addAttribute("songs", songs);
        return "listSongs";
    }

    @PostMapping("/saveSong")
    public String saveSong(@RequestParam String title, @RequestParam String trackId, @RequestParam String genre,
                           @RequestParam int releaseYear, @RequestParam Long albumId) {
        songService.save(title, genre, releaseYear, trackId, albumId);
        return "redirect:/songs";
    }

    @GetMapping("/edit/{songId}")
    public String editSong(@PathVariable Long songId, Model model) {
        Song song = songService.findById(songId);
        List<Album> albums = albumService.findAll();
        model.addAttribute("song", song);
        model.addAttribute("albums", albums);
        return "edit-song";
    }

    @PostMapping("/edit")
    public String saveEditedSong(@RequestParam Long songId, @RequestParam String title, @RequestParam String trackId,
                                 @RequestParam String genre, @RequestParam int releaseYear, @RequestParam Long albumId) {
        songService.editSong(songId, trackId, title, genre, releaseYear, albumId);
        return "redirect:/songs";
    }

    @PostMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id) {
        songService.deleteSong(id);
        return "redirect:/songs";
    }

    @GetMapping("/add-form")
    public String getAddSongPage(Model model) {
        List<Album> albums = albumService.findAll();
        model.addAttribute("albums", albums);
        return "add-song";
    }

    @GetMapping("/details/{songId}")
    public String getSongDetails(@PathVariable Long songId, Model model) {
        Song song = songService.findById(songId);
        model.addAttribute("song", song);
        return "song-details";
    }
//
//    @GetMapping("/add-artist/{songId}")
//    public String getAddArtistPage(@PathVariable Long songId, Model model) {
//        Song song = songService.findById(songId);
//        List<Artist> artists = artistService.listArtists();
//        model.addAttribute("song", song);
//        model.addAttribute("artists", artists);
//        return "add-artist-to-song";
//    }
//
//    @PostMapping("/add-artist")
//    public String addArtistToSong(@RequestParam Long songId, @RequestParam Long artistId) {
//        Song song = songService.findById(songId);
//        Artist artist = artistService.findById(artistId);
////        songService.addArtistToSong(artist, song);
//        return "redirect:/songs/details/" + songId;
//    }
}
