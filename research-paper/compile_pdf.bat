@echo off
echo =======================================================================
echo           SmartPrep Research Paper HTML to PDF Compiler
echo =======================================================================
echo.
echo Compiling SmartPrep_IEEE_Research_Paper.html to SmartPrep_IEEE_Research_Paper.pdf...
echo.

powershell -NoProfile -ExecutionPolicy Bypass -Command "$html = '%~dp0SmartPrep_IEEE_Research_Paper.html'; $pdf = '%~dp0SmartPrep_IEEE_Research_Paper.pdf'; $paths = @('C:\Program Files (x86)\Microsoft\Edge\Application\msedge.exe', 'C:\Program Files\Microsoft\Edge\Application\msedge.exe', 'C:\Program Files\Google\Chrome\Application\chrome.exe', 'C:\Program Files (x86)\Google\Chrome\Application\chrome.exe'); $browser = $paths | Where-Object { Test-Path $_ } | Select-Object -First 1; if ($null -ne $browser) { Write-Host 'Found browser at:' $browser; Start-Process $browser -ArgumentList '--headless', '--disable-gpu', '--no-sandbox', '--no-pdf-header-footer', ('--print-to-pdf=' + $pdf), $html -Wait; } else { Write-Host 'Browser not found in standard paths. Trying system PATH...'; Start-Process 'msedge' -ArgumentList '--headless', '--disable-gpu', '--no-sandbox', '--no-pdf-header-footer', ('--print-to-pdf=' + $pdf), $html -Wait -ErrorAction SilentlyContinue; } if (Test-Path $pdf) { Write-Host 'SUCCESS: Generated SmartPrep_IEEE_Research_Paper.pdf successfully!' -ForegroundColor Green; } else { Write-Host 'ERROR: PDF generation failed.' -ForegroundColor Red; }"

echo.
pause
