package press.turngeek.mycampaign.controller;

import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import press.turngeek.mycampaign.model.Donation;
import press.turngeek.mycampaign.model.Donation.Status;
import press.turngeek.mycampaign.services.DonationService;
import press.turngeek.mycampaign.util.DomainLog;

@ViewScoped
@Named
public class DonateMoneyController implements Serializable {
	private static final long serialVersionUID = 5493038842003809106L;
	private String textColor = "000000";
	private String bgColor = "ffffff";
	private Long campaignId;
	private Donation donation;
	
	@Inject
	private DonationService donationService;
	
	@Inject
	private FacesContext facesContext;

	@Inject @DomainLog
	private Logger logger;

	@PostConstruct
	public void init() {
		this.donation = new Donation();
	}
	public Long getCampaignId() {
		return campaignId;
	}
	public void setCampaignId(Long campaignId) {
		this.campaignId = campaignId;
	}
	public Donation getDonation() {
		return donation;
	}
	public void setDonation(Donation donation) {
		this.donation = donation;
	}
	public String getTextColor() {
		return textColor;
	}
	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}
	public String getBgColor() {
		return bgColor;
	}
	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}
	public String doDonation() {
		getDonation().setStatus(Status.IN_PROCESS);
		donationService.addDonation(getCampaignId(), getDonation());
		
		logger.log(Level.INFO, "log.donateMoney.thank_you", new Object[]{getDonation().getDonorName(), getDonation().getAmount()});
		    final ResourceBundle resourceBundle = facesContext.getApplication().getResourceBundle(facesContext, "msg");
		    final String msg = resourceBundle.getString("donateMoney.thank_you");
		    facesContext.addMessage(
		        null,
		        new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
		    init();
		    return Pages.DONATE_MONEY;
	}
}