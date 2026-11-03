package aed.tp2.props.records;

import aed.tp2.props.enums.Transaccion;

public record HistorialUsuario(
    Transaccion transaccion, 
    float distancia, 
    float millas_adquiridas, 
    float promo_factor, 
    Contraparte contraparte
) {}
