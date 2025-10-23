Feature: Login en Ferremas
  Como usuario de Ferremas
  Quiero poder iniciar sesión
  Para acceder al sistema

  Scenario Outline: Login exitoso con diferentes tipos de usuario
    Given que estoy en la página de login de Ferremas
    When ingreso el usuario "<email>"
    And ingreso la contraseña "<password>"
    And hago click en el botón de login
    Then debería ver mi nombre de usuario "<nombre_esperado>" en el navbar
    And la URL debería contener "<url_esperada>"

    Examples:
      | email               | password  | nombre_esperado | url_esperada |
      | pablo@mail.com      | macoy123  | PABLO           | /            |
      | admin@ferremas.cl   | admin123  | ADMIN           | /admin       |

  Scenario Outline: Login fallido con credenciales inválidas
    Given que estoy en la página de login de Ferremas
    When ingreso el usuario "<email>"
    And ingreso la contraseña "<password>"
    And hago click en el botón de login
    Then debería ver un mensaje de error de credenciales incorrectas
    And debería permanecer en la página de login

    Examples:
      | email                        | password            |
      | pablo@mail.com               | passwordIncorrecto  |
      | usuarioincorrecto@mail.com   | macoy123            |
      | incorrecto@mail.com          | passwordIncorrecto  |