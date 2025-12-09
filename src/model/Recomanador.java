package model;


import model.excepcions.NoRecomanacionsException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Recomanador {

    // Rep l'usuari i el catàleg, i retorna la llista de jocs recomanats
    public List<Joc> recomanarJocs(Usuari usuari, CatalegJocs cataleg) throws Exception {

        // 1. Validar que l'usuari ha jugat a alguna cosa abans
        List<Adquisicio> adquisicions = usuari.getAdquisicions();
        if (adquisicions == null || adquisicions.isEmpty()) {
            throw new NoRecomanacionsException();
        }

        // 2. Trobar tots els gèneres que l'usuari ha jugat (Preferits)
        Set<String> generesPreferits = new HashSet<>();
        for (Adquisicio adq : adquisicions) {
            generesPreferits.addAll(adq.getJoc().getGeneres());
        }

        // 3. Obtenir tots els jocs del catàleg
        List<Joc> totsElsJocs = cataleg.getJocsOrdenatsPerNom();

        // 4. Filtrar recomanacions
        List<Joc> recomanacions = new ArrayList<>();
        for (Joc joc : totsElsJocs) {
            // No recomanar jocs no disponibles o ja adquirits
            if (joc.getEstat() != EstatJoc.DISPONIBLE || usuari.teJoc(joc)) {
                continue;
            }

            // Comprovar si el joc és d'algun gènere que agrada a l'usuari
            boolean teGenerePreferit = false;
            for (String genere : joc.getGeneres()) {
                if (generesPreferits.contains(genere)) {
                    teGenerePreferit = true;
                    break;
                }
            }

            if (teGenerePreferit) {
                recomanacions.add(joc);
            }
        }

        if (recomanacions.isEmpty()) {
            throw new NoRecomanacionsException();
        }

        return recomanacions;
    }
}