package press.turngeek.mycampaign.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import press.turngeek.mycampaign.data.CampaignProducer;
import press.turngeek.mycampaign.model.Campaign;
import press.turngeek.mycampaign.util.Events.Added;
import press.turngeek.mycampaign.util.Events.Updated;

@SessionScoped
@Named
public class EditCampaignController implements Serializable {
	private static final long serialVersionUID = 2815796004558360299L;

	@Inject
	@Added
	private Event<Campaign> campaignAddEvent;

	@Inject
	@Updated
	private Event<Campaign> campaignUpdateEvent;

	@Inject
	private CampaignProducer campaignProducer;

	public String doSave() {
		Event<Campaign> saveEvent = null;
		if (campaignProducer.isAddMode()) {
			saveEvent = campaignAddEvent;
		} else {
			saveEvent = campaignUpdateEvent;
		}

		saveEvent.fire(campaignProducer.getSelectedCampaign());
		return Pages.LIST_CAMPAIGNS;
	}

	public String doCancel() {
		return Pages.LIST_CAMPAIGNS;
	}
}
