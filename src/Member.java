import java.util.GregorianCalendar;

/**
 *
 * @author brentwindham
 *This is the implementation of a member in the grocery store system.
 */
public class Member {

	private String memberId;
	private String name;
	private int phoneNumber;
	private double feePaid;
	private String address;
	private GregorianCalendar joinDate;
	private int counter = 1;

	/**
	 * Constructor for member.
	 * @param name The name of the member.
	 * @param address The address of the member.
	 * @param phoneNumber The phone number of the member.
	 * @param feePaid The fee paid by the member.
	 */
	public Member(String name, String address, int phoneNumber, double feePaid) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.feePaid = feePaid;
		this.joinDate = new GregorianCalendar();
		this.memberId = "m" + counter;
		counter++;
	}

	/**
	 * Getter for MemberId
	 * @return memberId
	 */
	public String getMemberId() {
		return memberId;
	}

	/**
	 * Setter for memberId
	 * @param memberId The memberId of the member.
	 * @return Returns true if completed.
	 */
	public boolean setMemberId(String memberId) {
		this.memberId = memberId;
		return true;
	}

	/**
	 * Getter for name
	 * @return Returns the name of the member
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for name
	 * @param name The name of the member
	 * @return Returns true if set
	 */
	public boolean setName(String name) {
		this.name = name;
		return true;
	}

	/**
	 * Getter for phone number
	 * @return Returns the members phone number
	 */
	public int getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Setter for phone number
	 * @param phoneNumber The new phone number
	 * @return Returns true if set
	 */
	public boolean setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
		return true;
	}

	/**
	 * Getter for fee paid
	 * @return Returns the fee paid
	 */
	public double getFeePaid() {
		return feePaid;
	}

	/**
	 * Setter for fee paid
	 * @param feePaid The fee paid
	 * @return Returns true if set
	 */
	public boolean setFeePaid(double feePaid) {
		this.feePaid = feePaid;
		return true;
	}

	/**
	 * Getter for address
	 * @return Returns the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Setter for address
	 * @param address The new address
	 * @return Returns true if set
	 */
	public boolean setAddress(String address) {
		this.address = address;
		return true;
	}

	/**
	 * getter for the join date
	 * @return Returns the join date
	 */
	public GregorianCalendar getJoinDate() {
		return joinDate;
	}

	/**
	 * Setter for the join date
	 * @param joinDate The new join date
	 * @return Returns true if set
	 */
	public boolean setJoinDate(GregorianCalendar joinDate) {
		this.joinDate = joinDate;
		return true;
	}




}
