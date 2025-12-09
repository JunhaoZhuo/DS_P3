Feature: Comentar Joc
  COM A usuari identificat
  VULL comentar sobre un joc (anunciat, disponible o retirat)
  PER A poder compartir informació amb altres usuaris

  @TA12.a
  Scenario Outline: Comentar un joc en qualsevol estat
    Given un usuari s'ha registrat amb l'e-mail "<email>" i dades vàlides
    And existeix un joc anomenat "<titolJoc>" amb estat "<estat>"
    When l'usuari amb e-mail "<email>" comenta el joc "<titolJoc>" amb el text "<text>"
    Then el sistema mostra el missatge "Comentari afegit correctament"
    And els detalls del joc "<titolJoc>" mostren el comentari "<text>" fet per "<email>"

    Examples:
      | email             | titolJoc            | estat      | text                     |
      | joan@example.com  | GTA VI              | ANUNCIAT   | Quines ganes que surti!  |
      | anna@example.com  | Elden Ring          | DISPONIBLE | És una obra mestra.      |
      | marc@example.com  | Paragon             | RETIRAT    | Et trobem a faltar.      |

  @TA12.b
  Scenario: Intentar comentar sense estar registrat
    Given existeix un joc anomenat "Minecraft" amb estat "DISPONIBLE"
    When l'usuari amb e-mail "unknown@example.com" comenta el joc "Minecraft" amb el text "Hola"
    Then el sistema mostra el missatge "No existeix cap usuari amb aquest e-mail"