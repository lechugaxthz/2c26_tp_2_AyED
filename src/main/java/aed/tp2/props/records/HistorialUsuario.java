package aed.tp2.props.records;

import aed.tp2.props.enums.Transaccion;

public record HistorialUsuario(
    Transaccion transaccion, 
    Double distancia, 
    Double millas_adquiridas, 
    String promo_nombre,
    Double promo_factor,
    Contraparte contraparte
) {}
