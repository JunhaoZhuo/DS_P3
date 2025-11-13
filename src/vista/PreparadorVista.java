package vista;

import model.Joc;
import java.util.List;
import java.util.stream.Collectors;
import model.Adquisicio;


public class PreparadorVista {

    /**
     * Formata la llista de jocs del catàleg (US4)
     */
    public static String prepararLlistaJocsCataleg(List<Joc> jocs) {
        List<String> titols = jocs.stream()
                .map(Joc::getTitol)
                .collect(Collectors.toList());
        return "Llista de jocs del catàleg:\n" + String.join("\n", titols);
    }
    /**
     * Prepara el text per a la llista de jocs adquirits (US5)
     */
    public static String prepararLlistaAdquisicions(List<Adquisicio> adquisicions) {
        List<String> titols = adquisicions.stream()
                .map(Adquisicio::getTitolJoc)
                .collect(Collectors.toList());
        return "Llista de jocs adquirits per l'usuari:\n" + String.join("\n", titols);
    }


}