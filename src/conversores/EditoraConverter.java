package conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.dao.EditoraDAO;
import modelo.dominio.Editora;

@FacesConverter(forClass=Editora.class)
public class EditoraConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Integer id = null;
		
		try {
			id = Integer.parseInt(value);
			
			EditoraDAO dao = new EditoraDAO();
			Editora edi = dao.lerPorId(id);
			
			return edi;
			
		} catch (NumberFormatException e) {
			id = null;
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value instanceof Editora)
		{
			Editora edi = (Editora) value;
			return edi.getId().toString();
		}
		
		return null;
	}

}
