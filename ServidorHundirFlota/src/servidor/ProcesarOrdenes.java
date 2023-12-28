package servidor;

public class ProcesarOrdenes {
	
	 enum Tipo {
	        TIPO1, TIPO2, TIPO3, TIPO4, TIPO5, TIPO6, TIPO7, TIPO8, DESCONOCIDO
	    }
	
	public Object procesarOrden(Object orden) {
		
		switch (obtenerTipo(orden)) {
		case TIPO1:
			return null;	
		case TIPO2:
			return null;
		case TIPO3:
			return null;
		case TIPO4:
			return null;
		case TIPO5:
			return null;
		case TIPO6:
			return null;
		case TIPO7:
			return null;
		case TIPO8:
			return null;
		case DESCONOCIDO:
			return null;
		}
		return null;
		
	}

	private Tipo obtenerTipo(Object orden) {
		if (orden instanceof Tipo1) {
            return Tipo.TIPO1;
        } else if (orden instanceof Tipo2) {
            return Tipo.TIPO2;
        } else if (orden instanceof Tipo3) {
            return Tipo.TIPO3;
        } else if (orden instanceof Tipo4) {
            return Tipo.TIPO4;
        } else if (orden instanceof Tipo5) {
            return Tipo.TIPO5;
        } else if (orden instanceof Tipo6) {
            return Tipo.TIPO6;
        } else if (orden instanceof Tipo7) {
            return Tipo.TIPO7;
        } else if (orden instanceof Tipo8) {
            return Tipo.TIPO8;
        } else {
            return Tipo.DESCONOCIDO;
        }
	}
	
	static class Tipo1 {}
    static class Tipo2 {}
    static class Tipo3 {}
    static class Tipo4 {}
    static class Tipo5 {}
    static class Tipo6 {}
    static class Tipo7 {}
    static class Tipo8 {}

	

}
