package resources.implementations.mock.entities;

import model.EspecAssolimentJoc;
import resources.interfaces.entities.EspecAssolimentDAO;
import utils.tuples.Parell;

import java.util.*;

public class EspecAssolimentMockDAO implements EspecAssolimentDAO {

    private final Map<Parell<String, String>, EspecAssolimentJoc> specAssoliments;

    public EspecAssolimentMockDAO() {
        specAssoliments = new HashMap<>();

        addSpecAssoliment("Elden Ring: Shadow of the Erdtree", "Roundtable Hold", 0.0);
        addSpecAssoliment("Elden Ring: Shadow of the Erdtree", "Margit, the Fell Omen", 0.0);
        addSpecAssoliment("Elden Ring: Shadow of the Erdtree", "Legendary Talismans", 0.0);
        addSpecAssoliment("Baldur's Gate 3", "Descent From Avernus", 0.0);
        addSpecAssoliment("Baldur's Gate 3", "Fists of Fury", 0.0);
        addSpecAssoliment("Baldur's Gate 3", "Under Lock and Key", 0.0);
        addSpecAssoliment("Cyberpunk 2077", "The Gig", 0.0);
        addSpecAssoliment("Cyberpunk 2077", "The Heist", 0.0);
        addSpecAssoliment("Cyberpunk 2077", "The Pickup", 0.0);
        addSpecAssoliment("Palworld", "Newbie Pal Tamer", 0.0);
        addSpecAssoliment("Palworld", "Twilight Siren", 0.0);
        addSpecAssoliment("Palworld", "Predator Hunter", 0.0);
        addSpecAssoliment("Paragon", "Sweet Victory!", 0.0);
        addSpecAssoliment("Paragon", "Supporter", 0.0);
        addSpecAssoliment("LawBreakers", "My Turf", 0.0);
        addSpecAssoliment("LawBreakers", "Kickin' It", 0.0);
        addSpecAssoliment("LawBreakers", "Hacking Time", 0.0);
    }

    public Optional<EspecAssolimentJoc> getById(String[] id) throws Exception {
        String titol = Objects.requireNonNull(id[0], "El títol no pot ser null");
        String titolJoc = Objects.requireNonNull(id[1], "El títol del joc no pot ser null");
        return Optional.ofNullable(specAssoliments.get(new Parell<>(titol, titolJoc)));
    }

    @Override
    public List<EspecAssolimentJoc> getAll() {
        return new ArrayList<>(specAssoliments.values());
    }

    @Override
    public boolean add(EspecAssolimentJoc especAssolimentJoc) throws Exception {
        if (getById(new String[]{especAssolimentJoc.getTitol(), especAssolimentJoc.getTitolJoc()}).isPresent()) {
            return false;
        }
        specAssoliments.put(new Parell<>(especAssolimentJoc.getTitol(), especAssolimentJoc.getTitolJoc()), especAssolimentJoc);
        return true;
    }

    @Override
    public boolean delete(EspecAssolimentJoc especAssolimentJoc) throws Exception {
        return specAssoliments.remove(new Parell<>(especAssolimentJoc.getTitol(), especAssolimentJoc.getTitolJoc())) != null;
    }

    public boolean update(EspecAssolimentJoc especAssolimentJoc) throws Exception {
        if (!getById(new String[]{especAssolimentJoc.getTitol(), especAssolimentJoc.getTitolJoc()}).isPresent()) {
            return false;
        }
        specAssoliments.put(new Parell<>(especAssolimentJoc.getTitol(), especAssolimentJoc.getTitolJoc()), especAssolimentJoc);
        return true;
    }

    private void addSpecAssoliment(
            String titolJoc,
            String titolAssoliment,
            Double percentatgeJugadorsCompletat
    ) {
        specAssoliments.put(new Parell<>(titolJoc, titolAssoliment), new EspecAssolimentJoc(titolAssoliment, titolJoc, percentatgeJugadorsCompletat));
    }
}
