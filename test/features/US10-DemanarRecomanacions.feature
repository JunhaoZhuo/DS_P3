Feature: Demanar recomanacions
  Com a usuari
  Vull rebre recomanacions personalitzades
  Per així descobrir més jocs que em puguin interessar

  Scenario Outline: Éxit al demanar recomanacions amb compte registrat
    Given usuari "<usuari>" registrat
    When demano recomanacions
    Then el sistema mostra una llista de jocs recomanats segons l'interès

    Examples:
      | usuari  |
      | "Joan"  |
      | "Anna"  |

  Scenario Outline: Demanar recomanacions sense compte registrat
    Given usuari "<usuari>" no registrat
    When intento demanar recomanacions
    Then el sistema mostrarà el missatge "Cal que et registris per rebre recomanacions"

    Examples:
      | usuari      |
      | "Visitant1" |
      | "Visitant2" |
