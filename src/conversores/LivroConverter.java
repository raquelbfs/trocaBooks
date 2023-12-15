package conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.dao.LivroDAO;
import modelo.dominio.Livro;

@FacesConverter(forClass=Livro.class)
public class LivroConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Integer id = null;
		
		try {
			id = Integer.parseInt(value);
			
			LivroDAO dao = new LivroDAO();
			Livro liv = dao.lerPorId(id);
			
			return liv;
			
		} catch (NumberFormatException e) {
			id = null;
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value instanceof Livro)
		{
			Livro liv = (Livro) value;
			return liv.getCodigo().toString();
		}
		
		return null;
	}

}
