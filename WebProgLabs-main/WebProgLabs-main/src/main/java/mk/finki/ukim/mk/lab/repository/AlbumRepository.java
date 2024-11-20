package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.Album;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AlbumRepository {
    List<Album> albums=new ArrayList<>();
    @PostConstruct
    public void init(){
        albums.add(new Album(1L,"Use Your Illusions I","Rokc","1993"));
        albums.add(new Album(2L,"Use Your Illusions II","Rokc","1992"));
        albums.add(new Album(3L,"Apepite For Destruction","Rokc","1992"));
        albums.add(new Album(4L,"Back in Black","Rokc3","1992"));
        albums.add(new Album(5L,"Highway to Hell","Rokc3","1992"));
    }
    public List<Album> findAll(){
        return albums;
    }
    public Optional<Album> findById(Long id){
        return albums.stream().filter(a->a.getId().equals(id)).findFirst();
    }
}
