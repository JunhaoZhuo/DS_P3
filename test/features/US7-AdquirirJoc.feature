Feature: Adquirir joc
  Com a usuari
  Vull adquirir un joc que m'interessa
  Per així poder jugar-ho posteriorment

  @TA7.a
  Scenario Outline: Èxit en adquirir un joc disponible
    Given un usuari s'ha registrat amb l'e-mail "<email>" i dades vàlides
    And un joc disponible anomenat "<titolJoc>" existeix al catàleg
    When l'usuari amb e-mail "<email>" adquireix el joc "<titolJoc>"
    Then el sistema mostra el missatge "Joc adquirit correctament"
    Examples:
      | email              | titolJoc     |
      | joan@example.com   | Grand Theft Auto VI    |
  @TA7.b
  Scenario Outline: Adquirir un joc sense estar registrat
    Given un joc disponible anomenat "<titolJoc>" existeix al catàleg
    When l'usuari amb e-mail "<email>" adquireix el joc "<titolJoc>"
    Then el sistema mostra el missatge "No existeix cap usuari amb aquest e-mail"
    Examples:
      | email                | titolJoc         |
      | no.registrat@dot.com | Call of Duty   |

  @TA7.c
  Scenario Outline: Intentar adquirir un joc que no està disponible
    Given un usuari s'ha registrat amb l'e-mail "<email>" i dades vàlides
    And un joc anunciat anomenat "<titolJoc>" existeix al catàleg
    When l'usuari amb e-mail "<email>" adquireix el joc "<titolJoc>"
    Then el sistema mostra el missatge "El joc no està disponible per adquirir"
    Examples:
      | email              | titolJoc                 |
      | joan@example.com   | Grand Theft Auto VI    |