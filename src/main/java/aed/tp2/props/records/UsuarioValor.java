package aed.tp2.props.records;

import java.util.ArrayList;

import aed.tp2.props.enums.Categoria;

public record UsuarioValor(
    int id, 
    ArrayList<HistorialUsuario> historial, 
    float millas_disponibles, 
    float millas_acumuladas, 
    float millas_canjeadas, 
    UsuarioValor par_exclusivo, 
    boolean exclusion_valida,
    int idx,
    Categoria categoria
) {}
