
      function defineOpcion(opcion, id) {

        document.forms[0].opcion.value = opcion;
        //id es para actualizar y eliminar
        document.forms[0].idAgencia.value = id;
        //si la opción es eliminar (6), debe preguntar si está seguro.
        if (opcion !== 6) {
          document.forms[0].submit();
        } else if (confirm("¿esta seguro de borrar al id " + id + "?")) {
          document.forms[0].submit();
        }

      }
