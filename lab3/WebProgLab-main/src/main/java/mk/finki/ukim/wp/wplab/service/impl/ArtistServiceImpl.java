package mk.finki.ukim.wp.wplab.service.impl;

import mk.finki.ukim.wp.wplab.model.Artist;
import mk.finki.ukim.wp.wplab.repository.ArtistRepository;
import mk.finki.ukim.wp.wplab.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> listArtists() {
        return artistRepository.findAll();
    }

    @Override
    public Artist findById(Long id) {
        return artistRepository.findById(id);
    }
}
