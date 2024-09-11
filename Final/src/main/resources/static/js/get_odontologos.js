let odontologoIdToDelete = null;

function obtenerOdontologos() {
$.ajax({
                url: '/odontologos',
                type: 'GET',
                dataType: 'json',
                success: function(data) {
                    let tableBody = $('#odontologosTable tbody');
                    tableBody.empty();

                    if (data.length === 0) {

                        tableBody.append(`
                             <tr>
                                 <td colspan="6" class="text-center">No existen odontólogos disponibles</td>
                             </tr>
                        `);
                    } else {
                                    data.forEach(odontologo => {
                                        tableBody.append(`
                                            <tr>
                                                <td style="display: none">${odontologo.id}</td>
                                                <td>${odontologo.nombre}</td>
                                                <td>${odontologo.apellido}</td>
                                                <td>${odontologo.matricula}</td>
                                                <td>
                                                   <a type="button" class="btn btn-primary" style="color: #ffffff;" href="odontologoActualizar.html?id=${odontologo.id}"><i class="fa-sharp fa-solid fa-pen-to-square"></i></a>
                                                </td>
                                                <td>
                                                   <a type="button" class="btn btn-danger"  data-toggle="modal" data-target="#modalEliminar" style="color: #ffffff;" onclick="confirmarEliminacion(${odontologo.id})"><i class="fa-sharp fa-solid fa-trash"></i></a>
                                                </td>
                                            </tr>
                                        `);
                                    });
                    }
                },
                error: function(error) {
                    console.error('Error al obtener los odontologos:', error);
                }
            });
 }

function confirmarEliminacion(id) {
    odontologoIdToDelete = id;
    $('#modalEliminar').modal('show');
}
$('#confirmDeleteButton').on('click', function() {
    eliminarOdontologo(odontologoIdToDelete);
    $('#modalEliminar').modal('hide');
});
function eliminarOdontologo(id) {
    $.ajax({
        url: `/odontologos/eliminar/${id}`,
        type: 'DELETE',
        success: function() {
            console.log('Odontólogo eliminado correctamente.');
            obtenerOdontologos();
        },
        error: function() {
            console.log('Error al eliminar el odontólogo.');
        }
    });
}

          $(document).ready(function() {
              obtenerOdontologos();
          });