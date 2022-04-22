package br.com.zup.edu.hotel.api.controller;

import br.com.zup.edu.hotel.api.model.Reserva;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
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

    public ReservaRequest(LocalDate checkIn, LocalDate checkOut) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public ReservaRequest() {
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public Reserva paraReserva() {
        return new Reserva(checkIn,checkOut);
    }
}
