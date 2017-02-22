package press.turngeek.mycampaign.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NamedQueries({ 
	@NamedQuery(name = Campaign.FIND_ALL, query = "SELECT c FROM Campaign c ORDER BY c.name"),
	@NamedQuery(name = Campaign.GET_AMOUNT_DONATED_SO_FAR, query = "SELECT SUM(d.amount) FROM Donation d WHERE d.campaign = :campaign")
})

@Entity
public class Campaign {
	
	public static final String FIND_ALL = "Campaign.findAll";
	public static final String GET_AMOUNT_DONATED_SO_FAR = "Campaign.getAmountDonatedSoFar";

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Size(min = 4, max = 30, message = "{campaign.name.size}")
	private String name;
	
	@NotNull(message = "{campaign.targetAmount.notNull}")
	@DecimalMin(value = "10.00", message = "{campaign.targetAmount.decimalMin}")
	private Double targetAmount;
	
	@NotNull(message = "{campaign.donationMinimum.notNull}")
	@DecimalMin(value = "1.00", message = "{campaign.donationMinimum.decimalMin}")
	private Double donationMinimum;
	
	@Transient
	private Double amountDonatedSoFar;

	@Embedded
	@NotNull
	@AttributeOverrides({ @AttributeOverride(name = "name", column = @Column(name = "accountName")) })
	private Account account;

	@OneToMany(mappedBy = "campaign", cascade = CascadeType.REMOVE)
	private List<Donation> donations;

	public Campaign() {
		account = new Account();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getTargetAmount() {
		return targetAmount;
	}

	public void setTargetAmount(Double targetAmount) {
		this.targetAmount = targetAmount;
	}

	public Double getDonationMinimum() {
		return donationMinimum;
	}

	public void setDonationMinimum(Double donationMinimum) {
		this.donationMinimum = donationMinimum;
	}

	public Double getAmountDonatedSoFar() {
		return amountDonatedSoFar;
	}

	public void setAmountDonatedSoFar(Double amountDonatedSoFar) {
		this.amountDonatedSoFar = amountDonatedSoFar;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public List<Donation> getDonations() {
		return donations;
	}

	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}
}