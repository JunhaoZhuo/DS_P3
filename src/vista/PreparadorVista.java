package vista;

import model.Joc;
import java.util.List;
import java.util.stream.Collectors;

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


}