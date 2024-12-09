package mk.finki.ukim.wp.wplab.repository;

import mk.finki.ukim.wp.wplab.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findAll();
}
