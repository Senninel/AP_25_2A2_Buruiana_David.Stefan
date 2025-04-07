<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Raportul Colecției de Imagini</title>
</head>
<body>
    <h1>Raportul Colecției de Imagini</h1>
    <ul>
    <#list images as image>
        <li>
            <strong>${image.name}</strong><br>
            Data adăugării: ${image.addedDate}<br>
            Etichete: ${image.tags?join(", ")}<br>
            Cale: ${image.path}
        </li>
    </#list>
    </ul>
</body>
</html>
