var calc = new calculadora.Calculadora();
if (calc.porcentaje(200, 10) != 20.0) { throw new Exception("10%"); }
if (calc.porcentaje(500, 0) != 0.0) { throw new Exception("0%"); }
if (calc.porcentaje(80, 150) != 120.0) { throw new Exception("150%"); }
try { calc.porcentaje(200, -10); throw new Exception("No exception"); } catch (IllegalArgumentException expected) { }
System.out.println("Percentage checks passed");
