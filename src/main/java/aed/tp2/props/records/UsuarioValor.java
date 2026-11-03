package aed.tp2.props.records;

import java.util.ArrayList;

public record UsuarioValor(
    ArrayList<HistorialUsuario> historial, 
    float millas_disponibles, 
    float millas_acumuladas, 
    float millas_canjeadas, 
    UsuarioValor par_exclusivo, 
    boolean exclusion_valida,
    int idx
) {}
