package resources.implementations.mock.entities;

import model.EspecAssolimentJoc;
import resources.interfaces.entities.EspecAssolimentDAO;
import java.util.*;

public class EspecAssolimentMockDAO implements EspecAssolimentDAO {

    // Ara guardem només per títol de l'assoliment.
    // Nota: Si dos jocs tinguessin un assoliment amb el mateix nom, caldria un ID més complex,
    // però per la pràctica assumim títols únics o gestionats per context.
    private final Map<String, EspecAssolimentJoc> specAssoliments;

    public EspecAssolimentMockDAO() {
        specAssoliments = new HashMap<>();

        // Només definim l'assoliment i el % (totes a 0.0 segons l'original)
        addSpecAssoliment("Roundtable Hold", 0.0);
        addSpecAssoliment("Margit, the Fell Omen", 0.0);
        addSpecAssoliment("Legendary Talismans", 0.0);
        addSpecAssoliment("Descent From Avernus", 0.0);
        addSpecAssoliment("Fists of Fury", 0.0);
        addSpecAssoliment("Under Lock and Key", 0.0);
        addSpecAssoliment("The Gig", 0.0);
        addSpecAssoliment("The Heist", 0.0);
        addSpecAssoliment("The Pickup", 0.0);
        addSpecAssoliment("Newbie Pal Tamer", 0.0);
        addSpecAssoliment("Twilight Siren", 0.0);
        addSpecAssoliment("Predator Hunter", 0.0);
        addSpecAssoliment("Sweet Victory!", 0.0);
        addSpecAssoliment("Supporter", 0.0);
        addSpecAssoliment("My Turf", 0.0);
        addSpecAssoliment("Kickin' It", 0.0);
        addSpecAssoliment("Hacking Time", 0.0);
    }

    @Override
    public Optional<EspecAssolimentJoc> getById(String[] id) throws Exception {
        String titol = Objects.requireNonNull(id[0], "El títol no pot ser null");
        return Optional.ofNullable(specAssoliments.get(titol));
    }

    @Override
    public List<EspecAssolimentJoc> getAll() {
        return new ArrayList<>(specAssoliments.values());
    }

    @Override
    public boolean add(EspecAssolimentJoc especAssolimentJoc) throws Exception {
        if (specAssoliments.containsKey(especAssolimentJoc.getTitol())) {
            return false;
        }
        specAssoliments.put(especAssolimentJoc.getTitol(), especAssolimentJoc);
        return true;
    }

    @Override
    public boolean delete(EspecAssolimentJoc especAssolimentJoc) throws Exception {
        return specAssoliments.remove(especAssolimentJoc.getTitol()) != null;
    }

    @Override
    public boolean update(EspecAssolimentJoc especAssolimentJoc) throws Exception {
        if (!specAssoliments.containsKey(especAssolimentJoc.getTitol())) {
            return false;
        }
        specAssoliments.put(especAssolimentJoc.getTitol(), especAssolimentJoc);
        return true;
    }

    private void addSpecAssoliment(String titolAssoliment, Double percentatge) {
        specAssoliments.put(titolAssoliment, new EspecAssolimentJoc(titolAssoliment, percentatge));
    }
}