package vista;

import model.Joc;
import java.util.List;
import java.util.stream.Collectors;

public class FormatadorVista {

    /**
     * Formata la llista de jocs del catàleg (US4)
     */
    public static String formatarLlistaJocsCataleg(List<Joc> jocs) {
        List<String> titols = jocs.stream()
                .map(Joc::getTitol)
                .collect(Collectors.toList());
        return "Llista de jocs del catàleg:\n" + String.join("\n", titols);
    }

    // Aquí aniran els altres mètodes de formatació (per US5 i US6)
}