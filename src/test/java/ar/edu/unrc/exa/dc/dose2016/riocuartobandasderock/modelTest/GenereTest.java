package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.modelTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Genere;

public class GenereTest {
  
  /**
   * test that getName method it works ok
   * @see Genere.getName()
   */
  @Test
  public void setNameTest(){
    Genere genere = new Genere();
    String name = "pop";
    genere.setName(name);
    assertEquals(genere.getName(),name);  
  }
  
  /**
   * test that getId method it works ok
   * @see Genere.getId()
   */
  @Test
  public void setIdTest(){
    Genere genere = new Genere();
    String id = "1";
    genere.setId(id);
    assertEquals(genere.getId(),id);  
  }
  
  /**
   * test that getDescription method it works ok
   * @see Genere.getDescription()
   */
  @Test
  public void setDescriptionTest(){
    Genere genere = new Genere();
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
    Genere genere = new Genere();
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
    Genere genere = new Genere();
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
    Genere genere = new Genere();
    ArrayList<Genere> generes = new ArrayList<Genere>();
    generes.add(new Genere("rock", "esto es rock vieja"));
    generes.add(new Genere("cha cha", "huevo poche"));
    genere.setInfluence(generes);
    assertEquals(genere.getInfluence() ,generes);  
  }
}
