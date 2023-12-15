package conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.dao.SituacaoDAO;
import modelo.dominio.Situacao;

@FacesConverter(forClass=Situacao.class)
public class SituacaoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Integer id = null;
		
		try {
			id = Integer.parseInt(value);
			
			SituacaoDAO dao = new SituacaoDAO();
			Situacao sit = dao.lerPorId(id);
			
			return sit;
			
		} catch (NumberFormatException e) {
			id = null;
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value instanceof Situacao)
		{
			Situacao sit = (Situacao) value;
			return sit.getId().toString();
		}
		
		return null;
	}

}
