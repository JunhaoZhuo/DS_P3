Feature: Registrar usuari
    COM A usuari nou
    VULL registrar-me al sistema
    PER PODER accedir a totes les funcionalitats del sistema

  # TA1. a
  Scenario Outline: L'usuari es registra correctament proporcionant les dades obligatòries (e-mail, contrasenya, nom d'usuari, i data de naixement) i el sistema confirma el registre
    Given un nou usuari amb un e-mail no existent
    When l'usuari es registra amb l'e-mail "<email>", la contrasenya "<contrasenya>", el nom d'usuari "<nomUsuari>" i la data de naixement "<dataNaixement>"
    Then el sistema mostra el missatge "<resultat>"
    Examples:
      | email                   | contrasenya | nomUsuari   | dataNaixement | resultat                      |
      | johndoe@gmail.com       | password1$  | johndoe     | 01-01-1990    | Usuari registrat correctament |
      | ajaleo@gmail.com        | password1$  | ajaleo      | 01-01-1990    | Usuari registrat correctament |
      | lucia_perez@example.com | password1$  | lucia_perez | 01-01-1990    | Usuari registrat correctament |

  # TA1. b
  Scenario Outline: L'usuari no introdueix alguna/es dada de les obligatòries (e-mail, contrasenya, nom d'usuari o data de naixement) i el sistema avisa de la primera dada que falta
    Given un nou usuari amb un e-mail no existent
    When l'usuari es registra amb l'e-mail "<email>", la contrasenya "<contrasenya>", el nom d'usuari "<nomUsuari>" i la data de naixement "<dataNaixement>"
    Then el sistema mostra el missatge "<resultat>"
    Examples:
      | email              | contrasenya | nomUsuari | dataNaixement | resultat                                |
      |                    | password1$  | johndoe   | 01-01-1990    | L'e-mail no pot ser buit                |
      | johndoe@gmail.com  |             | johndoe   | 01-01-1990    | La contrasenya no pot ser buida         |
      | johndoe@gmail.com  | password1$  |           | 01-01-1990    | El nom d'usuari no pot ser buit         |
      | johndoe@gmail.com  | password1$  | johndoe   |               | La data de naixement no pot ser buida   |
      | johndoe@gmail.com  | password1$  |           |               | El nom d'usuari no pot ser buit         |

  # TA1. c
  Scenario: Existeix un usuari registrat amb el mateix e-mail i el sistema avisa de l'error
    Given un usuari s'ha registrat amb l'e-mail "johndoe@gmail.com", la contrasenya "password1$", el nom d'usuari "johndoe" i la data de naixement "01-01-1991"
    When l'usuari es registra amb l'e-mail "johndoe@gmail.com", la contrasenya "password1$", el nom d'usuari "johndoe" i la data de naixement "01-01-1991"
    Then el sistema mostra el missatge "Ja existeix un usuari amb aquest e-mail"

  # TA1. d
  Scenario: Existeix un usuari registrat amb el mateix nom d'usuari i el sistema avisa de l'error
    Given un usuari s'ha registrat amb l'e-mail "johndoe@gmail.com", la contrasenya "password1$", el nom d'usuari "johndoe" i la data de naixement "01-01-1991"
    When l'usuari es registra amb l'e-mail "johndoe2@gmail.com", la contrasenya "password1$", el nom d'usuari "johndoe" i la data de naixement "01-01-1991"
    Then el sistema mostra el missatge "Ja existeix un usuari amb aquest nom d'usuari"

  # TA1. e
  Scenario Outline: L'usuari introdueix un e-mail amb format invàlid i el sistema avisa de l'error
    Given un nou usuari amb un e-mail no existent
    When l'usuari es registra amb l'e-mail "<email>", la contrasenya "password1$", el nom d'usuari "johndoe" i la data de naixement "01-01-1990"
    Then el sistema mostra el missatge "L'e-mail no té un format vàlid"
    Examples:
      | email              |
      | johndoe            |
      | johndoe@gmail      |
      | johndoe@gmail.     |
      | johndoe@.com       |
      | @gmail.com         |
      | johndoe@gmail..com |

  # TA1. f
  Scenario Outline: L'usuari introdueix una contrasenya que no compleix els requisits mínims i el sistema avisa de quin requisit no es compleix
    Given un nou usuari amb un e-mail no existent
    When l'usuari es registra amb l'e-mail "johndoe@gmail.com", la contrasenya "<contrasenya>", el nom d'usuari "johndoe" i la data de naixement "01-01-1990"
    Then el sistema mostra el missatge "<resultat>"
    Examples:
        | contrasenya      | resultat                                           |
        | pass             | La contrasenya he de tenir 8 caràcters o més       |
        | password         | La contrasenya he de tenir almenys un nombre       |
        | password1        | La contrasenya he de tenir almenys un símbol       |
        | password$        | La contrasenya he de tenir almenys un nombre       |
        | 12345678$        | La contrasenya ha de tenir almenys una lletra      |

  # TA1. g
  Scenario Outline: Ja existeix un usuari registrat amb el mateix e-mail a la base de dades i el sistema avisa de l'error
    Given s'han carregat els usuaris de la base de dades
    When l'usuari es registra amb l'e-mail "<email>", la contrasenya "password1$", el nom d'usuari "johndoe" i la data de naixement "01-01-1990"
    Then el sistema mostra el missatge "Ja existeix un usuari amb aquest e-mail"
    Examples:
      | email                      |
      | ajaleo@gmail.com           |
      | lucia_perez@example.com    |