
function getQueryParam(param) {
            const urlParams = new URLSearchParams(window.location.search);
            return urlParams.get(param);
}

         $('#pacienteForm').on('submit', function(event) {
             event.preventDefault();
             let paciente = {
             "nombre": $('#nombre').val(),
              "apellido": $('#apellido').val(),
              "dni": $('#dni').val(),
              "fechaAlta": $('#fecha').val(),
               "domicilio": {
                     "calle": $('#calle').val(),
                     "numero": $('#numero').val(),
                     "localidad": $('#localidad').val(),
                     "provincia": $('#provincia').val()
                 }
             };

             $.ajax({
                 url: `/pacientes`,
                 type: 'POST',
                 contentType: 'application/json',
                 data: JSON.stringify(paciente),
                 success: function() {
                     $('#successModalPas').modal('show');
                     window.location.href = '/pacienteListado.html';
                 },
                 error: function() {
                     console.log('Error al agregar los datos.');
                 }
             });
         });