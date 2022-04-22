package br.com.zup.edu.hotel.api.controller;

import br.com.zup.edu.hotel.api.model.Quarto;
import br.com.zup.edu.hotel.api.model.Reserva;
import br.com.zup.edu.hotel.api.repository.QuartoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class ReservarQuartoController {

    private final QuartoRepository repository;

    public ReservarQuartoController(QuartoRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/quartos/{quartoId}/reservas")
    @Transactional
    public ResponseEntity<?> reservar(@PathVariable Long quartoId,
                                      @RequestBody @Valid ReservaRequest request,
                                      UriComponentsBuilder uriComponentsBuilder) {

        Quarto quarto = repository.findById(quartoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Quarto não cadastrado"));

        Reserva reserva = request.paraReserva();

        quarto.reservar(reserva);

        repository.flush();

        URI location = uriComponentsBuilder.path("/quartos/{quartoId}/reservas/{reservaId}")
                .buildAndExpand(quarto.getId(), reserva.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }
}
