
function getQueryParam(param) {
            const urlParams = new URLSearchParams(window.location.search);
            return urlParams.get(param);
}

        $(document).ready(function() {
            const id = getQueryParam('id');
            if (id) {
                $.ajax({
                    url: `/odontologos/buscar/${id}`,
                    type: 'GET',
                    success: function(data) {
                        $('#id').val(data.id);
                        $('#nombre').val(data.nombre);
                        $('#apellido').val(data.apellido);
                        $('#matricula').val(data.matricula);
                    },
                    error: function() {
                        console.log('Error al obtener los datos del odontólogo.');
                    }
                });
            }
        });


         $('#odontologoForm').on('submit', function(event) {
             event.preventDefault();
             let id = $('#id').val();
             let odontologo = {
                 "id": id,
                 "nombre": $('#nombre').val(),
                 "apellido": $('#apellido').val(),
                 "matricula": $('#matricula').val()
             };

             $.ajax({
                 url: `/odontologos/actualizar/${id}`,
                 type: 'PUT',
                 contentType: 'application/json',
                 data: JSON.stringify(odontologo),
                 success: function() {
                     $('#successModal').modal('show');
                     window.location.href = '/odontologoListado.html'; // Redirigir a la lista de odontólogos
                 },
                 error: function() {
                     console.log('Error al actualizar los datos.');
                 }
             });
         });