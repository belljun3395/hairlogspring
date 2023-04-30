package jongjun.hairlog.data.enums;

public enum HurtRate {
	H("많이 상함"),
	M("보통 상함"),
	L("적게 상함"),
	;

	private String description;

	HurtRate(String description) {
		this.description = description;
	}
}
