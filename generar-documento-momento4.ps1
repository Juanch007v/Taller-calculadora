Add-Type -AssemblyName System.IO.Compression
Add-Type -AssemblyName System.IO.Compression.FileSystem

$outputPath = Join-Path $PSScriptRoot "Momento4_Cazadores_de_Bugs.docx"
if (Test-Path $outputPath) {
    Remove-Item $outputPath -Force
}

function Escape-Xml([string] $value) {
    return [System.Security.SecurityElement]::Escape($value)
}

function Paragraph([string] $text, [string] $style = "Normal") {
    $escaped = Escape-Xml $text
    return "<w:p><w:pPr><w:pStyle w:val=""$style""/></w:pPr><w:r><w:t xml:space=""preserve"">$escaped</w:t></w:r></w:p>"
}

function TableCell([string] $text, [bool] $header = $false) {
    $escaped = Escape-Xml $text
    $fill = if ($header) { "<w:shd w:fill=""1F4E78""/>" } else { "" }
    $color = if ($header) { "<w:color w:val=""FFFFFF""/>" } else { "" }
    return "<w:tc><w:tcPr>$fill</w:tcPr><w:p><w:r><w:rPr>$color<w:b w:val=""$header""/></w:rPr><w:t xml:space=""preserve"">$escaped</w:t></w:r></w:p></w:tc>"
}

function TableRow([string[]] $cells, [bool] $header = $false) {
    $row = "<w:tr>"
    foreach ($cell in $cells) {
        $row += TableCell $cell $header
    }
    return $row + "</w:tr>"
}

$table = "<w:tbl><w:tblPr><w:tblW w:w=""0"" w:type=""auto""/><w:tblBorders><w:top w:val=""single"" w:sz=""4""/><w:left w:val=""single"" w:sz=""4""/><w:bottom w:val=""single"" w:sz=""4""/><w:right w:val=""single"" w:sz=""4""/><w:insideH w:val=""single"" w:sz=""4""/><w:insideV w:val=""single"" w:sz=""4""/></w:tblBorders></w:tblPr><w:tblGrid><w:gridCol w:w=""500""/><w:gridCol w:w=""1300""/><w:gridCol w:w=""2100""/><w:gridCol w:w=""1900""/><w:gridCol w:w=""1900""/><w:gridCol w:w=""3500""/></w:tblGrid>"
$table += TableRow @("#", "Método", "Entrada que lo revela", "Esperado", "Obtenido", "¿Cuál creemos que es el error?") $true
$table += TableRow @("1", "restar", "restar(10, 5)", "5", "-5", "Se invirtió el orden de los operandos: se calcula b - a en vez de a - b.")
$table += TableRow @("2", "multiplicar", "multiplicar(-2, -3)", "6", "-6", "Cuando ambos números son negativos se cambia incorrectamente el signo del producto.")
$table += TableRow @("3", "dividir", "dividir(10, 0)", "ArithmeticException: No se puede dividir entre cero", "No se lanzó excepción; se obtuvo Infinity", "Falta validar el divisor antes de dividir entre cero.")
$table += TableRow @("4", "esPar", "esPar(-3)", "false", "true", "La condición no reconoce correctamente el residuo -1 de un impar negativo.")
$table += TableRow @("5", "factorial", "factorial(5) y factorial(20)", "120 y 2432902008176640000", "24 y 121645100408832000", "El límite del ciclo excluye n; se multiplica hasta n - 1 y falta el último factor.")
$table += TableRow @("6", "promedio", "promedio(new double[]{}) y promedio(null)", "IllegalArgumentException: Se requiere al menos un número", "NaN para arreglo vacío y NullPointerException para null", "No se valida si el arreglo es null o está vacío antes de recorrerlo y dividir.")
$table += "</w:tbl>"

