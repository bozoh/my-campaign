package press.turngeek.mycampaign.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named
public class ListDonationsController implements Serializable {
	private static final long serialVersionUID = 437878972432L;

	public String doOk() {
		return Pages.LIST_CAMPAIGNS;
	}
}