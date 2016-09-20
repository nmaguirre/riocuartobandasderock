package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.modelTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Genre;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.TGenre;

public class GenereTest {
  
  /**
   * test that getName method it works ok
   * @see Genere.getName()
   */
  @Test
  public void setNameTest(){
    Genre genere = new Genre();
    TGenre name = TGenre.pop;
    genere.setName(name);
    assertEquals(genere.getName(),name);  
  }
  
  /**
   * test that getId method it works ok
   * @see Genere.getId()
   */
  @Test
  public void setIdTest(){
    Genre genre = new Genre();
    String id = "1";
    genre.setId(id);
    assertEquals(genre.getId(),id);  
  }
  
  /**
   * test that getDescription method it works ok
   * @see Genere.getDescription()
   */
  @Test
  public void setDescriptionTest(){
    Genre genere = new Genre();
    String description;
    description = "La música pop es un género de música popular que tuvo su origen a finales de los años 1950 como una derivación del rock and roll,";
    description += " ;en combinación con otros géneros musicales que estaban en moda en aquel momento";
    genere.setDescription(description);
    assertEquals(genere.getDescription(),description);  
  }
  
  /**
   * test that getRelease method it works ok
   * @see Genere.getRelease()
   */
  @Test
  public void setReleaseTest(){
    Genre genere = new Genre();
    String release = "24/08/1992";
    genere.setRelease(release);
    assertEquals(genere.getRelease(),release);  
  }
  
  /**
   * test that getInitiators it works ok
   * @see Genere.getInitiators()
   */
  @Test
  public void initiatedByTest(){
    Genre genere = new Genre();
    ArrayList<String> authors = new ArrayList<String>();
    authors.add("Ezequiel depetris");
    authors.add("Elver Galarga");
    genere.setInitiatedBy(authors);
    assertEquals(genere.getInitiators() ,authors);  
  }
  
  /**
   * test that getInfluence it works ok
   * @see Genere.getInfluence()
   */
  @Test
  public void influencesTest(){
    Genre genre = new Genre();
    TGenre gen = TGenre.pop;
    TGenre gen1 = TGenre.rock;
    ArrayList<Genre> generes = new ArrayList<Genre>();
    generes.add(new Genre(gen, "esto es rock vieja"));
    generes.add(new Genre(gen1, "huevo poche"));
    genre.setInfluence(generes);
    assertEquals(genre.getInfluence() ,generes);  
  }
}
