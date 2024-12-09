package mk.finki.ukim.wp.wplab.repository;

import mk.finki.ukim.wp.wplab.bootstrap.DataHolder;
import mk.finki.ukim.wp.wplab.model.Artist;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ArtistRepository {

//    public final List<Artist> artists = new ArrayList<>();

    public ArtistRepository() {
//        artists.add(new Artist(1L, "Kanye", "West", "GOAT"));
//        artists.add(new Artist(2L, "Kendrick", "Lamar", "Pulitzer prize winner"));
//        artists.add(new Artist(3L, "Metro", "Boomin", "Prod god"));
//        artists.add(new Artist(4L, "Jermaine", "Cole", "Izguben"));
//        artists.add(new Artist(5L, "MetalFingers", "DOOM", "Rip"));
    }

    public List<Artist> findAll()
    {
        return new ArrayList<>(DataHolder.artists);
    }

    public Artist findById(Long id)
    {
        return DataHolder.artists.stream().filter(x-> x.getId().equals(id)).findFirst().get();
    }
}
