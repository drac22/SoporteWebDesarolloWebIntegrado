package pruebas.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pruebas.demo.model.Solicitud;
import pruebas.demo.model.tipos.TipoSolicitud;
import pruebas.demo.repository.TipoSolicitudRepository;

@Service
public class TipoSolicitudService {
    @Autowired
    private TipoSolicitudRepository tipoSolicitudRepository;

    public List<TipoSolicitud> obtenerTipoSolicitudes(){
        return tipoSolicitudRepository.findAll();
    }

}
