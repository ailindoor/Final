let pacienteIdToDelete = null;

function obtenerPacientes() {
$.ajax({
                url: '/pacientes',
                type: 'GET',
                dataType: 'json',
                success: function(data) {
                    let tableBody = $('#pacientesTable tbody');
                    tableBody.empty();

                    if (data.length === 0) {

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
    pacienteIdToDelete = id;
    $('#modalEliminar').modal('show');
}
$('#confirmDeleteButton').on('click', function() {
    eliminarPaciente(pacienteIdToDelete);
    $('#modalEliminar').modal('hide');
});
function eliminarPaciente(id) {
    $.ajax({
        url: `/pacientes/eliminar/${id}`,
        type: 'DELETE',
        success: function() {
            console.log('Paciente eliminado correctamente.');
            obtenerPacientes();
        },
        error: function() {
            console.log('Error al eliminar el paciente.');
        }
    });
}

          $(document).ready(function() {
              obtenerPacientes();
          });