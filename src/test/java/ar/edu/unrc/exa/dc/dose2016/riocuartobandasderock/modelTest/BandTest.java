package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.modelTest;

import org.junit.Test;
import static org.junit.Assert.*;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;

import java.util.LinkedList;
import java.util.UUID;

/**
 * Title:   riocuartobandasderock.test.Band<p>
 * Description: class which defines a suit test for band model.<p>
 * @author Ezequiel Depetris&Gaston Massimino
 */
public class BandTest {

  /**
   * in this test we set an id, and then we check
   * that match with the id created
   */
  @Test
  public void setIdTest(){
    String an_id = "1";
    Band band = new Band();
    band.setId(an_id);
    assertEquals(band.getId(),an_id);
  }

  /**
   * in this test we set a name, and then we check
   * that match with the name created
   */
  @Test
  public void setNameTest(){
    Band band = new Band();
    String name = "led zeppeling";
    band.setName(name);
    assertEquals(band.getName(),name);
  }



}
