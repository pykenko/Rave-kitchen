package foods;

public class Burger extends Food {

	private boolean bun;
	private boolean bacon;
	private boolean lettuce;
	private boolean tomato;
	private boolean onion;
	private boolean pickle;
	private boolean cheese;
	private boolean patty;
	private boolean bun2;
	
	public Burger(String name, boolean bun, boolean bacon, boolean lettuce, boolean tomato, boolean onion,
			boolean pickle, boolean cheese, boolean patty, boolean bun2) {
		super(name);
		this.bun = bun;
		this.bacon = bacon;
		this.lettuce = lettuce;
		this.tomato = tomato;
		this.onion = onion;
		this.pickle = pickle;
		this.cheese = cheese;
		this.patty = patty;
		this.bun2 = bun2;
	}
	
	public boolean isBun() {
		return bun;
	}
	public void setBun(boolean bun) {
		this.bun = bun;
	}
	public boolean isBacon() {
		return bacon;
	}
	public void setBacon(boolean bacon) {
		this.bacon = bacon;
	}
	public boolean isLettuce() {
		return lettuce;
	}
	public void setLettuce(boolean lettuce) {
		this.lettuce = lettuce;
	}
	public boolean isTomato() {
		return tomato;
	}
	public void setTomato(boolean tomato) {
		this.tomato = tomato;
	}
	public boolean isOnion() {
		return onion;
	}
	public void setOnion(boolean onion) {
		this.onion = onion;
	}
	public boolean isPickle() {
		return pickle;
	}
	public void setPickle(boolean pickle) {
		this.pickle = pickle;
	}
	public boolean isCheese() {
		return cheese;
	}
	public void setCheese(boolean cheese) {
		this.cheese = cheese;
	}
	public boolean isPatty() {
		return patty;
	}
	public void setPatty(boolean patty) {
		this.patty = patty;
	}
	public boolean isBun2() {
		return bun2;
	}
	public void setBun2(boolean bun2) {
		this.bun2 = bun2;
	}

	
}
