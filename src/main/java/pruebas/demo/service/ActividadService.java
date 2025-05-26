package pruebas.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import pruebas.demo.model.Actividad;
import pruebas.demo.model.Asignacion;
import pruebas.demo.model.DTO.ActividadDTO;
import pruebas.demo.model.DTO.ActividadDTOResponse;
import pruebas.demo.repository.ActividadRepository;
import pruebas.demo.repository.AsignacionRepository;

@Service
public class ActividadService {

    private final ActividadRepository actividadRepository;
    private final AsignacionRepository asignacionRepository;

    public ActividadService(ActividadRepository actividadRepository, AsignacionRepository asignacionRepository) {
        this.actividadRepository = actividadRepository;
        this.asignacionRepository = asignacionRepository;
    }

    public List<ActividadDTOResponse> mostrarActividades() {
        return actividadRepository.findAll().stream()
                .map(a -> new ActividadDTOResponse(
                        a.getIdActividad(),
                        a.getFecha(),
                        a.getDetalleActividad(),
                        a.getMinutosTrabajados(),
                        a.getAsignacion().getSolicitud().getIdSolicitud(),
                        a.getAsignacion().getColaborador().getNombreColaborador()))
                .collect(Collectors.toList());
    }

    public List<Actividad> obtenerActividadesPorAsignacion(Long idAsignacion) {
        return actividadRepository.findByIdAsignacion(idAsignacion);
    }

    public Actividad registrarActividad(ActividadDTO actividadDTO) {
        Asignacion asignacion = asignacionRepository.findById(actividadDTO.getIdAsignacion())
                .orElseThrow(() -> new RuntimeException("Asignacion no encontrada"));

        if (actividadDTO.getMinutosTrabajados() <= 0) {
            throw new IllegalArgumentException("Los minutos trabajados deben ser positivos.");
        }
        Actividad nuevaActividad = new Actividad();
        nuevaActividad.setFecha(LocalDate.now());
        nuevaActividad.setMinutosTrabajados(actividadDTO.getMinutosTrabajados());
        nuevaActividad.setDetalleActividad(actividadDTO.getDetalleActividad());
        nuevaActividad.setAsignacion(asignacion);

        return actividadRepository.save(nuevaActividad);
    }
}
