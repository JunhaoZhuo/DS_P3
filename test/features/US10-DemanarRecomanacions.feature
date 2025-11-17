Feature: Demanar recomanacions
  Com a usuari
  Vull rebre recomanacions personalitzades
  Per així descobrir més jocs que em puguin interessar

  @TA10.a
  Scenario: Un usuari rep recomanacions basades en gèneres
    Given els usuaris i les seves adquisicions es troben carregats de la base de dades
    When l'usuari "marta.soler@example.com" demana recomanacions
    Then el sistema mostra el missatge següent:
      """
      Jocs recomanats per a tu:
      Elden Ring: Shadow of the Erdtree
      """

  @TA10.b
  Scenario: Un usuari no registrat demana recomanacions
    Given s'ha carregat el catàleg de la base de dades
    When l'usuari "visitant@example.com" demana recomanacions
    Then el sistema mostra el missatge "No existeix cap usuari amb aquest e-mail"