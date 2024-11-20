package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.model.exceptions.AlbumNotFoundException;
import mk.finki.ukim.mk.lab.repository.AlbumRepository;
import mk.finki.ukim.mk.lab.repository.SongRepository;
import mk.finki.ukim.mk.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;

    public SongServiceImpl(SongRepository songRepository, AlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Song> listSongs() {
        return this.songRepository.findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        return this.songRepository.addArtistToSong(artist,song);
    }

    @Override
    public Optional<Song> findByTrackId(String trackId) {
        return this.songRepository.findByTrackId(trackId);
    }

    @Override
    public Optional<Song> findById(Long id) {
        return this.songRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        this.songRepository.deleteById(id);
    }

    @Override
        public Optional<Song> saveSong(String trackId, String title, String genre, Integer releaseYear, Long albumId, Long id) {
            Album album=albumRepository.findById(albumId).orElseThrow(()->new AlbumNotFoundException(albumId));
            return this.songRepository.saveSong(trackId,title,genre,releaseYear,album,id);

    }
}
