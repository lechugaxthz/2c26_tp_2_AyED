package aed.tp2;

import aed.tp2.props.Promos;
import aed.tp2.props.Usuarios;
import aed.tp2.props.interfaces.SistemaAirMiles;

public class AirMiles implements SistemaAirMiles {
    /* COMPLETAR */

    private Usuarios usuarios;
    private Promos promos;

    public AirMiles() {
        this.usuarios = new Usuarios();
        this.promos = new Promos();
    }

    public void registrarUsuario(int id) {
        usuarios.agregarUsuario(id);
    }

    public void registrarUsuarioConReferidor(int id, int ref) {
        usuarios.agregarConReferido(id, ref);
    }

    public void acumularMillas(int id, double distancia, String nombrePromo) {
        var promo = promos.getValor(nombrePromo);
        if (promo != null) {
            boolean condicional_factor = promo.activa && promo.tiene_tope && promo.tope > 0d;
            boolean condicional_sin_factor = promo.activa && !promo.tiene_tope;
            double millas_adquiridas = usuarios.agregarVuelo(
                    id,
                    distancia,
                    (condicional_factor || condicional_sin_factor) ? promo.factor : 1d,
                    nombrePromo);

            if (condicional_factor) {
                promo.tope -= millas_adquiridas;
                promos.setpTriesValor(nombrePromo, promo.factor, promo.tope, promo.tiene_tope);
            }
        } else {
            usuarios.agregarVuelo(
                    id,
                    distancia,
                    1d,
                    nombrePromo);

        }
    }

    public void canjearMillas(int id, double cantidad) {
        usuarios.canjeaMillas(id, cantidad);
    }

    public void transferirMillas(int origen, int destino, double cantidad) {
        usuarios.transferir(origen, destino, cantidad);
    }

    public double consultarSaldo(int id) {
        return usuarios.getMillasDisponibles(id);
    }

    public String consultarCategoria(int id) {
        return usuarios.getCategoria(id);
    }

    public int[] rankingMillas(int n) {
        return usuarios.rankMillasDisponibles(n);
    }

    public void iniciarPromocion(String nombre, double factor) {
        promos.setpTriesValor(nombre, factor, 0d, false);
    }

    public void iniciarPromocionConTope(String nombre, double factor, double tope) {
        promos.setpTriesValor(nombre, factor, tope, true);
    }

    public void finalizarPromocion(String nombre) {
        promos.desactivarPromo(nombre);
    }

    public int elMasAcumulador() {
        return usuarios.usuarioMaxAcumulaciones();
    }

    public int elMasCanjeador() {
        return usuarios.usuarioMaxPromos();
    }

    public int[][] paresTransferidoresExclusivos() {
        return usuarios.paresExclusivos();

    }
}
