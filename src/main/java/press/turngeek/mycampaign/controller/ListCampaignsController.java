package press.turngeek.mycampaign.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.event.Event;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import press.turngeek.mycampaign.data.CampaignProducer;
import press.turngeek.mycampaign.model.Campaign;
import press.turngeek.mycampaign.model.Donation;
import press.turngeek.mycampaign.services.DonationService;
import press.turngeek.mycampaign.util.Events.Deleted;

@ViewScoped
@Named
public class ListCampaignsController implements Serializable {

	private static final long serialVersionUID = 8693277383648025822L;

	@Inject
	private CampaignProducer campaignProducer;

	@Inject
	private DonationService donationService;

	@Inject
	@Deleted
	private Event<Campaign> campaignDeleteEvent;

	private Campaign campaignToDelete;

	public String doAddCampaign() {
		System.out.println("Add Campaign");
		campaignProducer.prepareAddCampaign();
		return Pages.EDIT_CAMPAIGN;

	}

	public String doEditCampaign(Campaign campaign) {
		System.out.println("Edit Campaign " + campaign);
		campaignProducer.prepareEditCampaign(campaign);
		return Pages.EDIT_CAMPAIGN;
	}

	public String doEditDonationForm(Campaign campaign) {
		System.out.println("Edit Donation Form of Campaign " + campaign);
		campaignProducer.setSelectedCampaign(campaign);
		return Pages.EDIT_DONATION_FORM;
	}

	public String doListDonations(Campaign campaign) {
		System.out.println("List Donations of Campaign " + campaign);
		final List<Donation> donations = donationService.getDonationList(campaign.getId());
		campaign.setDonations(donations);
		campaignProducer.setSelectedCampaign(campaign);
		return Pages.LIST_DONATIONS;
	}

	public void doDeleteCampaign(Campaign campaign) {
		this.campaignToDelete = campaign;
		System.out.println("Campaign registered for deletion!");
	}

	public void commitDeleteCampaign() {
		campaignDeleteEvent.fire(campaignToDelete);
	}
}