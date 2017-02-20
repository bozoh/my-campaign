package press.turngeek.mycampaign.services;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import press.turngeek.mycampaign.model.Campaign;
import press.turngeek.mycampaign.util.TechLog;

@RequestScoped
public class CampaignServiceBean implements CampaignService {
	@Inject
	@TechLog
	private Logger logger;

	@Override
	public List<Campaign> getAllCampaigns() {
		logger.info("---------------- Get all campaigns -----");
		return new LinkedList<Campaign>();
	}

}