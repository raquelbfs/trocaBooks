package conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.dao.CategoriaDAO;
import modelo.dominio.Categoria;

@FacesConverter(forClass=Categoria.class)
public class CategoriaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Integer id = null;
		
		try {
			id = Integer.parseInt(value);
			
			CategoriaDAO dao = new CategoriaDAO();
			Categoria cat = dao.lerPorId(id);
			
			return cat;
			
		} catch (NumberFormatException e) {
			id = null;
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value instanceof Categoria)
		{
			Categoria cat = (Categoria) value;
			return cat.getId().toString();
		}
		
		return null;
	}

}
