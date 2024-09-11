// Función para obtener los parámetros de la URL
function getQueryParam(param) {
            const urlParams = new URLSearchParams(window.location.search);
            return urlParams.get(param);
}
 // Cargar los datos del paciente
        $(document).ready(function() {
            const id = getQueryParam('id');
            if (id) {
                $.ajax({
                    url: `/pacientes/buscar/${id}`,
                    type: 'GET',
                    success: function(data) {
                        $('#id').val(data.id);
                        $('#nombre').val(data.nombre);
                        $('#apellido').val(data.apellido);
                        $('#dni').val(data.dni);
                        $('#calle').val(data.domicilio.calle);
                        $('#numero').val(data.domicilio.numero);
                        $('#localidad').val(data.domicilio.localidad);
                        $('#provincia').val(data.domicilio.provincia);
                        $('#fecha').val(data.fechaAlta);
                    },
                    error: function() {
                        console.log('Error al obtener los datos del odontólogo.');
                    }
                });
            }
        });

  // Manejar el envío del formulario actualizar odontologo
         $('#pacienteForm').on('submit', function(event) {
             event.preventDefault();
             let id = $('#id').val();
              let paciente = {
                            "id": id,
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
                 url: `/pacientes/actualizar/${id}`,
                 type: 'PUT',
                 contentType: 'application/json',
                 data: JSON.stringify(paciente),
                 success: function() {
                     window.location.href = '/pacienteListado.html'; // Redirigir a la lista de pacientes
                      $('#successModalPas').modal('show');
                 },
                 error: function() {
                     console.log('Error al actualizar los datos.');
                 }
             });
         });