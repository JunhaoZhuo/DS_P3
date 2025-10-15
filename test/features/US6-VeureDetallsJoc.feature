Feature: Veure detalls d'un joc
  COM A usuari
  VULL veure els detalls d'un joc
  PER PODER saber-ne coses com ara el gènere, la data de llançament, la classificació per edats, etc.

  # TA6. a
  Scenario: Veure els detalls d'un joc existent
    Given s'ha carregat el catàleg de la base de dades
    When l'usuari tria veure els detalls del joc "Elden Ring: Shadow of the Erdtree"
    Then el sistema mostra el missatge següent:
    """
    Títol: "Elden Ring: Shadow of the Erdtree"
    Gènere(s): RPG
    Desenvolupadora(es): FromSoftware
    Distribuïdora(es): Bandai Namco Entertainment
    Data d'anunci: 23-02-2022
    Data de llançament: 21-06-2024
    Data de retirada: N/A
    Estat: DISPONIBLE
    """

  # TA6. b
  Scenario: Veure els detalls d'un joc inexistent
    Given s'ha carregat el catàleg de la base de dades
    When l'usuari tria veure els detalls del joc "Dark Souls IV"
    Then el sistema mostra el missatge "No s'ha trobat cap joc amb aquest títol"

