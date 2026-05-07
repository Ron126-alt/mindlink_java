<%@ page import="java.util.List, modelo.Usuario" %>
<%
    @SuppressWarnings("unchecked")
    List<Usuario> lista = (List<Usuario>) request.getAttribute("lista");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Usuarios</title>

    <style>
        body {
            font-family: Arial;
            background-color: #e6f2ff;
            text-align: center;
        }

        table {
            margin: auto;
            border-collapse: collapse;
            width: 80%;
            background: white;
        }

        th, td {
            padding: 10px;
            border: 1px solid #ccc;
        }

        th {
            background-color: #3399ff;
            color: white;
        }

        a {
            text-decoration: none;
            color: red;
            font-weight: bold;
        }

        .btn {
            display: inline-block;
            margin-top: 15px;
            padding: 10px 20px;
            background-color: #3399ff;
            color: white;
            border-radius: 5px;
        }
    </style>
</head>

<body>

    <h2>Usuarios registrados</h2>

    <table>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Correo</th>
            <th>Tipo</th>
            <th>Acciones</th>
        </tr>

        <%
            if (lista != null && !lista.isEmpty()) {
                for (Usuario u : lista) {
        %>

        <tr>
            <td><%= u.getId() %></td>
            <td><%= u.getNombre() %></td>
            <td><%= u.getCorreo() %></td>
            <td><%= u.getTipoUsuario() %></td>
            <td>
                <a href="UsuarioServlet?accion=eliminar&id=<%= u.getId() %>">Eliminar</a>
            </td>
        </tr>

        <%
                }
            } else {
        %>

        <tr>
            <td colspan="5">No hay usuarios registrados</td>
        </tr>

        <%
            }
        %>

    </table>

    <br>

    <a href="formulario.jsp" class="btn">Registrar nuevo usuario</a>

</body>
</html>