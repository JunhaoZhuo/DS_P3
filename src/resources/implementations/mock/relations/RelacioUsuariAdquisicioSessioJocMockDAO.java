package resources.implementations.mock.relations;

import resources.interfaces.relations.RelacioUsuariAdquisicioSessioJocDAO;
import utils.tuples.Sextet;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RelacioUsuariAdquisicioSessioJocMockDAO extends RelacioMockDAO<Sextet<String, String, LocalDate, LocalDateTime, LocalDateTime, Integer>> implements RelacioUsuariAdquisicioSessioJocDAO {
    // TODO: comprovar que cap sessió sigui anterior a la data d'adquisició del joc
    public RelacioUsuariAdquisicioSessioJocMockDAO() {
        // ajaleo@gmail.com
        addRelacio("ajaleo@gmail.com", "Elden Ring: Shadow of the Erdtree", "22-06-2024", "22-06-2024 19:30", "22-06-2024 22:15", 2);
        addRelacio("ajaleo@gmail.com", "Elden Ring: Shadow of the Erdtree", "22-06-2024", "23-06-2024 18:00", "23-06-2024 21:20", 2); // repeated session
        addRelacio("ajaleo@gmail.com", "Paragon", "20-04-2016", "21-04-2016 18:00", "21-04-2016 20:10", 2);

        // marta.soler@example.com
        addRelacio("marta.soler@example.com", "Baldur's Gate 3", "15-08-2023", "16-08-2023 17:45", "16-08-2023 21:30", 2);
        addRelacio("marta.soler@example.com", "Baldur's Gate 3", "15-08-2023", "17-08-2023 20:10", "17-08-2023 23:00", 2); // repeated session
        addRelacio("marta.soler@example.com", "Cyberpunk 2077", "17-09-2022", "18-09-2022 19:00", "18-09-2022 22:10", 2);

        // joan93@example.org
        addRelacio("joan93@example.org", "Palworld", "25-01-2024", "26-01-2024 16:20", "26-01-2024 18:40", 2);
        addRelacio("joan93@example.org", "Palworld", "25-01-2024", "27-01-2024 15:00", "27-01-2024 18:00", 2); // repeated session

        // lucia_perez@example.com
        addRelacio("lucia_perez@example.com", "Elden Ring: Shadow of the Erdtree", "25-06-2024", "26-06-2024 18:10", "26-06-2024 21:00", 2);
        addRelacio("lucia_perez@example.com", "Elden Ring: Shadow of the Erdtree", "25-06-2024", "27-06-2024 20:15", "27-06-2024 23:10", 2); // repeated session
        addRelacio("lucia_perez@example.com", "LawBreakers", "15-09-2017", "16-09-2017 15:00", "16-09-2017 17:20", 2);
        addRelacio("lucia_perez@example.com", "LawBreakers", "15-09-2017", "17-09-2017 14:30", "17-09-2017 16:00", 2); // repeated session

        // david.ros@example.net
        addRelacio("david.ros@example.net", "Cyberpunk 2077", "10-12-2021", "11-12-2021 19:00", "11-12-2021 22:00", 2);
        addRelacio("david.ros@example.net", "Cyberpunk 2077", "10-12-2021", "12-12-2021 20:30", "12-12-2021 23:45", 2); // repeated session
        addRelacio("david.ros@example.net", "Baldur's Gate 3", "05-09-2023", "06-09-2023 20:30", "06-09-2023 23:00", 2);
        addRelacio("david.ros@example.net", "Baldur's Gate 3", "05-09-2023", "07-09-2023 18:00", "07-09-2023 21:15", 2); // repeated session
    }

    private void addRelacio(String emailUsuari, String nomJoc, String dataAdquisicio, String dataIniciSessio, String dataFiSessio, Integer estatSessio) {
        DateTimeFormatter dateOnlyFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        try {
            relacions.add(
                    new Sextet<>(
                            emailUsuari,
                            nomJoc,
                            LocalDate.parse(dataAdquisicio, dateOnlyFormatter),
                            LocalDateTime.parse(dataIniciSessio, dateTimeFormatter),
                            LocalDateTime.parse(dataFiSessio, dateTimeFormatter),
                            estatSessio
                    )
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
