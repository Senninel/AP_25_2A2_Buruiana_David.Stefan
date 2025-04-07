<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Raportul Colectiei de Imagini</title>
</head>
<body>
    <h1>Raportul Colectiei de Imagini</h1>
    <ul>
    <#list images as image>
        <li>
            <strong>${image.name()}</strong><br>
            Data adaugarii: ${image.formattedDate()!"Data necunoscuta"}<br>
            Etichete: ${image.tags()?join(", ")!"Fara etichete"}<br>
            Cale: ${image.path()!"Cale necunoscuta"}
        </li>
    </#list>
    </ul>
</body>
</html>
