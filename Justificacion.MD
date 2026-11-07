## Invariante de Representacion en Castellano

- Cada vez que se genere una transferencia se debe verificar si el usuario destino ya es un usuario transferidor exclsivo, en caso de que ya lo sea el usaurio destino pierde la exclusividad, y el usuario origen debe tambien ser marcado con el Flag esEclusivo en false, a su vez el Usuario con el que tenia la exclusividad el Usuario Destino tambien debe perder dicha exclusividad. 
- Todos los usaurios nuevos sin transferencias son posibles paresTransferidoresExclusivos, por lo que todos tendran esEclusivo = True
- La propiedad millasAcumuladas no debe ser modificada en caso del usuario recibir una transferencia
- La propiedad del usuario millasCanjeadas debe ser modificada tanto cuando el usuario canjea millas como cuando realiza una transferencia
- Al acumular millas (realizar un vuelo) se debe verificar si las millas acumuladas del usuario en cuestion es mayor a las millas acumuladas del usuario guardado en la variable elMasAcumulador, en caso de ser cierto se debe modificar la referencia tomando el nuevo usuario caso contrario no se debe modificar la variable elMasAcumulador (Dicho de otra manera, el valor grabado en la variable elMasAcumulador debe cumpplir que representa el usuario con el mayor numero de millas acumuladas una vez se inicia y se termina la accion de acumular)
- Al realizar la operacion canjearMillas se debe verificar si el total de millas canjeadas por el usuario en cuestion es mayor al del usaurio grabado en la variable elMasCanjeador en caso de que lo sea debe modificarse la variable con la referencia de este usuario, caso contrario no se debe modificar la variable (Dicho de otra forma la el valor a guardar en la variable elMasCanjeador debe representar siempre el usario con mayor nro de millas canjeadas)


# Estrucutras
Implementacion de Diccionario con un ABB
Usuarios : ABB <ID, Usuario> (O(log U)) 
Usuario: {id, historial, millasDisponibles, millasCanjeadas, millasAcumuladas ,indice, esEclusivo, parExclusivo}
HeapMax (por millasDisponibles)








