package mk.finki.ukim.wp.wplab.web.controller;

import mk.finki.ukim.wp.wplab.model.Album;
import mk.finki.ukim.wp.wplab.service.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public String getAlbumsPage(Model model) {
        List<Album> albums = albumService.findAll();
        model.addAttribute("albums", albums);
        return "list-albums";
    }

    @GetMapping("/add-form")
    public String getAddAlbumPage() {
        return "add-album";
    }

    @PostMapping("/save")
    public String saveAlbum(@RequestParam String name, @RequestParam String genre,
                            @RequestParam String releaseYear) {
        albumService.save(name, genre, releaseYear);
        return "redirect:/albums";
    }

    @GetMapping("/edit/{albumId}")
    public String editAlbum(@PathVariable Long albumId, Model model) {
        Album album = albumService.findById(albumId).orElseThrow(() -> new RuntimeException("Album not found"));
        model.addAttribute("album", album);
        return "edit-album";
    }

    @PostMapping("/edit")
    public String saveEditedAlbum(@RequestParam Long albumId, @RequestParam String name,
                                  @RequestParam String genre, @RequestParam String releaseYear) {
        albumService.edit(albumId, name, genre, releaseYear);
        return "redirect:/albums";
    }

    @PostMapping("/delete/{albumId}")
    public String deleteAlbum(@PathVariable Long albumId) {
        albumService.delete(albumId);
        return "redirect:/albums";
    }
}
