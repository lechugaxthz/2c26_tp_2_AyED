# TP2 — Sistema de Fidelización AirMiles


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

## Justificación de complejidades

U = cantidad de usuarios, |L| = largo del nombre de una promoción, N = tamaño del ranking pedido, P = cantidad de pares exclusivos vigentes.

### Cómo llegamos a cada estructura

Empezamos por las operaciones con restricciones más fuertes y fuimos eligiendo una estructura para cada una.

**1. Casi todas las operaciones sobre usuarios: O(log U) en promedio, O(U) en el peor caso.**
Registrar, canjear, transferir y consultar empiezan buscando a un usuario por id. Elegimos un **Diccionario Log** (`<id, UsuarioValor>`) implementado sobre un ABB.
- Buscar e insertar recorre un camino de la raíz a un nodo: O(altura). La altura es O(log U) en promedio y O(U) si el árbol se degenera en lista, que es justo lo que pide el enunciado.
- No usamos una lista (buscar sería O(U) también en promedio), ni un arreglo indexado por id (los ids son arbitrarios). Un árbol balanceado (AVL) también serviría, pero no hace falta ya que el enunciado acepta O(U) en el peor caso.
- Una vez encontrado el usuario, todo lo que hay que leer o modificar de él (millas, exclusividad, handles) es un campo de `UsuarioValor`: O(1). Agregar una transacción a su historial es O(1) así como leer la última transacción.

**2. Promociones: O(|L|), sin importar cuántas haya.**
Elegimos un **Trie** (Diccionario Digital `nombre → PromoValor`).
- Buscar o insertar avanza un nodo por cada letra del nombre. En cada nodo, el hijo de la letra siguiente se accede en O(1), porque los hijos están en un arreglo de tamaño fijo indexado por la letra. Total: |L| pasos de O(1) = **O(|L|)**.
- Un Diccionario Log por nombre no alcanza: cada comparación de nombres cuesta O(|L|) y harían falta O(log P) de ellas, lo que da O(|L| · log P), que depende de la cantidad de promociones.

**3. elMasAcumulador y elMasCanjeador: O(1).**
Guardamos **dos referencias**: al usuario con más millas acumuladas y al que más canjeó (canjes + transferencias enviadas).
- Las millas acumuladas y las canjeadas **nunca disminuyen**. Cuando un usuario cambia, el nuevo máximo es el anterior o ese usuario. Alcanza con comparar sus millas contra las del usuario referenciado y, si son mayores, reemplazamos la referencia: O(1).
- No hace falta un heap para esto: un heap nos sirve cuando los valores también bajan.

**4. rankingMillas: O(N log U).**
Las millas **disponibles** sí suben y bajan, así que necesitamos una estructura que se pueda reordenar. Elegimos un **MaxHeap por millas disponibles, con handles**.
- Cada usuario guarda un handle que conoce su posición en el heap. Sin handle, encontrar al usuario en el heap costaría O(U).
- **Reubicar** a un usuario: desde la posición que indica su handle, si sus millas disponibles subieron se lo intercambia con su padre mientras sea mayor (sube); si bajaron, se lo intercambia con su hijo mayor mientras sea menor (baja). Como mucho recorre la altura del heap: **O(log U)**. Cada intercambio actualiza la posición guardada en los dos handles.
- **rankingMillas(N):** extrae el máximo N veces (cada extracción pone el último elemento en la raíz y lo hace bajar: O(log U)) y después reinserta los N extraídos (cada uno entra al final y sube: O(log U)). Son 2N operaciones de O(log U): **O(N log U)**. Si N > U, se toma N = U.

**5. paresTransferidoresExclusivos: O(U).**
En vez de recorrer todos los usuarios en cada consulta preferimos mantener los pares al día en cada transferencia, para que la consulta solo tenga que copiar la lista.
- Cada usuario guarda su estado de exclusividad: `par_exclusivo` (con quién transfirió) y `exclusion_valida` (si sigue siendo exclusivo). Actualizarlo en una transferencia es O(1).
- Los pares vigentes están en una **lista doblemente enlazada**, y los dos usuarios del par guardan un handle a su nodo. Agregar al final es O(1). Borrar con el handle también es O(1), porque el nodo conoce su anterior y siguiente.

**Categoría: O(1).** No se guarda: se calcula a partir de las millas acumuladas comparándolas con los umbrales 10.000, 50.000 y 1.000.000. Son tres comparaciones: O(1).

### Costo de cada operación

| Operación | Costo | Cómo se hace |
|---|---|---|
| crearSistema | O(1) | Crea el Diccionario Log, el Trie, el heap y la lista vacíos. |
| registrarUsuario | O(log U) prom. / O(U) peor | Inserta al usuario en el Diccionario Log. Lo agrega al final del heap con 0 millas: como 0 es el mínimo posible, no hace falta reubicarlo (O(1)). |
| registrarUsuarioConReferidor | O(log U) prom. / O(U) peor | Lo anterior, más buscar al referidor en el Diccionario Log. Suma 500 millas a cada uno (O(1)), reubica a los dos en el heap hacia arriba (O(log U)) y compara a los dos contra el máximo acumulador (O(1)). |
| acumularMillas | O(log U + \|L\|) prom. / O(U + \|L\|) peor | Busca la promo en el Trie (O(\|L\|)). Si está activa y le queda tope, usa su factor y descuenta del tope; si no, usa factor 1. Busca al usuario en el Diccionario Log, calcula millas = distancia × factor de categoría × factor de promo (O(1)), lo reubica hacia arriba en el heap (O(log U)) y lo compara contra el máximo acumulador (O(1)). |
| canjearMillas | O(log U) prom. / O(U) peor | Busca al usuario en el Diccionario Log, resta las millas (O(1)), lo reubica hacia abajo en el heap (O(log U)) y lo compara contra el máximo canjeador (O(1)). |
| transferirMillas | O(log U) prom. / O(U) peor | Busca a los dos usuarios en el Diccionario Log y actualiza sus millas (O(1)). Reubica al origen hacia abajo y al destino hacia arriba en el heap (O(log U)). Compara al origen contra el máximo canjeador (O(1)). Actualiza la exclusividad de los dos mirando la contraparte de la última transacción de cada historial (O(1)): si pasan a ser par, agrega el par al final de la lista y guarda el handle en ambos; si se rompe un par, borra su nodo con el handle (O(1)). |
| consultarSaldo / consultarCategoria | O(log U) prom. / O(U) peor | Busca al usuario en el Diccionario Log y lee sus millas disponibles, o calcula su categoría (O(1)). |
| iniciarPromocion / iniciarPromocionConTope | O(\|L\|) | Inserta la promo en el Trie. |
| finalizarPromocion | O(\|L\|) | Busca la promo en el Trie y la marca como inactiva (O(1)). |
| elMasAcumulador / elMasCanjeador | O(1) | Devuelve la referencia guardada. |
| rankingMillas | O(N log U) | Extrae el máximo del heap N veces: cada extracción pone el último elemento en la raíz y lo hace bajar (O(log U)). Después reinserta los N extraídos: cada uno entra al final y sube (O(log U)). Si N > U, se toma N = U. Como el arreglo del heap vuelve al tamaño que tenía, reinsertar no lo agranda y cada reinserción es O(log U) en el peor caso. Total: 2N · O(log U). |
| paresTransferidoresExclusivos | O(U) | Copia la lista de pares: O(P). Como cada usuario está en a lo sumo un par, P ≤ U/2. |
