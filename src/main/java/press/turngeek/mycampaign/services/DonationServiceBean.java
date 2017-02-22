package press.turngeek.mycampaign.services;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import press.turngeek.mycampaign.model.Campaign;
import press.turngeek.mycampaign.model.Donation;
import press.turngeek.mycampaign.util.TechLog;

@Stateless
public class DonationServiceBean implements DonationService {

	@Inject
	private EntityManager entityManager;

	@Inject
	@TechLog
	private Logger logger;

	@Override
	public List<Donation> getDonationList(Long campaignId) {
		Campaign managedCampaign = entityManager.find(Campaign.class, campaignId);
		List<Donation> donations = managedCampaign.getDonations();
	    donations.size();
		return donations;
	}

	@Override
	public void addDonation(Long campaignId, Donation donation) {
		Campaign managedCampaign = entityManager.find(Campaign.class, campaignId);
		donation.setCampaign(managedCampaign);
		entityManager.persist(donation);
	}

}
