package press.turngeek.mycampaign.services;

import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;

import press.turngeek.mycampaign.model.Account;
import press.turngeek.mycampaign.model.Campaign;
import press.turngeek.mycampaign.model.Donation;

@RequestScoped
@Alternative
public class MockCampaignServiceBean implements CampaignService {
	@Override
    public List<Campaign> getAllCampaigns() {
		Donation donation1 = new Donation();
		donation1.setDonorName("John Smith");
		donation1.setAmount(20d);
		donation1.setReceiptRequested(true);
		donation1.setStatus(Donation.Status.TRANSFERRED);
		donation1.setAccount(new Account(donation1.getDonorName(), 
			"XXX Bank","DE44876543210000123456"));
		Donation donation2 = new Donation();
		donation2.setDonorName("Peter Jones");
		donation2.setAmount(30d);
		donation2.setReceiptRequested(false);
		donation2.setStatus(Donation.Status.IN_PROCESS);
		donation2.setAccount(new Account(donation2.getDonorName(), 
			"YYY Bank","DE44864275310000654321"));
		List<Donation> spenden = new LinkedList<>();
		spenden.add(donation1);
		spenden.add(donation2);
		Campaign campaign1 = new Campaign();
		campaign1.setName("Shirts for the U19 Team");
		campaign1.setTargetAmount(1000d);
		campaign1.setAmountDonatedSoFar(258d);
		campaign1.setDonationMinimum(20d);
		campaign1.setId(1L);
		campaign1.setAccount(new Account("Robert Cook", "ABC Bank",
			"DE44123456780100200300"));
		campaign1.setDonations(spenden);
		Campaign campaign2 = new Campaign();
		campaign2.setName("Wheelchair for Maria");
		campaign2.setTargetAmount(2500d);
		campaign2.setAmountDonatedSoFar(742d);
		campaign2.setDonationMinimum(25d);
		campaign2.setId(2L);
		campaign2.setAccount(campaign1.getAccount());
		campaign2.setDonations(spenden);
		List<Campaign> ret = new LinkedList<>();
		ret.add(campaign1);
		ret.add(campaign2);
		return ret;
	}
}
