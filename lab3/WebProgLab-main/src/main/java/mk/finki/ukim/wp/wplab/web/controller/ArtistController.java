package mk.finki.ukim.wp.wplab.web.controller;

import mk.finki.ukim.wp.wplab.model.Artist;
import mk.finki.ukim.wp.wplab.model.Song;
import mk.finki.ukim.wp.wplab.service.ArtistService;
import mk.finki.ukim.wp.wplab.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Controller
@RequestMapping("/artist")
public class ArtistController {

    private final SpringTemplateEngine springTemplateEngine;
    private final ArtistService artistService;
    private final SongService songService;

    @Autowired
    public ArtistController(SpringTemplateEngine springTemplateEngine, ArtistService artistService, SongService songService) {
        this.springTemplateEngine = springTemplateEngine;
        this.artistService = artistService;
        this.songService = songService;
    }

    @GetMapping
    public String getArtistsPage(@RequestParam(required = false) String trackId, Model model) {
        model.addAttribute("trackId", trackId);
        model.addAttribute("artists", this.artistService.listArtists());
        return "artistsList";
    }

//    @PostMapping
//    public String addArtistToSong(@RequestParam String trackId, @RequestParam Long artistId, Model model) {
//        Song song = songService.findByTrackId(trackId);
//        Artist artist = artistService.findById(artistId);
//
//        songService.addArtistToSong(artist, song);
//
//        model.addAttribute("trackId", song.getTrackId());
//        model.addAttribute("artistId", artist.getId());
//        model.addAttribute("song", song);
//
//        return "song-details";
//    }
}
