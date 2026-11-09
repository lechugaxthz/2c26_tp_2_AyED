# Invariante de Representación — AirMiles

## Estructura de representación

```
Módulo AirMilesImpl implementa SistemaAirMiles <
   var usuarios: Usuarios
   var promos:   Promos
>

Usuarios ES struct <
   abbUsuarioPorID:            DiccionarioLog<Z, UsuarioValor>
   heapUsuarioMaxMillas:       Heap<Z, UsuarioValor>          /* max-heap por millas disponibles, con handles */
   usuarioMaxMillasAcumuladas: UsuarioValor                        /* elMasAcumulador */
   usuarioMaxPromos:           UsuarioValor                        /* elMasCanjeador */
   listaPares:                 ListaEnlazada<Z, Vector<Z>>      /* pares exclusivos vigentes */
>

UsuarioValor ES struct <
   id: Z
   historial: Vector<HistorialUsuario>
   millas_disponibles: R
   millas_acumuladas:  R
   millas_canjeadas:   R
   par_exclusivo: UsuarioValor        /* única contraparte de transferencias (o null) */
   exclusion_valida: bool
   hUsuario: HeapUsuarioHandler       /* handle a su posición en el heap */
   hPar: HandleLista                  /* handle a su nodo en listaPares (o null) */
>

Promos ES struct <
   pTries: DiccionarioDigital<string, PromoValor>
>

PromoValor ES struct <
   factor: R
   tope: R
   tiene_tope: bool
   activa: bool
>
```

## Invariante de representación

**Consistencia de referencias** (todo `UsuarioValor` referenciado desde cualquier parte de la estructura es un usuario de `abbUsuarioPorID`)
- Para cada clave `k` de `abbUsuarioPorID`, el usuario asociado tiene `id = k`.
- Toda clave de `abbUsuarioPorID` es mayor a 0.
- Los elementos de `heapUsuarioMaxMillas` son exactamente los usuarios de `abbUsuarioPorID`.
- El handle `hUsuario` de cada usuario referencia a ese mismo usuario.
- Si el diccionario de usuarios por ID no está vacío, `usuarioMaxMillasAcumuladas` y `usuarioMaxPromos` existen (distinto a null) y son usuarios del diccionario de usuarios por ID; si está vacío, ninguno de los dos existe (son null).
- Si `u.par_exclusivo` existe (distinto a null), es un usuario del diccionario de usuarios por ID distinto de `u`.
- Toda contraparte del historial de `u` es un usuario del diccionario de usuarios por ID distinto de `u`.
- Para cada transacción `h` del historial de `u_1` que tiene contraparte `u_2` (EMISION, RECEPCION, REFERIDO), `h.contraparte.idx_transaccion_historial` es una posición válida del historial de `u_2`. La transacción en esa posición tiene a `u_1` como contraparte, es del tipo complementario (EMISION ↔ RECEPCION, REFERIDO ↔ REFERIDO), tiene la misma cantidad de millas y su `idx_transaccion_historial` es la posición de `h` en el historial de `u_1`.
- Los ids de cada elemento `⟨u_1, u_2⟩` de `listaPares` son claves de `abbUsuarioPorID`.

**usuarios.heapUsuarioMaxMillas**
- La prioridad de cada usuario en el heap son sus millas disponibles.

**Cada usuario `u` del diccionario de usuarios por ID**
- `u.millas_acumuladas` es igual a la suma de las millas de los vuelos y bonos por referido de su historial.
- `u.millas_canjeadas` es igual a la suma de las millas de los canjes y transferencias emitidas de su historial.
- `u.millas_disponibles` es igual a sus millas acumuladas, más las recibidas por transferencia, menos las canjeadas, y no es negativa.
- Las millas de cada transacción del historial de `u` respetan su tipo: en un VUELO, la distancia es positiva y las millas no son negativas; en un canje (PROMO), una EMISION o una RECEPCION, las millas son positivas; en un REFERIDO, las millas son 500.
- El factor de promoción de cada VUELO del historial de `u` es mayor o igual a 1, y el de cualquier otra transacción es 1.

**usuarios.usuarioMaxMillasAcumuladas y usuarios.usuarioMaxPromos**
- Ningún usuario del diccionario de usuarios por ID tiene más millas acumuladas que `usuarioMaxMillasAcumuladas`.
- Ningún usuario del diccionario de usuarios por ID tiene más millas canjeadas que `usuarioMaxPromos`.

**Pares exclusivos (par_exclusivo, exclusion_valida, hPar)**
- Si `u.par_exclusivo` existe (distinto a null), `u.exclusion_valida` es verdadero y `u.par_exclusivo` es la única contraparte de transferencias de `u`.
- Si `u.exclusion_valida` es falso, `u.par_exclusivo` y `u.hPar` no existen (son null).
- Si `u_1.par_exclusivo = u_2`, entonces `u_2.par_exclusivo = u_1` o `u_2.exclusion_valida` es falso. Es decir, nunca se apunta a un usuario que tiene otro par.
- `u` no tiene contrapartes de transferencias si y solo si `u.exclusion_valida` es verdadero y `u.par_exclusivo` no existe (es null).
- Dos usuarios `u_1` y `u_2` son par si y solo si cada uno tiene al otro como `par_exclusivo` y ambos tienen `exclusion_valida` verdadero.
- Dos usuarios `u_1` y `u_2` son par si y solo si son exclusivos: la única contraparte de transferencias de `u_1` es `u_2`, y la única contraparte de transferencias de `u_2` es `u_1`.

**usuarios.listaPares**
- Contiene exactamente un elemento `⟨u_1, u_2⟩` por cada par de usuarios `u_1`, `u_2`, sin repetidos (ni `⟨u_1, u_2⟩` dos veces, ni `⟨u_1, u_2⟩` y `⟨u_2, u_1⟩`).
- Para cada elemento `⟨u_1, u_2⟩` de `listaPares`, `u_1` y `u_2` son distintos, son par y son exclusivos (cada uno es la única contraparte de transferencias del otro).
- El `hPar` de un usuario existe (distinto a null) si y solo si el usuario forma un par; en ese caso, ambos usuarios del par comparten el mismo handle, que apunta al elemento del par en la lista.
- Si el `hPar` de `u_1` existe (distinto a null), `u_1` y `u_2 = u_1.par_exclusivo` son exclusivos.

**promos.pTries**
- Para cada promoción, el factor es mayor o igual a 1.
- Si la promoción no tiene tope, su tope es 0.
- Si la promoción tiene tope, su tope no es negativo.