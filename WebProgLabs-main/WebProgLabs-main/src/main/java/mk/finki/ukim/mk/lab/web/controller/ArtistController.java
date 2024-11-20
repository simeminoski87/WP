package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.service.ArtistService;
import mk.finki.ukim.mk.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ArtistController {
    private final ArtistService artistService;
    private final SongService songService;

    public ArtistController(ArtistService artistService, SongService songService) {
        this.artistService = artistService;
        this.songService = songService;
    }

    @GetMapping("/artists")
    public String getArtistsList(@RequestParam String trackId, Model model) {
        // Add artists and trackId to the model
        model.addAttribute("artists", artistService.listArtists());
        model.addAttribute("trackId", trackId);

        return "artistsList"; // Refers to artistsList.html in the templates directory
    }

    @PostMapping("/artists")
    public String redirectToSongDetails() {
        // Redirect to the song details page
        return "redirect:/songDetails";
    }
    @GetMapping("/artistSongs")
    public String getArtistSongs(@RequestParam(name = "artistId") Long artistId, Model model) {
        // Find the artist by ID
        Artist artist = artistService.ArtistfindById(artistId).orElse(null);

        if (artist == null) {
            // Handle the case where the artist is not found
            model.addAttribute("errorMessage", "Artist not found.");
            return "listSongs"; // Return an error page if needed
        }

        // Filter songs by the artist
        List<Song> songs = songService.listSongs().stream()
                .filter(song -> song.getPerformers().contains(artist))
                .toList();

        // Add artist and songs to the model
        model.addAttribute("artist", artist);
        model.addAttribute("songs", songs);

        return "artistSongs"; // Refers to artistSongs.html in the templates directory
    }
    @GetMapping("/chooseArtist")
    public String chooseArtist(Model model){
        model.addAttribute("artists",artistService.listArtists());
        return "chooseArtist";
    }
}
