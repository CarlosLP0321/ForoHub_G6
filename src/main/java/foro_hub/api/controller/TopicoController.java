package foro_hub.api.controller;

import jakarta.validation.Valid;
import foro_hub.api.topico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public void registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico) {
        topicoRepository.save(new Topico(datosRegistroTopico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalleTopico(@PathVariable Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isPresent()) {
            return ResponseEntity.ok(topicoOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro no encontrado");
        }
    }

    @GetMapping("/listar")
    public Page<DatosListadoTopico> listadoTopicos(@PageableDefault(size = 10, sort = "fechaDeCreacion", direction = Sort.Direction.ASC) Pageable paginacion) {
        return topicoRepository.findAll(paginacion).map(DatosListadoTopico::new);
        //return topicoRepository.findByActivoTrue(paginacion).map(DatosListadoTopico::new);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico, @PathVariable Long id) {
        ResponseEntity<?> response = detalleTopico(id);
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return response;
        }

        Topico topico = topicoRepository.getReferenceById(id);
        topico.actualizarDatos(datosActualizarTopico);
        return ResponseEntity.ok("Topico actualizado exitosamente");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> eliminarTopico(@PathVariable Long id) {
        ResponseEntity<?> response = detalleTopico(id);
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return response;
        }

        topicoRepository.deleteById(id);
        return ResponseEntity.ok("Topico eliminado exitosamente");
    }
}
