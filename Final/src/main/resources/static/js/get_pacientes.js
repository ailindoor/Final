let pacienteIdToDelete = null;

function obtenerPacientes() {
$.ajax({
                url: '/pacientes', // URL del endpoint para obtener los odontologos
                type: 'GET',
                dataType: 'json',
                success: function(data) {
                    let tableBody = $('#pacientesTable tbody');
                    tableBody.empty(); // Limpiar la tabla

                    if (data.length === 0) {
                        // Si no existen odontologos
                        tableBody.append(`
                             <tr>
                                 <td colspan="10" class="text-center">No existen pacientes disponibles</td>
                             </tr>
                        `);
                    } else {
                                    data.forEach(paciente => {
                                        tableBody.append(`
                                            <tr>
                                                <td style="display: none">${paciente.id}</td>
                                                <td>${paciente.nombre}</td>
                                                <td>${paciente.apellido}</td>
                                                <td>${paciente.dni}</td>
                                                <td>${paciente.domicilio.calle}</td>
                                                <td>${paciente.domicilio.numero}</td>
                                                <td>${paciente.domicilio.localidad}</td>
                                                <td>${paciente.domicilio.provincia}</td>
                                                <td>${paciente.fechaAlta}</td>
                                                <td>
                                                   <a type="button" class="btn btn-primary" style="color: #ffffff;" href="pacienteActualizar.html?id=${paciente.id}"><i class="fa-sharp fa-solid fa-pen-to-square"></i></a>
                                                </td>
                                                <td>
                                                   <a type="button" class="btn btn-danger"  data-toggle="modal" data-target="#modalEliminar" style="color: #ffffff;" onclick="confirmarEliminacion(${paciente.id})"><i class="fa-sharp fa-solid fa-trash"></i></a>
                                                </td>
                                            </tr>
                                        `);
                                    });
                    }
                },
                error: function(error) {
                    console.error('Error al obtener los pacientes:', error);
                }
            });
 }

function confirmarEliminacion(id) {
    pacienteIdToDelete = id; // Guardamos el ID del odontólogo a eliminar
    $('#modalEliminar').modal('show'); // Mostrar modal de confirmación
}
$('#confirmDeleteButton').on('click', function() {
    eliminarPaciente(pacienteIdToDelete); // Llamar la función para eliminar
    $('#modalEliminar').modal('hide'); // Ocultar modal después de eliminar
});
function eliminarPaciente(id) {
    $.ajax({
        url: `/pacientes/eliminar/${id}`,
        type: 'GET',
        success: function() {
            console.log('Paciente eliminado correctamente.');
            obtenerPacientes(); // Recargar la tabla de odontólogos después de eliminar
        },
        error: function() {
            console.log('Error al eliminar el paciente.');
        }
    });
}
  // Llamar a la función para obtener y mostrar los odontologos
          $(document).ready(function() {
              obtenerPacientes();
          });