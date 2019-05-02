package contract.contracterr;

public class InvariantError extends ContractError {

	public InvariantError(String message) {
		super("Invariant failed" + message);
	}
}
