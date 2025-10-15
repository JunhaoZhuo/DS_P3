package resources.implementations.mock.entities;

import model.Usuari;
import resources.interfaces.entities.UsuariDAO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UsuariMockDAO implements UsuariDAO {

    Map<String, Usuari> usuaris = new HashMap<>();

    public UsuariMockDAO() {
        addUsuari("ajaleo@gmail.com", "Contrasenya1!", "ajaleo", "15-04-2023", "01-01-2023");
        addUsuari("marta.soler@example.com", "Passw0rd!", "martas", "22-11-2024", "15-02-2023");
        addUsuari("joan93@example.org", "Secure123!", "joan93", "03-09-2023", "20-03-2024");
        addUsuari("lucia_perez@example.com", "Qwerty!9", "luciap", "10-07-2025", "05-04-2024");
        addUsuari("david.ros@example.net", "DavR0s#", "dros", "29-01-2024", "10-04-2025");
    }

    public Optional<Usuari> getById(String[] id) throws Exception {
        String email = Objects.requireNonNull(id[0], "L'email no pot ser null");
        return Optional.ofNullable(usuaris.get(email));
    }

    @Override
    public List<Usuari> getAll() throws Exception {
        return new ArrayList<>(usuaris.values());
    }

    @Override
    public boolean add(Usuari usuari) throws Exception {
        if (getById(new String[]{usuari.getEmail()}).isPresent()) {
            return false;
        }
        usuaris.put(usuari.getEmail(), usuari);
        return true;
    }

    @Override
    public boolean delete(Usuari usuari) throws Exception {
        return usuaris.remove(usuari.getEmail()) != null;
    }

    public boolean update(Usuari usuari) throws Exception {
        if (!getById(new String[]{usuari.getEmail()}).isPresent()) {
            return false;
        }
        usuaris.put(usuari.getEmail(), usuari);
        return true;
    }

    private void addUsuari(
            String email,
            String password,
            String nomUsuari,
            String dataNaixement,
            String dataRegistre
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);

        Usuari usuari = new Usuari(
                email,
                password,
                nomUsuari,
                LocalDate.parse(dataNaixement, formatter),
                LocalDate.parse(dataRegistre, formatter)
        );

        usuaris.put(usuari.getEmail(), usuari);
    }
}
