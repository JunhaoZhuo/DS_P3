Feature: Adquirir joc
  Com a usuari
  Vull adquirir un joc que m’interessa
  Per així poder jugar-ho posteriorment

  Scenario Outline: Èxit al adquirir un joc disponible tenint un compte
    Given usuari "<usuari>"registrat
    When adquereixo un joc "<FPS>" disponible
    Then podré jugar el joc "<FPS>" adquirit
    Examples:
      | usuari    | FPS       |
      | "Joan"    | "Call of Duty" |
      | "Anna"    | "Battlefield"   |


  Scenario Outline: Adquirir un joc disponible sense tenir un compte
    Given usuari "<usuari>" no registrat
    When intento adquirir un joc "<FPS>" disponible
    Then el sistema mostrarà el missatge "Cal que et registris per adquirir jocs"
    Examples:
      | usuari    |   FPS       |
      | "Joan"    | "Call of Duty" |
      | "Anna"    | "Battlefield"  |

  Scenario Outline: Adquirir un joc anunciat
    Given usuari "<usuari>" registrat
    When intento adquirir un joc "<FPS>" anunciat
    Then el sistema mostrarà el missatge "No pots adquirir jocs anunciats"
    Examples:
      | usuari    |   FPS       |
      | "Joan"    | "Call of Duty" |
      | "Anna"    | "Battlefield"  |
