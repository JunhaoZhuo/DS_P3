Feature: Jugar sessió de joc
  Com a usuari
  Vull jugar una sessió d’un joc adquirit
  Per així passar-me l'estona avorrida

  @TA9.a
  Scenario Outline: Iniciar i finalitzar una sessió d'un joc adquirit
    Given l'usuari "<email>" ha adquirit el joc "<titolJoc>"
    When l'usuari "<email>" inicia i finalitza una sessió del joc "<titolJoc>"
    Then el sistema mostra el missatge "Sessió de joc finalitzada correctament"
    Examples:
      | email            | titolJoc           |
      | joan@example.com | FIFA 24            |
      | laia@example.com | Cyberpunk 2077     |

  @TA9.b
  Scenario: Intentar jugar un joc no adquirit
    Given un usuari s'ha registrat amb l'e-mail "anna@example.com" i dades vàlides
    And un joc disponible anomenat "Assassin's Creed" existeix al catàleg
    When l'usuari "anna@example.com" inicia i finalitza una sessió del joc "Assassin's Creed"
    Then el sistema mostra el missatge "No pots jugar a un joc que no has adquirit"