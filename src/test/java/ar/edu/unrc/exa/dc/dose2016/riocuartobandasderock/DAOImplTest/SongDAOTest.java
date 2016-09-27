
package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.DAOImplTest;

import static org.junit.Assert.*;

import mockit.Expectations;
import mockit.Mocked;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.SongDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Song;
public class SongDAOTest {
    
            @Mocked SongDAO songDao;
            
            @Test
	public void findByIdTest() {
		Song songModel = new Song();
		songModel.setId(3);
		new Expectations(){
			{
				songDao.findById(withEqual("3"));
				returns (songModel);
			}
		};
		assertEquals(songModel,songDao.findById("3"));
	}
                 
        @Test(expected=IllegalArgumentException.class)
            public void findByIdIfIdIsEmptyTest(){
		new Expectations(){
			{
				songDao.findById(" ");
			returns (new IllegalArgumentException(""));
			}
		};
	}
            
            @Test 
            public void findAllSongsTest(){
                List<Song> allSongs = new LinkedList<Song>();
                Song song1=new Song();
                Song song2=new Song();
                allSongs.add(song1);
                allSongs.add(song2);
                new Expectations() {
                                {
                                    songDao.getAllSongs();
                                    returns (allSongs);
                                }
                };
                assertEquals(allSongs, songDao.getAllSongs());
            }
    
}
