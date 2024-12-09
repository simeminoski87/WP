package mk.finki.ukim.wp.wplab.service.impl;

import mk.finki.ukim.wp.wplab.model.Album;
import mk.finki.ukim.wp.wplab.repository.AlbumRepository;
import mk.finki.ukim.wp.wplab.service.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    @Override
    public Optional<Album> findById(Long albumId) {
        return albumRepository.findById(albumId);
    }

    @Override
    public Album save(String title, String genre, String releaseYear) {
        Album album = new Album(title, genre, releaseYear);
        return albumRepository.save(album);
    }

    @Override
    public void delete(Long albumId) {
        albumRepository.deleteById(albumId);
    }

    @Override
    public void edit(Long albumId, String title, String genre, String releaseYear) {
        Optional<Album> optionalAlbum = albumRepository.findById(albumId);
        if (optionalAlbum.isPresent()) {
            Album album = optionalAlbum.get();
            album.setName(title);
            album.setGenre(genre);
            album.setReleaseYear(releaseYear);
            albumRepository.save(album);
        } else {
            throw new RuntimeException("Album not found");
        }
    }
}
