package br.com.zup.edu.hotel.api.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate checkIn;

    @Column(nullable = false)
    private LocalDate checkOut;

    @Column(nullable = false)
    private LocalDateTime criadaEm = LocalDateTime.now();

    @ManyToOne(optional = false)
    private Quarto quarto;

    public Reserva(LocalDate checkIn, LocalDate checkOut) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    /**
     * @deprecated Construtor para uso exclusivo do Hibernate.
     */
    @Deprecated
    public Reserva() {
    }

    public Long getId() {
        return id;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }
}
