Feature: Revisar joc
  Com a usuari
  Vull revisar un joc
  Per així compartir la meva valoració amb la comunitat

  @TA8.a
  Scenario: Un usuari revisa un joc adquirit per primer cop
    Given l'usuari "joan@example.com" ha adquirit el joc "FIFA 24"
    When l'usuari "joan@example.com" revisa el joc "FIFA 24" amb les següents puntuacions:
      | Categoria   | Puntuacio |
      | Jugabilitat | 8         |
      | Grafics     | 7         |
      | Historia    | 5         |
      | Musica      | 9         |
    Then el sistema mostra el missatge "Revisió afegida correctament"

  @TA8.b
  Scenario: Un usuari torna a revisar un joc (actualitza la revisió)
    Given l'usuari "laia@example.com" ha adquirit el joc "Cyberpunk 2077"
    And l'usuari "laia@example.com" ja ha revisat el joc "Cyberpunk 2077"
    When l'usuari "laia@example.com" revisa el joc "Cyberpunk 2077" amb les següents puntuacions:
      | Categoria   | Puntuacio |
      | Jugabilitat | 10        |
      | Grafics     | 10        |
      | Historia    | 9         |
      | Musica      | 9         |
    Then el sistema mostra el missatge "Revisió actualitzada correctament"

  @TA8.c
  Scenario: Intentar revisar un joc no adquirit
    Given un usuari s'ha registrat amb l'e-mail "anna@example.com" i dades vàlides
    And un joc disponible anomenat "Assassin's Creed" existeix al catàleg
    When l'usuari "anna@example.com" revisa el joc "Assassin's Creed" amb les següents puntuacions:
      | Categoria   | Puntuacio |
      | Jugabilitat | 9         |
      | Grafics     | 8         |
      | Historia    | 10        |
      | Musica      | 9         |
    Then el sistema mostra el missatge "No pots revisar un joc que no has adquirit"