package buyers;

public class Vip extends Customer {
	private Integer reward;

	public Vip(String name, Integer patience, String order, Integer reward) {
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
