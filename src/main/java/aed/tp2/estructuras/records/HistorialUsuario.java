package aed.tp2.estructuras.records;

import aed.tp2.estructuras.enums.Transaccion;

public record HistorialUsuario(Transaccion transaccion, float distancia, float millas_adquiridas, float promo_factor, int contraparte) {}
