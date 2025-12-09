package resources.implementations.mock.relations;

import resources.interfaces.relations.RelacioJocEspecAssolimentDAO;
import utils.tuples.Parell;

public class RelacioJocEspecAssolimentMockDAO extends RelacioMockDAO<Parell<String, String>> implements RelacioJocEspecAssolimentDAO {

    public RelacioJocEspecAssolimentMockDAO() {
        // Aquí definim quins assoliments pertanyen a quins jocs
        // Format: Parell(TitolJoc, TitolAssoliment)

        // Elden Ring
        addRelacio("Elden Ring: Shadow of the Erdtree", "Roundtable Hold");
        addRelacio("Elden Ring: Shadow of the Erdtree", "Margit, the Fell Omen");
        addRelacio("Elden Ring: Shadow of the Erdtree", "Legendary Talismans");

        // Baldur's Gate 3
        addRelacio("Baldur's Gate 3", "Descent From Avernus");
        addRelacio("Baldur's Gate 3", "Fists of Fury");
        addRelacio("Baldur's Gate 3", "Under Lock and Key");

        // Cyberpunk 2077
        addRelacio("Cyberpunk 2077", "The Gig");
        addRelacio("Cyberpunk 2077", "The Heist");
        addRelacio("Cyberpunk 2077", "The Pickup");

        // Palworld
        addRelacio("Palworld", "Newbie Pal Tamer");
        addRelacio("Palworld", "Twilight Siren");
        addRelacio("Palworld", "Predator Hunter");

        // Paragon
        addRelacio("Paragon", "Sweet Victory!");
        addRelacio("Paragon", "Supporter");

        // LawBreakers
        addRelacio("LawBreakers", "My Turf");
        addRelacio("LawBreakers", "Kickin' It");
        addRelacio("LawBreakers", "Hacking Time");
    }

    private void addRelacio(String titolJoc, String titolAssoliment) {
        try {
            relacions.add(new Parell<>(titolJoc, titolAssoliment));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}