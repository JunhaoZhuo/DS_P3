package resources.implementations.mock.entities;

import model.Joc;
import resources.interfaces.entities.JocDAO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class JocMockDAO implements JocDAO {

    private final Map<String, Joc> jocs;
    private final DateTimeFormatter formatter;

    public JocMockDAO() {
        jocs = new HashMap<>();
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        addJocAnunciat(
                "Grand Theft Auto VI",
                "Acció-Aventura",
                "Rockstar Games",
                "Rockstar Games",
                "05-12-2023" // announcement trailer
        );

        addJocAnunciat(
                "Death Stranding 2",
                "Acció-Aventura",
                "Kojima Productions",
                "Sony Interactive Entertainment",
                "08-12-2022" // announced at The Game Awards
        );

        // --- Jocs Disponibles (top sellers now) ---
        addJocDisponible(
                "Elden Ring: Shadow of the Erdtree",
                "RPG",
                "FromSoftware",
                "Bandai Namco Entertainment",
                "21-06-2024", // launch expansion
                "23-02-2022"  // original base game announcement
        );

        addJocDisponible(
                "Baldur's Gate 3",
                "RPG",
                "Larian Studios",
                "Larian Studios",
                "03-08-2023", // PC launch
                "06-06-2019"  // announced at Google Stadia Connect
        );

        addJocDisponible(
                "Cyberpunk 2077",
                "RPG",
                "CD Projekt Red",
                "CD Projekt",
                "10-12-2020", // launch
                "30-05-2012"  // announcement
        );

        addJocDisponible(
                "Palworld",
                "Survival / Aventura",
                "Pocket Pair",
                "Pocket Pair",
                "19-01-2024",
                "05-06-2021"
        );

        // --- Jocs Retirats ---
        addJocRetirat(
                "Paragon",
                "MOBA",
                "Epic Games",
                "Epic Games",
                "18-03-2016", // Early Access
                "01-11-2015", // Announcement
                "26-04-2018"  // Shutdown
        );

        addJocRetirat(
                "LawBreakers",
                "FPS",
                "Boss Key Productions",
                "Nexon",
                "08-08-2017",
                "20-08-2015",
                "14-09-2018"
        );
    }

    public Optional<Joc> getById(String[] id) throws Exception {
        String titol = Objects.requireNonNull(id[0], "El títol no pot ser null");
        return Optional.ofNullable(jocs.get(titol));
    }

    @Override
    public List<Joc> getAll() throws Exception {
        return new ArrayList<>(jocs.values());
    }

    @Override
    public boolean add(Joc joc) throws Exception {
        if (getById(new String[]{joc.getTitol()}).isPresent()) {
            return false;
        }
        jocs.put(joc.getTitol(), joc);
        return true;
    }

    @Override
    public boolean delete(Joc joc) throws Exception {
        return jocs.remove(joc.getTitol()) != null;
    }

    public boolean update(Joc joc) throws Exception {
        if (!getById(new String[]{joc.getTitol()}).isPresent()) {
            return false;
        }
        jocs.put(joc.getTitol(), joc);
        return true;
    }

    private void addJocAnunciat(
            String titol,
            String genere,
            String desenvolupadora,
            String distribuidora,
            String dataAnunci
    ) {
        Joc joc = Joc.crearJocAnunciat(
                titol,
                List.of(genere.split(", ")),
                List.of(desenvolupadora.split(", ")),
                List.of(distribuidora.split(", ")),
                LocalDate.parse(dataAnunci, formatter)
        );
        jocs.put(joc.getTitol(), joc);
    }

    private void addJocDisponible(
            String titol,
            String genere,
            String desenvolupadora,
            String distribuidora,
            String dataLlancement,
            String dataAnunci
    ) {
        Joc joc = Joc.crearJocDisponible(
                titol,
                List.of(genere.split(", ")),
                List.of(desenvolupadora.split(", ")),
                List.of(distribuidora.split(", ")),
                LocalDate.parse(dataLlancement, formatter),
                LocalDate.parse(dataAnunci, formatter)
        );
        jocs.put(joc.getTitol(), joc);
    }

    private void addJocRetirat(
            String titol,
            String genere,
            String desenvolupadora,
            String distribuidora,
            String dataLlancement,
            String dataAnunci,
            String dataRetirada
    ) {
        Joc joc = Joc.crearJocRetirat(
                titol,
                List.of(genere.split(", ")),
                List.of(desenvolupadora.split(", ")),
                List.of(distribuidora.split(", ")),
                LocalDate.parse(dataLlancement, formatter),
                LocalDate.parse(dataAnunci, formatter),
                LocalDate.parse(dataRetirada, formatter)
        );
        jocs.put(joc.getTitol(), joc);
    }
}
