package conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.dao.UsuarioDAO;
import modelo.dominio.Usuario;

@FacesConverter(forClass=Usuario.class)
public class UsuarioConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Integer id = null;
		
		try {
			id = Integer.parseInt(value);
			
			UsuarioDAO dao = new UsuarioDAO();
			Usuario usu = dao.lerPorId(id);
			
			return usu;
			
		} catch (NumberFormatException e) {
			id = null;
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value instanceof Usuario)
		{
			Usuario usu = (Usuario) value;
			return usu.getId().toString();
		}
		
		return null;
	}

}
