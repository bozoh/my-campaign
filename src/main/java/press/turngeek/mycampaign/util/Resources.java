package press.turngeek.mycampaign.util;

import java.util.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Dependent
public class Resources {
	
	@Produces
	@PersistenceContext
	private EntityManager em;

	@Produces
	@DomainLog
	public Logger produceLog(InjectionPoint injectionPoint) {
		//final ResourceBundle resourceBundle = facesContext.getApplication().getResourceBundle(facesContext, "msg");
		//Caused by: java.util.MissingResourceException: Can't find messages bundle
		//return Logger.getLogger("ProduceLogger: "+injectionPoint.getMember().getDeclaringClass().getName(), "messages");
		return Logger.getLogger("ProduceLogger: "+injectionPoint.getMember().getDeclaringClass().getName());
	}

	@Produces
	@TechLog
	public Logger techLog(InjectionPoint injectionPoint) {
		//Caused by: java.util.MissingResourceException: Can't find messages bundle
		//return Logger.getLogger("TechLogger: "+injectionPoint.getMember().getDeclaringClass().getName(), "messages");
		return Logger.getLogger("TechLogger: "+injectionPoint.getMember().getDeclaringClass().getName());
		
	}

	@Produces
	@RequestScoped
	public FacesContext produceFacesContext() {
		return FacesContext.getCurrentInstance();
	}

}