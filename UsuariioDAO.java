package com.miapp.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    public void insertarUsuario(String nombre, String contrasena) {
        Connection conexion = ConexionDB.conectar();
        if (conexion == null) {
            System.out.println("No se pudo conectar a la base de datos.");
            return;
        }

        String sql = "INSERT INTO USUARIO (USERNAME, USERPASSWORD) VALUES (?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, nombre);
            statement.setString(2, contrasena);
            statement.executeUpdate();
            System.out.println("Usuario insertado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion(conexion);
        }
    }

    public void mostrarUsuarios() {
        Connection conexion = ConexionDB.conectar();
        if (conexion == null) {
            System.out.println("No se pudo conectar a la base de datos.");
            return;
        }

        String sql = "SELECT * FROM USUARIO";
        try (PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                System.out.println(rs.getInt("userid") + ": " + rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion(conexion);
        }
    }
}
