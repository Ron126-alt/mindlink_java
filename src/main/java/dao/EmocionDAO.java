package dao;

import conexion.Conexion;
import modelo.Emocion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmocionDAO {

    public boolean insertar(Emocion e) {
        String sql = "INSERT INTO emocion(tipo, descripcion, usuario_id) VALUES (?, ?, ?)";

        try (Connection con = Conexion.getConexion()) {

            if (con == null) {
                System.out.println("ERROR: conexión NULL");
                return false;
            }

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, e.getTipo());
            ps.setString(2, e.getDescripcion());
            ps.setInt(3, e.getIdUsuario());

            int filas = ps.executeUpdate();

            return filas > 0;

        } catch (Exception ex) {
            System.out.println("Error insertar: " + ex.getMessage());
            return false;
        }
    }

    public List<Emocion> listarPorUsuario(int idUsuario) {
        List<Emocion> lista = new ArrayList<>();

        String sql = "SELECT * FROM emocion WHERE usuario_id=? ORDER BY fecha DESC";

        try (Connection con = Conexion.getConexion()) {

            if (con == null) return lista;

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Emocion e = new Emocion();
                e.setId(rs.getInt("id"));
                e.setTipo(rs.getString("tipo"));
                e.setDescripcion(rs.getString("descripcion"));

                Timestamp fecha = rs.getTimestamp("fecha");
                if (fecha != null) {
                    e.setFecha(fecha.toString());
                }

                lista.add(e);
            }

        } catch (Exception ex) {
            System.out.println("Error listar: " + ex.getMessage());
        }

        return lista;
    }

    // ELIMINAR
    public boolean eliminar(int id) {
        String sql = "DELETE FROM emocion WHERE id=?";

        try (Connection con = Conexion.getConexion()) {

            if (con == null) return false;

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception ex) {
            System.out.println("Error eliminar: " + ex.getMessage());
            return false;
        }
    }

    public Emocion obtenerPorId(int id) {
        String sql = "SELECT * FROM emocion WHERE id=?";
        Emocion e = null;

        try (Connection con = Conexion.getConexion()) {

            if (con == null) return null;

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                e = new Emocion();
                e.setId(rs.getInt("id"));
                e.setTipo(rs.getString("tipo"));
                e.setDescripcion(rs.getString("descripcion"));

                Timestamp fecha = rs.getTimestamp("fecha");
                if (fecha != null) {
                    e.setFecha(fecha.toString());
                }
            }

        } catch (Exception ex) {
            System.out.println("Error obtener: " + ex.getMessage());
        }

        return e;
    }

    public boolean actualizar(Emocion e) {
        String sql = "UPDATE emocion SET tipo=?, descripcion=? WHERE id=?";

        try (Connection con = Conexion.getConexion()) {

            if (con == null) return false;

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, e.getTipo());
            ps.setString(2, e.getDescripcion());
            ps.setInt(3, e.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception ex) {
            System.out.println("Error actualizar: " + ex.getMessage());
            return false;
        }
    }
}