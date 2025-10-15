package model;

import model.excepcions.EmptyCatalegException;
import model.excepcions.JocNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class CatalegJocs {
    private List<Joc> jocs;

    public CatalegJocs() {
        this.jocs = new ArrayList<>();
    }
    
    public void setJocs(List<Joc> jocs) {
        this.jocs = jocs;
    }

    public Joc findByTitol(String titol) throws JocNotFoundException {
        for (Joc joc : jocs) {
            if (joc.getTitol().equals(titol)) {
                return joc;
            }
        }
        throw new JocNotFoundException();
    }

    public EspecAssolimentJoc findEspecAssolimentByTitol(String titolAssoliment) {
        for (Joc joc : jocs) {
            for (EspecAssolimentJoc ea : joc.getEspecAssoliments()) {
                if (ea.getTitol().equals(titolAssoliment)) {
                    return ea;
                }
            }
        }
        return null;
    }

    public List<Joc> getJocsOrdenatsPerNom() throws Exception {
        if (jocs.isEmpty()) {
            throw new EmptyCatalegException();
        }

        return jocs.stream()
                .sorted((joc1, joc2) -> joc1.getTitol().compareToIgnoreCase(joc2.getTitol()))
                .toList();
    }
}
