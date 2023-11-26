public class Reservation {
    private String clienteNome;
    private String horarioReserva;
    private int numeroPessoas;

    public Reservation(String clienteNome, String horarioReserva, int numeroPessoas) {
        this.clienteNome = clienteNome;
        this.horarioReserva = horarioReserva;
        this.numeroPessoas = numeroPessoas;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public String getHorarioReserva() {
        return horarioReserva;
    }

    public int getNumeroPessoas() {
        return numeroPessoas;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public void setHorarioReserva(String horarioReserva) {
        this.horarioReserva = horarioReserva;
    }

    public void setNumeroPessoas(int numeroPessoas) {
        this.numeroPessoas = numeroPessoas;
    }
}
