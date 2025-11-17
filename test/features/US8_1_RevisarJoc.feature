Feature: Revisar joc
  Com a usuari
  Vull revisar joc
  Per així compartir la meva valoració amb la comunitat

  Scenario Outline: Éxit al revisar un joc adquirit i disponible valorant els 4 camps
    Given usuari "<usuari>" registrat
    When reviso el joc "<joc>" adquirits i disponibles valorant els 4 camps (Jugabilitat, Gràfics, Història, Música)
    Then el sistema desa la valoració mitjana

    Examples:
      | usuari | joc             |
      | "Joan" | "FIFA 24"       |
      | "Anna" | "Assassin's Creed" |

  Scenario Outline: Éxit al tornar revisar un joc adquirit i disponible
    Given usuari "<usuari>" registrat
    When torno revisar el joc "<joc>" adquirits i disponibles valorant els 4 camps
    Then el sistema desa la valoració mitjana nova substituint la anterior

    Examples:
      | usuari | joc             |
      | "Marc" | "Minecraft"     |
      | "Laia" | "Cyberpunk 2077" |

  Scenario Outline: Éxit al revisar un joc adquirit i retirat
    Given usuari "<usuari>" registrat
    When reviso el joc "<joc>" adquirit i retirat valorant els 4 camps
    Then el sistema desa la valoració mitjana

    Examples:
      | usuari | joc             |
      | "Joan" | "The Witcher 3" |
      | "Anna" | "Red Dead 2"    |


