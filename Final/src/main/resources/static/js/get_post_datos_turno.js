 let turnoIdToDelete = null;
function obtenerTurnos() {
$.ajax({
                url: '/turnos',
                type: 'GET',
                dataType: 'json',
                success: function(data) {
                    const tableBody = $('#turnos-table-body');
                    tableBody.empty();

                    if (data.length === 0) {

                        const row = $('<tr></tr>');
                        const noDataCell = $('<td></td>').attr('colspan', '5').addClass('text-center').text('No existen turnos registrados');
                        row.append(noDataCell);
                        tableBody.append(row);
                    } else {

                        data.forEach(turno => {
                            const row = $('<tr></tr>');


                            const idCell = $('<td></td>').text(turno.id);
                            row.append(idCell);


                            const pacienteCell = $('<td></td>').text(turno.paciente ? `${turno.paciente.nombre} ${turno.paciente.apellido}` : 'Sin asignar');
                            row.append(pacienteCell);


                            const odontologoCell = $('<td></td>').text(turno.odontologo ? `${turno.odontologo.nombre} ${turno.odontologo.apellido}` : 'Sin asignar');
                            row.append(odontologoCell);


                            const fechaCell = $('<td></td>').text(turno.fecha);
                            row.append(fechaCell);

                            const horaCell = $('<td></td>').text(turno.hora);
                            row.append(horaCell);

                            const eliminarCell = $('<td></td>');
                            const eliminarBtn = $('<a type="button" class="btn btn-danger"  data-toggle="modal" data-target="#modalEliminar" style="color: #ffffff;"><i class="fa-sharp fa-solid fa-trash"></i></a>').addClass('btn btn-danger');
                            eliminarBtn.on('click', function() {
                                turnoIdToDelete = turno.id;
                                $('#confirmDeleteModal').modal('show');
                            });
                            eliminarCell.append(eliminarBtn);
                            row.append(eliminarCell);


                            tableBody.append(row);
                        });
                    }
                },
                error: function(error) {
                    console.error('Error al obtener los turnos:', error);
                }
            });
 }

    $('#crearTurnoModal').on('show.bs.modal', function (event) {

        $.ajax({
            url: '/turnos/nuevo',
            method: 'GET',
            success: function (data) {

                $('#odontologo').empty();
                $('#paciente').empty();


                $('#odontologo').append('<option value="" disabled selected>Seleccione un odontólogo</option>');
                $('#paciente').append('<option value="" disabled selected>Seleccione un paciente</option>');


                data.odontologos.forEach(function (odontologo) {
                    $('#odontologo').append('<option value="' + odontologo.id + '">' + odontologo.nombre + ' ' + odontologo.apellido + '</option>');
                });


                data.pacientes.forEach(function (paciente) {
                    $('#paciente').append('<option value="' + paciente.id + '">' + paciente.nombre + ' ' + paciente.apellido + '</option>');
                });
            },
            error: function () {
                alert('Error al cargar los datos.');
            }
        });
    });

    $('#formCrearTurno').on('submit', function (event) {
            event.preventDefault();


            const odontologoId = $('#odontologo').val();
            const pacienteId = $('#paciente').val();
            const fechaTurno = $('#fecha').val();
            const horaTurno = $('#hora').val();


            $.ajax({
                url: '/turnos',
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({
                    paciente: { id: pacienteId },
                    odontologo: { id: odontologoId },
                    fecha: fechaTurno,
                    hora: horaTurno
                }),
                success: function (response) {
                    $('#successModal').modal('show');
                    $('#crearTurnoModal').modal('hide');
                    obtenerTurnos();
                },
                error: function () {
                    console.log('Se produjo un error al asignar el turno.');
                }
            });
        });

  function eliminarTurno(id) {
              $.ajax({
                  url: `/turnos/eliminar/${id}`,
                  type: 'DELETE',
                  success: function() {
                      obtenerTurnos();
                  },
                  error: function(error) {
                      console.error('Error al eliminar el turno:', error);
                  }
              });
          }

         $('#confirmDeleteButton').on('click', function() {
             eliminarTurno(turnoIdToDelete);
             $('#modalEliminar').modal('hide');
         });

         $(document).ready(function() {
             obtenerTurnos();
         });