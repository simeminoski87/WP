package mk.finki.ukim.wp.wplab.service;

import mk.finki.ukim.wp.wplab.model.Album;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AlbumService {

    List<Album> findAll();

    Optional<Album> findById(Long albumId);

    Album save(String title, String genre, String releaseYear);

    void delete(Long albumId);

    void edit(Long albumId, String title, String genre, String releaseYear);
}
