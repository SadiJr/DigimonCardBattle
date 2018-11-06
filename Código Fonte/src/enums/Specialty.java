package enums;
public enum Specialty {
	FIRE("Fogo"),
	GRASS("Grama");
	
	private String description;
	
	Specialty(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}