package mk.finki.ukim.wp.wplab.service;

import mk.finki.ukim.wp.wplab.model.Artist;
import mk.finki.ukim.wp.wplab.model.Song;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SongService{
    List<Song> listSongs();
//    Song addArtistToSong(Artist artist, Song song);
    Song findByTrackId(String trackId);
    void save( String title, String genre, int releaseYear, String trackId, Long albumId);
    Song findById(Long songId);
    void editSong(Long songId, String trackId, String title, String genre, int releaseYear, Long albumId);
    void deleteSong(Long songId);
}