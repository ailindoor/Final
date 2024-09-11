
function getQueryParam(param) {
            const urlParams = new URLSearchParams(window.location.search);
            return urlParams.get(param);
}

         $('#odontologoForm').on('submit', function(event) {
             event.preventDefault();
             let odontologo = {
                 "nombre": $('#nombre').val(),
                 "apellido": $('#apellido').val(),
                 "matricula": $('#matricula').val()
             };

             $.ajax({
                 url: `/odontologos`,
                 type: 'POST',
                 contentType: 'application/json',
                 data: JSON.stringify(odontologo),
                 success: function() {
                     $('#successModal').modal('show');
                     window.location.href = '/odontologoListado.html';
                 },
                 error: function() {
                     console.log('Error al agregar los datos.');
                 }
             });
         });