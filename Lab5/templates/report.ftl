<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Image Report</title>
</head>
<body>
    <h1>Image Repository Report</h1>
    <ul>
    <#list images as image>
        <li><strong>${image.name}</strong> - ${image.date} - ${image.tags?join(', ')} - ${image.path}</li>
    </#list>
    </ul>
</body>
</html>
