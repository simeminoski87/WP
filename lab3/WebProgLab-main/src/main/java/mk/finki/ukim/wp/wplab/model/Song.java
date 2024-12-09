package mk.finki.ukim.wp.wplab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mk.finki.ukim.wp.wplab.bootstrap.DataHolder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String trackId;
    @Setter
    private String title;

    @Setter
    private String genre;

    @Setter
    private int releaseYear;

    @ManyToMany
    private List<Artist> performers;

    @ManyToOne
    @Setter
    private Album album;

    public Song(String trackId, String title, String genre, int releaseYear, Album album) {
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = new ArrayList<>();
        this.album = album;
    }

    public void addArtist(Artist artist) {
        performers.add(artist);
    }
}