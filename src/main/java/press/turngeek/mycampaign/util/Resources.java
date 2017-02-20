package press.turngeek.mycampaign.util;

import java.util.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;

@Dependent
public class Resources {
  
  @Produces
  @DomainLog
  public Logger produceLog(InjectionPoint injectionPoint) {
    return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName(), "messages");
  }
  
  @Produces
  @TechLog
  public Logger techLog(InjectionPoint injectionPoint) {
    return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName(), "messages");
  }

  @Produces
  @RequestScoped
  public FacesContext produceFacesContext() {
    return FacesContext.getCurrentInstance();
  }

}