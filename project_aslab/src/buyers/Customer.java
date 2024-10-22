package buyers;

public abstract class Customer {
	private String name;
	private Integer patience;
	private String order;
	//Patience = (random from 1-20) + 15
	

	public Customer(String name, Integer patience, String order) {
		super();
		this.name = name;
		this.patience = patience;
		this.order = order;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getPatience() {
		return patience;
	}


	public void setPatience(Integer patience) {
		this.patience = patience;
	}


	public String getOrder() {
		return order;
	}


	public void setOrder(String order) {
		this.order = order;
	}

	
	

	
}
