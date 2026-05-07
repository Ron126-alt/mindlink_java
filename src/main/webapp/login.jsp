<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>

<style>
body {
    font-family: Arial;
    background: #e6f2ff;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.container {
    background: white;
    padding: 30px;
    border-radius: 10px;
    width: 300px;
    text-align: center;
}

input {
    width: 90%;
    padding: 8px;
    margin: 10px 0;
}

button {
    background: #3399ff;
    color: white;
    padding: 10px;
    border: none;
    width: 100%;
}

a {
    display: block;
    margin-top: 10px;
}
</style>
</head>

<body>

<div class="container">

<h2>Login</h2>

<form action="LoginServlet" method="post">
    <input type="email" name="correo" placeholder="Correo" required>
    <input type="password" name="contrasena" placeholder="Contraseña" required>
    <button type="submit">Ingresar</button>
</form>

<p style="color:red;">
${error}
</p>

<a href="formulario.jsp">Crear cuenta</a>

</div>

</body>
</html>