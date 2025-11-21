Feature: Llistar valoracions
  Com a usuari
  Vull veure les valoracions (revisions i comentaris) que he fet
  Per recordar les meves opinions sobre els jocs

  @TA11.a
  Scenario: Llistar valoracions mixtes (revisions i comentaris)
    Given els usuaris i les seves adquisicions es troben carregats de la base de dades
    # Assumim que les dades Mock carregaran una revisió i un comentari per a "ajaleo@gmail.com"
    When l'usuari "ajaleo@gmail.com" sol·licita veure la llista de les seves valoracions
    Then el sistema mostra el missatge següent:
      """
      Valoracions de l'usuari:
      - Elden Ring: Shadow of the Erdtree: [Revisió] Nota mitjana: 9.25
      - Paragon: [Comentari] Joc molt divertit però tancat massa aviat (2018-04-20) - per ajaleo@gmail.com
      """