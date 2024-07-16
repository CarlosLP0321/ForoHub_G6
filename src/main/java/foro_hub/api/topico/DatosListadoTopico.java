package foro_hub.api.topico;

import java.time.LocalDateTime;

public record DatosListadoTopico(Long id, String titulo, String mensaje, LocalDateTime fechaDeCreacion, String estado, String autor, String curso ) {

    public DatosListadoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaDeCreacion(), topico.getEstado(), topico.getAutor(), topico.getCurso());
    }
}


