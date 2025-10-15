package resources.implementations.mock.relations;

import resources.interfaces.relations.RelacioUsuariAdquisicioJocDAO;
import utils.tuples.Quintet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// TODO: El valor del preu a Double? Seria millor BigDecimal però embolica molt la troca
public class RelacioUsuariAdquisicioJocMockDAO extends RelacioMockDAO<Quintet<String, String, LocalDate, Double, String>> implements RelacioUsuariAdquisicioJocDAO {

    public RelacioUsuariAdquisicioJocMockDAO() {
        addRelacio("ajaleo@gmail.com", "Elden Ring: Shadow of the Erdtree", "22-06-2024", "39.99", "EUR");
        addRelacio("ajaleo@gmail.com", "Grand Theft Auto VI", "10-12-2023", "69.99", "EUR"); // preorder
        addRelacio("ajaleo@gmail.com", "Paragon", "20-04-2016", "0.00", "EUR"); // free-to-play past acquisition

        addRelacio("marta.soler@example.com", "Baldur's Gate 3", "15-08-2023", "59.99", "EUR");
        addRelacio("marta.soler@example.com", "Cyberpunk 2077", "17-09-2022", "49.99", "EUR");

        addRelacio("joan93@example.org", "Palworld", "25-01-2024", "29.99", "USD");
        addRelacio("joan93@example.org", "Death Stranding 2", "10-12-2022", "69.99", "USD"); // preorder

        addRelacio("lucia_perez@example.com", "Elden Ring: Shadow of the Erdtree", "25-06-2024", "39.99", "EUR");
        addRelacio("lucia_perez@example.com", "LawBreakers", "15-09-2017", "29.99", "EUR"); // retired game

        addRelacio("david.ros@example.net", "Cyberpunk 2077", "10-12-2021", "69.99", "USD");
        addRelacio("david.ros@example.net", "Baldur's Gate 3", "05-09-2023", "59.99", "USD");
    }

    private void addRelacio(String email, String titolJoc, String dataAdquisicio, String preu, String moneda) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            relacions.add(
                    new Quintet<>(
                            email,
                            titolJoc,
                            LocalDate.parse(dataAdquisicio, formatter),
                            Double.parseDouble(preu),
                            moneda
                    )
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
