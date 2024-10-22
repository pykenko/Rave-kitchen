package buyers;

public class Casual extends Customer {
	private Integer reward;

	public Casual(String name, Integer patience, String order, Integer reward) {
		super(name, patience, order);
		this.reward = reward;
	}

	public Integer getReward() {
		return reward;
	}

	public void setReward(Integer reward) {
		this.reward = reward;
	}
	
}
