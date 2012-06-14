package inmobiliaria.entities;

public class Cliente extends Persona {
	private static final long serialVersionUID = 1L;
	public static final int EMPRESA = 1;
    public static final int PERSONA = 2;
    private int tipo;
	private String calle;
    private String calleNro;
    private String callePiso;

    public Cliente() {
    }
    
    public String toString()
    {
        return getNombre() + " " + getApellido() + 
                " - Nro de comprador: " + 
                " - Tipo: " + getTipoAsString();
    }
    
    public String getTipoAsString()
    {
        return (getTipo() == 1) ? "Empresa" : "Persona";
    }
    public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCalleNro() {
		return calleNro;
	}

	public void setCalleNro(String calleNro) {
		this.calleNro = calleNro;
	}

	public String getCallePiso() {
		return callePiso;
	}

	public void setCallePiso(String callePiso) {
		this.callePiso = callePiso;
	}

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
