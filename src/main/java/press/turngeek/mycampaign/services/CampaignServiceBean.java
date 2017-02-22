package press.turngeek.mycampaign.services;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import press.turngeek.mycampaign.model.Campaign;
import press.turngeek.mycampaign.util.TechLog;

@Stateless
public class CampaignServiceBean implements CampaignService {
	@Inject
	@TechLog
	private Logger logger;

	@Inject
	private EntityManager entityManager;

	@Override
	public List<Campaign> getAllCampaigns() {
		logger.info("---------------- Get all campaigns -----");
		TypedQuery<Campaign> query = entityManager.createNamedQuery(Campaign.FIND_ALL, Campaign.class);
		List<Campaign> campaigns = query.getResultList();
		for (Campaign campaign : campaigns) {
			campaign.setAmountDonatedSoFar(getAmountDonatedSoFar(campaign));
		}
		return campaigns;
	}

	@Override
	public void addCampaign(Campaign campaign) {
		entityManager.persist(campaign);
	}

	@Override
	public void deleteCampaign(Campaign campaign) {
		Campaign managedCampaign = entityManager.find(Campaign.class, campaign.getId());
		entityManager.remove(managedCampaign);
	}

	@Override
	public void updateCampaign(Campaign campaign) {
		entityManager.merge(campaign);
	}

	private Double getAmountDonatedSoFar(Campaign campaign) {
		TypedQuery<Double> query = entityManager.createNamedQuery(Campaign.GET_AMOUNT_DONATED_SO_FAR, Double.class);
		query.setParameter("campaign", campaign);
		Double result = query.getSingleResult();
		if (result == null)
			result = 0d;
		return result;
	}

}