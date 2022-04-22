package br.com.zup.edu.hotel.api.controller;

import br.com.zup.edu.hotel.api.model.Quarto;
import br.com.zup.edu.hotel.api.model.Reserva;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ReservaRequest {

    @NotNull
    @FutureOrPresent
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate checkIn;

    @NotNull
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate checkOut;

    @NotBlank
    private String reservadoPara;

    public ReservaRequest(LocalDate checkIn, LocalDate checkOut, String reservadoPara) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.reservadoPara = reservadoPara;
    }

    public ReservaRequest() {
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public String getReservadoPara() {
        return reservadoPara;
    }

    public Reserva paraReserva(Quarto quarto) {
        if (quarto.isReservado()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "O quarto já está reservado");
        }

        return new Reserva(checkIn,checkOut,reservadoPara,quarto);
    }
}
