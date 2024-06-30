package tareaspruebas.junit;

import java.time.LocalDate;

public class Reserva {
    private Sala sala;
    private Usuario usuario;
    private LocalDate fecha;

    public Reserva(Sala sala, Usuario usuario, LocalDate fecha) {
        this.sala = sala;
        this.usuario = usuario;
        this.fecha = fecha;
    }

    // Getters
    public Sala getSala() {
        return sala;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    // Setters
    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}


