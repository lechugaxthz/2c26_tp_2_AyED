package aed.tp2.props.records;

import aed.tp2.props.sub_class.UsuarioValor;

public record Contraparte(
    UsuarioValor usuario, 
    int idx_transaccion_historial
) {}
