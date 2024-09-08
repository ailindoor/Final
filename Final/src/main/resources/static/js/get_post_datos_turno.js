 let turnoIdToDelete = null;
function obtenerTurnos() {
$.ajax({
                url: '/turnos', // URL del endpoint para obtener turnos
                type: 'GET',
                dataType: 'json',
                success: function(data) {
                    const tableBody = $('#turnos-table-body');
                    tableBody.empty(); // Limpiar la tabla

                    if (data.length === 0) {
                        // Si no hay turnos
                        const row = $('<tr></tr>');
                        const noDataCell = $('<td></td>').attr('colspan', '5').addClass('text-center').text('No existen turnos registrados');
                        row.append(noDataCell);
                        tableBody.append(row);
                    } else {
                        // Si existen turnos
                        data.forEach(turno => {
                            const row = $('<tr></tr>');

                            // Columna ID
                            const idCell = $('<td></td>').text(turno.id);
                            row.append(idCell);

                            // Columna Paciente
                            const pacienteCell = $('<td></td>').text(turno.paciente ? `${turno.paciente.nombre} ${turno.paciente.apellido}` : 'Sin asignar');
                            row.append(pacienteCell);

                            // Columna Odontólogo
                            const odontologoCell = $('<td></td>').text(turno.odontologo ? `${turno.odontologo.nombre} ${turno.odontologo.apellido}` : 'Sin asignar');
                            row.append(odontologoCell);

                            // Columna Fecha
                            const fechaCell = $('<td></td>').text(turno.fecha);
                            row.append(fechaCell);

                            // Columna Eliminar
                            const eliminarCell = $('<td></td>');
                            const eliminarBtn = $('<a type="button" class="btn btn-danger"  data-toggle="modal" data-target="#modalEliminar" style="color: #ffffff;"><i class="fa-sharp fa-solid fa-trash"></i></a>').addClass('btn btn-danger');
                            eliminarBtn.on('click', function() {
                                turnoIdToDelete = turno.id;
                                $('#confirmDeleteModal').modal('show');
                            });
                            eliminarCell.append(eliminarBtn);
                            row.append(eliminarCell);

                            // Agregar la fila a la tabla
                            tableBody.append(row);
                        });
                    }
                },
                error: function(error) {
                    console.error('Error al obtener los turnos:', error);
                }
            });
 }
 // Evento cuando se abre el modal
    $('#crearTurnoModal').on('show.bs.modal', function (event) {
        // Hacer la solicitud AJAX para obtener odontólogos y pacientes
        $.ajax({
            url: '/turnos/nuevo',  // La URL de la API en el backend
            method: 'GET',
            success: function (data) {
                // Limpiar las opciones de los selects
                $('#odontologo').empty();
                $('#paciente').empty();

                // Agregar una opción por defecto
                $('#odontologo').append('<option value="" disabled selected>Seleccione un odontólogo</option>');
                $('#paciente').append('<option value="" disabled selected>Seleccione un paciente</option>');

                // Llenar el select de odontólogos
                data.odontologos.forEach(function (odontologo) {
                    $('#odontologo').append('<option value="' + odontologo.id + '">' + odontologo.nombre + ' ' + odontologo.apellido + '</option>');
                });

                // Llenar el select de pacientes
                data.pacientes.forEach(function (paciente) {
                    $('#paciente').append('<option value="' + paciente.id + '">' + paciente.nombre + ' ' + paciente.apellido + '</option>');
                });
            },
            error: function () {
                alert('Error al cargar los datos.');
            }
        });
    });
    //Crear el nuevo turno
    $('#formCrearTurno').on('submit', function (event) {
            event.preventDefault();

            // Obtener los valores seleccionados
            const odontologoId = $('#odontologo').val();
            const pacienteId = $('#paciente').val();
            const fechaTurno = $('#fecha').val();

            // Hacer la solicitud POST para crear el turno
            $.ajax({
                url: '/turnos',
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({
                    paciente: { id: pacienteId },
                    odontologo: { id: odontologoId },
                    fecha: fechaTurno
                }),
                success: function (response) {
                    $('#successModal').modal('show');
                    $('#crearTurnoModal').modal('hide'); // Cerrar el modal después de la asignación
                    obtenerTurnos();
                },
                error: function () {
                    alert('Se produjo un error al asignar el turno.');
                }
            });
        });
  //Funciona para eliminar un turno
  function eliminarTurno(id) {
              $.ajax({
                  url: `/turnos/eliminar/${id}`,
                  type: 'GET',
                  success: function() {
                      obtenerTurnos(); // Actualizar la tabla
                  },
                  error: function(error) {
                      console.error('Error al eliminar el turno:', error);
                  }
              });
          }
 // Confirmar eliminación del turno
         $('#confirmDeleteButton').on('click', function() {
             eliminarTurno(turnoIdToDelete);
             $('#modalEliminar').modal('hide');
         });
 // Llamar a la función para obtener y mostrar los turnos cuando se cargue la página
         $(document).ready(function() {
             obtenerTurnos();
         });