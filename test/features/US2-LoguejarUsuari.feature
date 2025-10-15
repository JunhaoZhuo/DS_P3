Feature: Loguejar usuari
  COM A usuari registrat
  VULL poder iniciar sessió al sistema amb les meves credencials
  PER PODER accedir a les funcionalitats restringides del sistema

  # TA2. a
  Scenario Outline: L'usuari que s'acaba de registrar inicia sessió correctament amb credencials vàlides
    Given un usuari s'ha registrat amb l'e-mail "<email>", la contrasenya "<contrasenya>", el nom d'usuari "<nomUsuari>" i la data de naixement "<dataNaixement>"
    When l'usuari inicia sessió amb l'e-mail "<email>" i la contrasenya "<contrasenya>"
    Then el sistema mostra el missatge "Sessió iniciada correctament"
    Examples:
      | email              | contrasenya | nomUsuari | dataNaixement |
      | johndoe@gmail.com  | password1$  | johndoe   | 01-01-1990    |
      | maria@gmail.com    | Mpassword2$ | maria     | 15-06-1988    |

  # TA2. b
  Scenario Outline: L'usuari introdueix un e-mail no registrat
    When l'usuari inicia sessió amb l'e-mail "<email>" i la contrasenya "<contrasenya>"
    Then el sistema mostra el missatge "No existeix cap usuari amb aquest e-mail"
    Examples:
      | email                  | contrasenya |
      | nouusuari@gmail.com    | prova1$     |
      | desconegut@outlook.com | prova2$     |

  # TA2. c
  Scenario Outline: L'usuari introdueix una contrasenya incorrecta
    Given un usuari s'ha registrat amb l'e-mail "<email>", la contrasenya "password1$", el nom d'usuari "<nomUsuari>" i la data de naixement "<dataNaixement>"
    When l'usuari inicia sessió amb l'e-mail "<email>" i la contrasenya "<contrasenya>"
    Then el sistema mostra el missatge "La contrasenya no és correcta"
    Examples:
      | email              | contrasenya | nomUsuari | dataNaixement |
      | johndoe@gmail.com  | password2$  | johndoe   | 01-01-1990    |
      | maria@gmail.com    | prova123$   | maria     | 15-06-1988    |

  # TA2. d
  Scenario Outline: L'usuari deixa algun dels camps obligatoris buit (e-mail o contrasenya)
    Given un usuari s'ha registrat amb l'e-mail "johndoe@gmail.com", la contrasenya "password1$", el nom d'usuari "johndoe" i la data de naixement "01-01-1990"
    When l'usuari inicia sessió amb l'e-mail "<email>" i la contrasenya "<contrasenya>"
    Then el sistema mostra el missatge "<resultat>"
    Examples:
      | email             | contrasenya | resultat                            |
      |                   | password1$  | L'e-mail no pot ser buit            |
      | johndoe@gmail.com |             | La contrasenya no pot ser buida     |

  # TA2. e
  Scenario Outline: L'usuari registrat a la base de dades inicia sessió correctament amb credencials vàlides
    Given s'han carregat els usuaris de la base de dades
    When l'usuari inicia sessió amb l'e-mail "<email>" i la contrasenya "<contrasenya>"
    Then el sistema mostra el missatge "Sessió iniciada correctament"
    Examples:
      | email                     | contrasenya     |
      | ajaleo@gmail.com          | Contrasenya1!   |
      | marta.soler@example.com   | Passw0rd!       |
      | david.ros@example.net     | DavR0s#         |

