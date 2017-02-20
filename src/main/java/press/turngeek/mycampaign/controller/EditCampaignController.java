package press.turngeek.mycampaign.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import press.turngeek.mycampaign.data.CampaignProducer;
import press.turngeek.mycampaign.model.Campaign;
import press.turngeek.mycampaign.util.Events.Added;

@SessionScoped
@Named
public class EditCampaignController implements Serializable {
	private static final long serialVersionUID = 2815796004558360299L;
	
	@Inject
	@Added
	private Event<Campaign> campaignAddEvent;

	
	@Inject
	private CampaignProducer campaignProducer;

	public String doSave() {
		if (campaignProducer.isAddMode()) {
			campaignAddEvent.fire(campaignProducer.getSelectedCampaign());
		}
		return Pages.LIST_CAMPAIGNS;
	}
	public String doCancel() {
		return Pages.LIST_CAMPAIGNS;
	}
}

