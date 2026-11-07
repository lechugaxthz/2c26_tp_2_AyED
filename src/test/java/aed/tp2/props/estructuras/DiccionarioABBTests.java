package aed.tp2.props.estructuras;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import aed.tp2.props.enums.Categoria;
import aed.tp2.props.sub_class.UsuarioValor;

import static org.junit.jupiter.api.Assertions.*;

public class DiccionarioABBTests {

    private UsuarioValor nuevoUsuario(float millas_iniciales) {
        return new UsuarioValor(
            new ArrayList<>(),
            millas_iniciales,
            millas_iniciales,
            0,
            null,
            true,
            0,
            Categoria.BRONCE
        );
    }

    @Test
    void guardarYObtenerUnUsuario() {
        DiccionarioABB<Integer, UsuarioValor> usuarios = new DiccionarioABB<>();
        UsuarioValor usr = nuevoUsuario(0);

        usuarios.insertar(5, usr);

        assertEquals(usr, usuarios.obtener(5));
    }

    @Test
    void guardarYRecuperarVariosUsuarios() {
        DiccionarioABB<Integer, UsuarioValor> usuarios = new DiccionarioABB<>();
        UsuarioValor u5 = nuevoUsuario(0);
        UsuarioValor u3 = nuevoUsuario(500);
        UsuarioValor u8 = nuevoUsuario(0);

        usuarios.insertar(5, u5);
        usuarios.insertar(3, u3);
        usuarios.insertar(8, u8);

        assertEquals(u5, usuarios.obtener(5));
        assertEquals(u3, usuarios.obtener(3));
        assertEquals(u8, usuarios.obtener(8));
    }

    @Test
    void usuarioInexistenteEsNulo() {
        DiccionarioABB<Integer, UsuarioValor> usuarios = new DiccionarioABB<>();
        assertNull(usuarios.obtener(1));
    }

    @Test
    void modificarUsuarioObtenidoSeReflejaEnElDiccionario() {
        DiccionarioABB<Integer, UsuarioValor> usuarios = new DiccionarioABB<>();
        usuarios.insertar(5, nuevoUsuario(0));

        float nuevasMillas = usuarios.obtener(5).getMillasDisponibles() + 250;

        usuarios.obtener(5).setMillasDisponibles(nuevasMillas);
        usuarios.obtener(5).setMillasAcumuladas(nuevasMillas);

        assertEquals(250, usuarios.obtener(5).getMillasDisponibles());
        assertEquals(250, usuarios.obtener(5).getMillasAcumuladas());
    }
}
