package dao;

import conexion.Conexion;
import modelo.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public boolean insertar(Usuario u) {
        String sql = "INSERT INTO usuario(nombre, correo, contrasena, tipoUsuario) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.getConexion()) {

            if (con == null) return false;

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCorreo());
            ps.setString(3, u.getContrasena());
            ps.setString(4, u.getTipoUsuario());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error insertar: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Usuario u) {
        String sql = "UPDATE usuario SET nombre=?, correo=?, contrasena=?, tipoUsuario=? WHERE id=?";

        try (Connection con = Conexion.getConexion()) {

            if (con == null) return false;

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCorreo());
            ps.setString(3, u.getContrasena());
            ps.setString(4, u.getTipoUsuario());
            ps.setInt(5, u.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error actualizar: " + e.getMessage());
            return false;
        }
    }

    public Usuario login(String correo, String contrasena) {
        String sql = "SELECT * FROM usuario WHERE correo=? AND contrasena=?";

        try (Connection con = Conexion.getConexion()) {

            if (con == null) return null;

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, contrasena);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setCorreo(rs.getString("correo"));
                u.setTipoUsuario(rs.getString("tipoUsuario"));
                return u;
            }

        } catch (Exception e) {
            System.out.println("Error login: " + e.getMessage());
        }

        return null;
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM usuario WHERE id=?";

        try (Connection con = Conexion.getConexion()) {

            if (con == null) return false;

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error eliminar: " + e.getMessage());
            return false;
        }
    }

    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (Connection con = Conexion.getConexion()) {

            if (con == null) return lista;

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setCorreo(rs.getString("correo"));
                u.setTipoUsuario(rs.getString("tipoUsuario"));
                lista.add(u);
            }

        } catch (Exception e) {
            System.out.println("Error listar: " + e.getMessage());
        }

        return lista;
    }
}