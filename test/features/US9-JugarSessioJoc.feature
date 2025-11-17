Feature: Jugar sessió de joc
  Com a usuari
  Vull jugar una sessió d’un joc adquirit
  Per així passar-me l'estona avorrida

  Scenario Outline: Éxit al jugar sessió d'un joc adquirit
    Given usuari "<usuari>" registrat
    When decideixo jugar una sessió del joc "<joc>"
    Then accedirà a la sessió de joc i el sistema desarà informacions com quin joc, quanta estona i què va aconseguir

    Examples:
      | usuari | joc             |
      | "Joan" | "FIFA 24"       |
      | "Anna" | "Assassin's Creed" |

  Scenario Outline: Éxit al jugar sessió d'un joc adquirit fa temps que ara està retirat
    Given usuari "<usuari>" registrat
    When decideixo jugar una sessió del joc "<joc>" adquirit fa temps que ara està retirat
    Then accedirà a la sessió de joc i el sistema desarà informacions com quin joc, quanta estona i què va aconseguir

    Examples:
      | usuari | joc             |
      | "Marc" | "Minecraft"     |
      | "Laia" | "Cyberpunk 2077" |

