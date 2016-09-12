package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import static spark.Spark.*;

public class Bootstrap {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");
    }
}