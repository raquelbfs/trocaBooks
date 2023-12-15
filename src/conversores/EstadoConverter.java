package conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.dao.EstadoDAO;
import modelo.dominio.Estado;

@FacesConverter(forClass=Estado.class)
public class EstadoConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Integer id = null;
		
		try {
			id = Integer.parseInt(value);
			
			EstadoDAO dao = new EstadoDAO();
			Estado stat = dao.lerPorId(id);
			
			return stat;
			
		} catch (NumberFormatException e) {
			id = null;
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value instanceof Estado)
		{
			Estado stat = (Estado) value;
			return stat.getId().toString();
		}
		
		return null;
	}

}
