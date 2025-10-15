Feature: Veure llista de jocs del catàleg
  COM A usuari
  VULL veure la llista de jocs del catàleg
  PER PODER conèixer els jocs que ofereix la plataforma

  # TA4. a
  Scenario: Veure els títols del catàleg ordenats per nom (alfabèticament)
    Given s'ha carregat el catàleg de la base de dades
    When l'usuari sol·licita veure la llista de jocs del catàleg ordenats per nom (alfabèticament)
    Then el sistema mostra el missatge següent:
    """
    Llista de jocs del catàleg:
    Baldur's Gate 3
    Cyberpunk 2077
    Death Stranding 2
    Elden Ring: Shadow of the Erdtree
    Grand Theft Auto VI
    LawBreakers
    Palworld
    Paragon
    """

  # TA4 .b
  Scenario: Veure els títols del catàleg si es troba buit
    Given el catàleg no conté cap videojoc
    When l'usuari sol·licita veure la llista de jocs del catàleg ordenats per nom (alfabèticament)
    Then el sistema mostra el missatge "No hi ha jocs disponibles al catàleg en aquest moment"

