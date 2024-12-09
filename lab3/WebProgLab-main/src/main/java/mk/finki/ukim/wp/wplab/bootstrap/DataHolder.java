package mk.finki.ukim.wp.wplab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.wp.wplab.model.Album;
import mk.finki.ukim.wp.wplab.model.Artist;
import mk.finki.ukim.wp.wplab.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Artist> artists;
    public static List<Song> songs;

    public static List<Album> albums;
    @PostConstruct
    public void init()
    {
        artists = new ArrayList<>();
        songs = new ArrayList<>();
        albums = new ArrayList<>();

        artists.add(new Artist(1L, "Kanye", "West", "GOAT"));
        artists.add(new Artist(2L, "Kendrick", "Lamar", "Pulitzer prize winner"));
        artists.add(new Artist(3L, "Metro", "Boomin", "Prod god"));
        artists.add(new Artist(4L, "Jermaine", "Cole", "Izguben"));
        artists.add(new Artist(5L, "MetalFingers", "DOOM", "Rip"));

    }
}
