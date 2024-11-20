package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SongRepository {
    List<Song> songs;
    private final AlbumRepository albumRepository;
    public SongRepository(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @PostConstruct
    public void init() {

        songs = new ArrayList<Song>();
        List<Artist> artistList= new ArrayList<Artist>();
        List<Album> albums=new ArrayList<>();
        albums.add(new Album(1L,"Use Your Illusions I","Rokc","1993"));
        albums.add(new Album(2L,"Use Your Illusions II","Rokc","1992"));
        albums.add(new Album(3L,"Apepite For Destruction","Rokc","1992"));
        albums.add(new Album(4L,"Back in Black","Rokc3","1992"));
        albums.add(new Album(5L,"Highway to Hell","Rokc3","1992"));
        artistList.add(new Artist((long) 1, "Simeon", "Minoski", "mkd1"));
        artistList.add(new Artist((long) 2, "Nokaut", "Unplugged", "mkd2"));
        artistList.add(new Artist((long) 3, "Polat", "ALEMDAR", "Eden e"));
        artistList.add(new Artist((long) 4, "ejsidis", "*", "Edinstven i e!"));
        artistList.add(new Artist((long) 5, "OGUZ", "KOGA LISJATA PAGJAAT", "Ne znaem"));
        songs.add(new Song("1", "1000 pricini za kraj", "RoCK", 1997, artistList,albums.get(4),1L));
        songs.add(new Song("2", "Vo bestraga", "Rock", 1989, new ArrayList<>(),albums.get(1),2L));
        songs.add(new Song("3", "Ti si problem", "Pop", 2001,new ArrayList<>(),albums.get(2),3L));
        songs.add(new Song("4", "Cairska romantika", "Hip hop", 2003, artistList,albums.get(0),4L));
        songs.add(new Song("5", "Kazi nesto draga", "Black Metal", 2021,new ArrayList<>(),albums.get(1),5L));
    }

    public List<Song> findAll() {
        return songs;
    }

    public Optional<Song> findByTrackId(String trackId) {
        return songs.stream().filter(song -> song.getTrackId().equals(trackId)).findFirst();
    }

    public Artist addArtistToSong(Artist artist, Song song) {
        if(!song.getPerformers().contains(artist)) {
            song.getPerformers().add(artist);
        }
        return artist;
    }
   public Optional<Song> findById(Long id){
        return songs.stream().filter(s->s.getId().equals(id)).findFirst();
    }
    public void deleteById(Long id){
        songs.removeIf(a->a.getId().equals(id));
    }
    public Optional<Song> saveSong(String trackId, String title, String genre, Integer releaseYear, Album album, Long id){
        songs.removeIf(s->s.getId().equals(id));
        Song song=new Song(trackId,title,genre,releaseYear,new ArrayList<>(),album,id);
        songs.add(song);
        return Optional.of(song);
    }
}
