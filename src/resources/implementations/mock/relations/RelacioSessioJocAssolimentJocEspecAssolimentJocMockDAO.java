package resources.implementations.mock.relations;

import resources.interfaces.relations.RelacioSessioJocAssolimentJocEspecAssolimentJocDAO;
import utils.tuples.Sextet;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RelacioSessioJocAssolimentJocEspecAssolimentJocMockDAO extends RelacioMockDAO<Sextet<String, String, LocalDate, LocalDateTime, String, LocalDateTime>> implements RelacioSessioJocAssolimentJocEspecAssolimentJocDAO {
    // TODO: comprovar que cap sessió sigui anterior a la data d'adquisició del joc
    public RelacioSessioJocAssolimentJocEspecAssolimentJocMockDAO() {
        // ajaleo@gmail.com
        addRelacio("ajaleo@gmail.com", "Elden Ring: Shadow of the Erdtree", "22-06-2024", "23-06-2024 18:00", "Roundtable Hold", "23-06-2024 18:10"); // repeated session

        // marta.soler@example.com
        addRelacio("marta.soler@example.com", "Baldur's Gate 3", "15-08-2023", "16-08-2023 17:45", "Descent From Avernus", "16-08-2023 18:04");
        addRelacio("marta.soler@example.com", "Cyberpunk 2077", "17-09-2022", "18-09-2022 19:00", "The Gig", "18-09-2022 21:11");

        // lucia_perez@example.com
        addRelacio("lucia_perez@example.com", "Elden Ring: Shadow of the Erdtree", "25-06-2024", "26-06-2024 18:10", "Roundtable Hold", "26-06-2024 18:21");
        addRelacio("lucia_perez@example.com", "Elden Ring: Shadow of the Erdtree", "25-06-2024", "27-06-2024 20:15", "Margit, the Fell Omen", "28-06-2024 08:58"); // repeated session
        addRelacio("lucia_perez@example.com", "LawBreakers", "15-09-2017", "17-09-2017 14:30", "My Turf", "17-09-2017 15:15"); // repeated session

        // david.ros@example.net
        addRelacio("david.ros@example.net", "Cyberpunk 2077", "10-12-2021", "11-12-2021 19:00", "The Gig", "11-12-2021 22:00");
        addRelacio("david.ros@example.net", "Cyberpunk 2077", "10-12-2021", "11-12-2021 23:30", "The Heist", "12-12-2021 00:59"); // repeated session
        addRelacio("david.ros@example.net", "Baldur's Gate 3", "05-09-2023", "06-09-2023 20:30", "Fists of Fury", "06-09-2023 20:47");
        addRelacio("david.ros@example.net", "Baldur's Gate 3", "05-09-2023", "07-09-2023 18:00", "Under Lock and Key", "07-09-2023 21:10"); // repeated session
    }

    private void addRelacio(String emailUsuari, String titolJoc, String dataAdquisicio, String dataIniciSessioJoc, String titolAssoliment, String dataHoraAssoliment) {
        DateTimeFormatter dateOnlyFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        try {
            relacions.add(
                    new Sextet<>(
                            emailUsuari,
                            titolJoc,
                            LocalDate.parse(dataAdquisicio, dateOnlyFormatter),
                            LocalDateTime.parse(dataIniciSessioJoc, dateTimeFormatter),
                            titolAssoliment,
                            LocalDateTime.parse(dataHoraAssoliment, dateTimeFormatter)
                    )
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
