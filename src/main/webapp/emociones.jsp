<%@ page import="java.util.*, modelo.Emocion"%>
<%
@SuppressWarnings("unchecked")
List<Emocion> lista = (List<Emocion>) request.getAttribute("lista");

Emocion emo = (Emocion) request.getAttribute("emocion");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Emociones</title>

<style>
body {
	font-family: Arial;
	background: linear-gradient(135deg, #74ebd5, #9face6);
}

.container {
	width: 80%;
	margin: auto;
	background: white;
	padding: 20px;
	border-radius: 10px;
}

button {
	background: #6c63ff;
	color: white;
	padding: 10px;
	border: none;
	border-radius: 5px;
}
</style>

</head>

<body>

	<div class="container">

		<h2><%= (emo != null) ? "Editar emoción" : "Registrar emoción" %></h2>

		<!-- 🔥 CORREGIDO -->
		<form action="EmocionServlet" method="post">

			<input type="hidden" name="id"
				value="<%= (emo != null) ? emo.getId() : "" %>"> <select
				name="tipo">
				<option
					<%= (emo != null && emo.getTipo().equals("Feliz")) ? "selected" : "" %>>Feliz</option>
				<option
					<%= (emo != null && emo.getTipo().equals("Triste")) ? "selected" : "" %>>Triste</option>
				<option
					<%= (emo != null && emo.getTipo().equals("Ansioso")) ? "selected" : "" %>>Ansioso</option>
				<option
					<%= (emo != null && emo.getTipo().equals("Enojado")) ? "selected" : "" %>>Enojado</option>
			</select> <input type="text" name="descripcion"
				value="<%= (emo != null) ? emo.getDescripcion() : "" %>"
				placeholder="¿Cómo te sientes?" required>

			<button type="submit">
				<%= (emo != null) ? "Actualizar" : "Guardar" %>
			</button>

		</form>

		<h2>Mis emociones</h2>

		<table border="1">
			<tr>
				<th>Tipo</th>
				<th>Descripción</th>
				<th>Fecha</th>
				<th>Acciones</th>
			</tr>

			<% if (lista != null) {
for (Emocion e : lista) { %>

			<tr>
				<td><%= e.getTipo() %></td>
				<td><%= e.getDescripcion() %></td>
				<td><%= e.getFecha() %></td>
				<td><a href="EmocionServlet?accion=editar&id=<%= e.getId() %>">Editar</a>
					| <a href="EmocionServlet?accion=eliminar&id=<%= e.getId() %>">Eliminar</a>
				</td>
			</tr>

			<% }} %>

		</table>

	</div>

</body>
</html>