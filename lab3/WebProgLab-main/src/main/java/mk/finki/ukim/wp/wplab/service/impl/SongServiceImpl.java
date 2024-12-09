package mk.finki.ukim.wp.wplab.service.impl;

import mk.finki.ukim.wp.wplab.model.Album;
import mk.finki.ukim.wp.wplab.model.Artist;
import mk.finki.ukim.wp.wplab.model.Song;
import mk.finki.ukim.wp.wplab.repository.AlbumRepository;
import mk.finki.ukim.wp.wplab.repository.SongRepository;
import mk.finki.ukim.wp.wplab.service.SongService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    final SongRepository songRepository;
    final AlbumRepository albumRepository;

    public SongServiceImpl(SongRepository songRepository, AlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

//    @Transactional
//    public Song addArtistToSong(Artist artist, Song song) {
//        song.addArtist(artist);  // Assuming Song has a ManyToOne relationship with Artist
//        return songRepository.save(song);  // Save the updated song with artist
//    }


    @Override
    public Song findByTrackId(String trackId) {
        return songRepository.findByTrackId(trackId);
    }

    @Override
    public void save(String title, String genre, int releaseYear, String trackId, Long albumId) {
        Song song = new Song(trackId, title, genre, releaseYear, albumRepository.findById(albumId).get());
        songRepository.save(song);
    }

    @Override
    public Song findById(Long songId) {
        return songRepository.findById(songId).get();
    }

    @Override
    public void editSong(Long songId, String trackId, String title, String genre, int releaseYear, Long albumId) {
        Optional<Song> optionalSong = songRepository.findById(songId);
        if (optionalSong.isPresent()) {
            Song song = optionalSong.get();
            song.setTrackId(trackId);
            song.setTitle(title);
            song.setGenre(genre);
            song.setReleaseYear(releaseYear);
            Album album = albumRepository.findById(albumId).orElseThrow(() -> new RuntimeException("Album not found"));
            song.setAlbum(album);
            songRepository.save(song);
        } else {
            throw new RuntimeException("Song not found");
        }
    }

    @Override
    public void deleteSong(Long songId) {
        songRepository.deleteById(songId);

    }
}
