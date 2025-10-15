Feature: Veure llista de jocs adquirits
  COM A usuari
  VULL veure la llista de jocs adquirits
  PER PODER saber a quins jocs puc jugar

  # TA5. a
  Scenario: Veure els títols adquirits per usuari ordenats per nom (alfabèticament)
    Given els usuaris i les seves adquisicions es troben carregats de la base de dades
    When l'usuari "ajaleo@gmail.com" sol·licita veure la llista de jocs adquirits ordenats per nom (alfabèticament)
    Then el sistema mostra el missatge següent:
    """
    Llista de jocs adquirits per l'usuari:
    Elden Ring: Shadow of the Erdtree
    Grand Theft Auto VI
    Paragon
    """

  # TA5. b
  Scenario: Veure els títols adquirits per usuari nou (que no ha adquirit cap joc encara)
    Given un usuari s'ha registrat amb l'e-mail "johndoe@gmail.com", la contrasenya "password1$", el nom d'usuari "johndoe" i la data de naixement "01-01-1991"
    When l'usuari "johndoe@gmail.com" sol·licita veure la llista de jocs adquirits ordenats per nom (alfabèticament)
    Then el sistema mostra el missatge "L'usuari no ha adquirit cap joc"

