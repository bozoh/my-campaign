package press.turngeek.mycampaign.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import press.turngeek.mycampaign.data.CampaignProducer;
import press.turngeek.mycampaign.model.Campaign;

@SessionScoped
@Named
public class ListCampaignsController implements Serializable {

	private static final long serialVersionUID = 8693277383648025822L;
	
	@Inject
	private CampaignProducer campaignProducer;

	public String doAddCampaign() {
		System.out.println("Add Campaign");
		campaignProducer.prepareAddCampaign();
		return Pages.EDIT_CAMPAIGN;
		
	}
	public String doEditCampaign(Campaign campaign) {
		System.out.println("Edit Campaign "+campaign);
		campaignProducer.prepareEditCampaign(campaign);
		return Pages.EDIT_CAMPAIGN;
	}
	public String doEditDonationForm(Campaign campaign) {
		System.out.println("Edit Donation Form of Campaign "+campaign);
		return Pages.EDIT_DONATION_FORM;
	}
	public String doListDonations(Campaign campaign) {
		System.out.println("List Donations of Campaign "+campaign);
		campaignProducer.setSelectedCampaign(campaign);
		return Pages.LIST_DONATIONS;
	}
	public void doDeleteCampaign(Campaign campaign) {
		System.out.println("Deletion not implemented, yet!");
	}
}