<!DOCTYPE html>
<html>
<head><title>Image Report</title></head>
<body>
<h1>Images</h1>
<ul>
<#list images as img>
  <li>${img.name} - ${img.date} - ${img.tags?join(", ")} - ${img.path}</li>
</#list>
</ul>
</body>
</html>
