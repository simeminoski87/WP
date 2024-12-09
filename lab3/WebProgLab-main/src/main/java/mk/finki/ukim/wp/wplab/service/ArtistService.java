package mk.finki.ukim.wp.wplab.service;

import mk.finki.ukim.wp.wplab.model.Artist;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ArtistService {
    List<Artist> listArtists();
    Artist findById(Long id);
}
