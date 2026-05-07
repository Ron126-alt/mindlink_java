<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registro</title>

<style>
body {
    font-family: Arial;
    background: linear-gradient(135deg, #74ebd5, #9face6);
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.container {
    background: white;
    padding: 30px;
    border-radius: 10px;
    width: 320px;
    text-align: center;
    box-shadow: 0px 4px 15px rgba(0,0,0,0.2);
}

input, select {
    width: 90%;
    padding: 8px;
    margin: 10px 0;
}

button {
    background: #6c63ff;
    color: white;
    padding: 10px;
    border: none;
    width: 100%;
    border-radius: 5px;
}

a {
    display: block;
    margin-top: 10px;
}
</style>
</head>

<body>

<div class="container">

<h2>Crear Usuario</h2>

<form action="UsuarioServlet" method="post">

<input type="text" name="nombre" placeholder="Nombre" required>

<input type="email" name="correo" placeholder="Correo" required>

<input type="password" name="contrasena" placeholder="Contraseña" required>

<select name="tipoUsuario">
    <option value="usuario">Usuario</option>
    <option value="admin">Administrador</option>
</select>

<button type="submit">Registrar</button>

</form>

<a href="login.jsp">Volver al login</a>

</div>

</body>
</html>