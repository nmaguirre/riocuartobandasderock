package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

public class Routes {

  // Routes to LAYOUTS
  public static String layout_landing_page(){
    return "views/layouts/landing_page.vm";
  }

  public static String layout_dashboard(){
    return "views/layouts/dashboard.vm";
  }

  // Routes to resource Band
  public static String edit_band(){
    return "views/bands/edit.vm";
  }

  public static String new_band(){
    return "views/bands/new.vm";
  }

  public static String index_band(){
    return "views/bands/index.vm";
  }

  public static String show_band(){
    return "views/bands/show.vm";
  }

  // Routes to resource Artists
  public static String edit_artist(){
    return "views/artists/edit.vm";
  }

  public static String new_artist(){
    return "views/artists/new.vm";
  }

  public static String index_artist(){
    return "views/artists/index.vm";
  }

  public static String show_artist(){
    return "views/artists/show.vm";
  }


  // Routes to resource Song
  public static String edit_song(){
    return "views/songs/edit.vm";
  }

  public static String new_song(){
    return "views/songs/new.vm";
  }

  public static String index_song(){
    return "views/songs/index.vm";
  }

  public static String show_song(){
    return "views/songs/show.vm";
  }


  // Routes to resource Album
  public static String edit_album(){
    return "views/albums/edit.vm";
  }

  public static String new_album(){
    return "views/albums/new.vm";
  }

  public static String index_album(){
    return "views/albums/index.vm";
  }

  public static String show_album(){
    return "views/albums/show.vm";
  }

  // Routes to Landing Page
  public static String landing_page(){
    return "views/landing_page/index.vm";
  }

  // Routes to Dashboard
  public static String dashboard_index(){
    return "views/dashboard/index.vm";
  }

}
