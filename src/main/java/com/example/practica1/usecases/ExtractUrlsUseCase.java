package com.example.practica1.usecases;

import com.example.practica1.model.Pelicula;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExtractUrlsUseCase {
    private static final String URL_BASE = "https://pelisplus.so";


    public void verificacion(){
        urls().forEach(link ->{
            var datosPelicula = extracPeliculas(link);
            System.out.println(datosPelicula.getTitulo());
            datosPelicula.getUrl().forEach(System.out::println);
            System.out.println("*********************************************************");
            System.out.println(" ");
        });
    }

    public List<String> urls(){
        List<String> urls = new ArrayList<>();
        try{
            var documento = Jsoup.connect(URL_BASE)
                    .userAgent("Mozilla/5.0")
                    .get()
                    .getElementById("owl-demo-premiere-movies");
            var etiquetas = documento.select("a");
            etiquetas.forEach(etiqueta -> urls.add(URL_BASE +  etiqueta.attr("href")));
        } catch (IOException e){
            e.getStackTrace();
        }
        return urls;
    }

    public Pelicula extracPeliculas(String url){
        var pelicula = new Pelicula();

        List<String> linksVideos = new ArrayList<>();
        try{
            var documento = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0")
                    .get();
            //DATOS PELICULA
            var datosPelicula = documento.getElementsByClass("pi-right").select("p");
            pelicula.setTitulo(documento.getElementsByClass("pi-right").select("h1").text());
            pelicula.setTituloOriginal(datosPelicula.get(0).text());
            pelicula.setYear(datosPelicula.get(1).text());
            pelicula.setGender(datosPelicula.get(3).text());
            pelicula.setSynopsis(datosPelicula.get(4).text());

            //LINK PELICULA
            var linkPeliula = documento.getElementById("level2_castell");
            if(linkPeliula==null){
                linkPeliula=documento.getElementById("level2_subtitulado");
            }
            linkPeliula.select("li").forEach(urlVideo->{
                linksVideos.add(urlVideo.attr("data-video"));
            });
            pelicula.setUrl(linksVideos);
        } catch (IOException e){
            e.getStackTrace();
        }
        return pelicula;
    }

}