$body = ""
$body += Paragraph "Momento 4. Cazadores de bugs" "Title"
$body += Paragraph "Proyecto: taller-junit-calculadora" "Subtitle"
$body += Paragraph "Procedimiento" "Heading1"
$body += Paragraph "Se copió CalculadoraTest.java como CalculadoraConErroresTest.java y se cambió el objeto probado por CalculadoraConErrores. Las fallas se identificaron a partir de las aserciones rojas y de sus valores esperado y obtenido."
$body += Paragraph "Resultado de la ejecución: 10 pruebas ejecutadas, 6 pruebas/familias de comportamiento fallidas y 4 correctas."
$body += Paragraph "Tabla 3. Registro de errores encontrados" "Heading1"
$body += $table
$body += Paragraph "Reflexión: Para pensar" "Heading1"
$body += Paragraph "Si solo se hubieran probado números positivos y casos normales, no se habrían detectado con seguridad tres fallos: multiplicar con dos números negativos, dividir entre cero y promedio con un arreglo vacío o null. El error de esPar sí puede aparecer con un impar positivo como 7, mientras que los fallos de restar y factorial también se revelan con entradas positivas adecuadas."
$body += Paragraph "Conclusión" "Heading1"
$body += Paragraph "Las pruebas de valores normales no son suficientes. Los valores negativos, cero, límites y entradas inválidas fueron los que permitieron distinguir el comportamiento correcto de la implementación defectuosa."

$documentXml = @"
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<w:document xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main"><w:body>$body<w:sectPr><w:pgSz w:w="11906" w:h="16838"/><w:pgMar w:top="1000" w:right="900" w:bottom="1000" w:left="900"/></w:sectPr></w:body></w:document>
"@

$stylesXml = @"
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<w:styles xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main"><w:style w:type="paragraph" w:default="1" w:styleId="Normal"><w:name w:val="Normal"/><w:rPr><w:sz w:val="22"/><w:fonts w:ascii="Aptos" w:hAnsi="Aptos"/></w:rPr></w:style><w:style w:type="paragraph" w:styleId="Title"><w:name w:val="Title"/><w:basedOn w:val="Normal"/><w:rPr><w:b/><w:sz w:val="34"/><w:color w:val="1F4E78"/></w:rPr></w:style><w:style w:type="paragraph" w:styleId="Subtitle"><w:name w:val="Subtitle"/><w:basedOn w:val="Normal"/><w:rPr><w:italic/><w:sz w:val="24"/></w:rPr></w:style><w:style w:type="paragraph" w:styleId="Heading1"><w:name w:val="Heading 1"/><w:basedOn w:val="Normal"/><w:rPr><w:b/><w:sz w:val="28"/><w:color w:val="1F4E78"/></w:rPr></w:style></w:styles>
"@

$contentTypesXml = @"
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<Types xmlns="http://schemas.openxmlformats.org/package/2006/content-types"><Default Extension="rels" ContentType="application/vnd.openxmlformats-package.relationships+xml"/><Default Extension="xml" ContentType="application/xml"/><Override PartName="/word/document.xml" ContentType="application/vnd.openxmlformats-officedocument.wordprocessingml.document.main+xml"/><Override PartName="/word/styles.xml" ContentType="application/vnd.openxmlformats-officedocument.wordprocessingml.styles+xml"/></Types>
"@

$rootRelsXml = @"
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<Relationships xmlns="http://schemas.openxmlformats.org/package/2006/relationships"><Relationship Id="rId1" Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument" Target="word/document.xml"/></Relationships>
"@

$documentRelsXml = @"
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<Relationships xmlns="http://schemas.openxmlformats.org/package/2006/relationships"><Relationship Id="rId1" Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/styles" Target="styles.xml"/></Relationships>
"@

$stream = [System.IO.File]::Open($outputPath, [System.IO.FileMode]::Create)
$archive = New-Object System.IO.Compression.ZipArchive($stream, [System.IO.Compression.ZipArchiveMode]::Create)
try {
    $entries = @{
        "[Content_Types].xml" = $contentTypesXml
        "_rels/.rels" = $rootRelsXml
        "word/document.xml" = $documentXml
        "word/styles.xml" = $stylesXml
        "word/_rels/document.xml.rels" = $documentRelsXml
    }
    foreach ($entryData in $entries.GetEnumerator()) {
        $entry = $archive.CreateEntry($entryData.Key)
        $writer = New-Object System.IO.StreamWriter($entry.Open(), (New-Object System.Text.UTF8Encoding($false)))
        try {
            $writer.Write($entryData.Value)
        } finally {
            $writer.Dispose()
        }
    }
} finally {
    $archive.Dispose()
    $stream.Dispose()
}

Write-Output "Documento creado: $outputPath"