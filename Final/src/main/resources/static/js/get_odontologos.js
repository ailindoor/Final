let odontologoIdToDelete = null;

function obtenerOdontologos() {
$.ajax({
                url: '/odontologos', // URL del endpoint para obtener los odontologos
                type: 'GET',
                dataType: 'json',
                success: function(data) {
                    let tableBody = $('#odontologosTable tbody');
                    tableBody.empty(); // Limpiar la tabla

                    if (data.length === 0) {
                        // Si no existen odontologos
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
    odontologoIdToDelete = id; // Guardamos el ID del odontólogo a eliminar
    $('#modalEliminar').modal('show'); // Mostrar modal de confirmación
}
$('#confirmDeleteButton').on('click', function() {
    eliminarOdontologo(odontologoIdToDelete); // Llamar la función para eliminar
    $('#modalEliminar').modal('hide'); // Ocultar modal después de eliminar
});
function eliminarOdontologo(id) {
    $.ajax({
        url: `/odontologos/eliminar/${id}`,
        type: 'GET',
        success: function() {
            console.log('Odontólogo eliminado correctamente.');
            obtenerOdontologos(); // Recargar la tabla de odontólogos después de eliminar
        },
        error: function() {
            console.log('Error al eliminar el odontólogo.');
        }
    });
}
  // Llamar a la función para obtener y mostrar los odontologos
          $(document).ready(function() {
              obtenerOdontologos();
          });