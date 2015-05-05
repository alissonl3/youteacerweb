package br.com.youteacher.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.youteacher.banco.dao.VideoDAO;
import br.com.youteacherweb.entidades.Video;

@FacesConverter(forClass=Video.class, value="videoConverter")
public class VideoConverter implements Converter {

	VideoDAO dao = new VideoDAO();
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent uiComponent, String codigoString) {
		if(codigoString !=null && codigoString.trim().length()>0){
			Integer codigo = Integer.parseInt(codigoString);
			Video video = dao.pesquisarPorID(codigo);
			return video;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent uiComponent, Object videoObjeto) {
	
			if(videoObjeto instanceof Video){
				Video video = (Video) videoObjeto;
				return video.getId().toString();
			}
		
        return "";
	}

}
