package mk.finki.ukim.wp.wplab.repository;

import mk.finki.ukim.wp.wplab.model.Artist;
import mk.finki.ukim.wp.wplab.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
 List<Song> findAllByAlbum_Id(Long albumId);

 List<Song> findAll();

 Song findByTrackId(String trackId);

// Artist addArtistToSong(Artist artist, Song song);
 void deleteById(Long songId);
}
