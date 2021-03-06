package br.com.zup.edu.hotel.api.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal valorDiaria;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoQuarto tipoQuarto;

    @Column(nullable = false)
    private boolean reservado = false;

    @Version
    private int versao;

    @OneToMany(mappedBy = "quarto", cascade = CascadeType.PERSIST)
    private List<Reserva> reservas = new ArrayList<>();

    public Quarto(String descricao, BigDecimal valorDiaria, TipoQuarto tipoQuarto) {
        this.descricao = descricao;
        this.valorDiaria = valorDiaria;
        this.tipoQuarto = tipoQuarto;
    }

    /**
     * @deprecated Construtor para uso exclusivo do Hibernate.
     */
    @Deprecated
    public Quarto() {
    }

    public Long getId() {
        return id;
    }

    public boolean isReservado() {
        return reservado;
    }

    public void adicionar(Reserva novaReserva) {
        this.reservas.add(novaReserva);
        this.reservado = true;
    }
}
