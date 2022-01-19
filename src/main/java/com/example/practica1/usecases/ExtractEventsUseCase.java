package com.example.practica1.usecases;

import com.example.practica1.model.Encuentro;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExtractEventsUseCase {

    private static final String URL_BASE2 = "https://www.luckia.co/";
    private static final String URL_EVENTOS_FUTBOL = "https://www.luckia.co/apuestas/futbol/?date=sve";


    public static void verificacion() {
        try {
            urlsEncuentros().forEach((link, encuentro) -> {
                System.out.println(URL_BASE2 + link);
                System.out.println(encuentro);
                extraccionCuota1X2(URL_BASE2 + link);
            });
            System.out.println("terminamos");
        } catch (IOException e) {
            e.getStackTrace();
        }
    }


    private static Map<String, Encuentro> urlsEncuentros() throws IOException {
        Map<String, Encuentro> encuentros = new HashMap<>();
        Jsoup.connect(URL_EVENTOS_FUTBOL)
                .userAgent("Mozilla/5.0")
                .get()
                .getElementsByClass("u-flex u-flex-col-reverse md:u-flex-col u-justify-center u-p-0 md:u-pr-4 event-header-center")
                .forEach(element -> {
                    var encuentro = new Encuentro(
                            element.getElementsByClass("c-event-list__team event-header-team top").text(),
                            element.getElementsByClass("c-event-list__team event-header-team bottom").text()
                    );
                    encuentros.put(element.select("a").attr("href"), encuentro);
                });
        return encuentros;
    }


    private static void extraccionCuota1X2(String link) {
        String classTipoCuota = "u-text-dark u-block u-p-8 u-ellipsis u-text-center pick-title";
        String classValorCuota = "u-text-dark u-flex u-justify-center u-items-center u-p-8 u-bg-white pick-value";
        try {
            var contenedorCuotasDelEncuentro = Jsoup.connect(link)
                    .userAgent("Mozilla/5.0")
                    .get()
                    .getElementsByClass("offer-type")
                    .get(0);

            contenedorCuotasDelEncuentro.getElementsByClass("c-game-offers__pick pick bet-pick ")
                    .forEach(element -> {
                        var tipoCuota =  element.getElementsByClass(classTipoCuota).text();
                        var cuota = element.getElementsByClass(classValorCuota).text();
                        System.out.println(tipoCuota + "= " + cuota);
                    });

        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
