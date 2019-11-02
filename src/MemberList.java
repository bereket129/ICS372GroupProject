import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author brentwindham
 * This implements the collection of members.
 */
public class MemberList implements Serializable {
	private LinkedList<Member> members = new LinkedList<Member>();
	private static MemberList memberList;

	/**
	 * Supports the singleton pattern
	 *
	 * @return the singleton object
	 */
	public static MemberList getInstance() {
		//TODO
		if (memberList == null) {
			return (memberList = new MemberList());
		} else {
			return memberList;
		}	}

	/**
	 * Private constructor for the memberlist object
	 */
	private MemberList() {
		//TODO

	}

	/**
	 * This adds a member to the list
	 * @param member The member being added.
	 * @return Returns true if added.
	 */
	public boolean addMember(Member member) {
		//TODO
		if (members.add(member)) {
			return true;
		}
		return false;
	}

	/**
	 * This removes a member from the list.
	 * @param memberId The id of the member being removed
	 * @return Returns true if removed
	 */
	public boolean removeMember(int memberId) {
		//TODO
		if (members.remove(findMember(memberId))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This finds a member in the list
	 * @param memberName The name of the member that is being looked for.
	 * @return Returns the member being looked for.
	 */
	public Iterator findMember(String memberName) {
		//TODO
		ArrayList<Member> arrayOfMembers = new ArrayList<>();
		for (Member member : members) {
			if (member.getName().equals(memberName)) {
				arrayOfMembers.add(member);
			}
		}
		return arrayOfMembers.iterator();
	}



	/**
	 * This finds a member in the list
	 * @param memberId The id of the member that is being looked for.
	 * @return Returns the member being looked for.
	 */
	public Member findMember(int memberId) {
		for (Member member : members) {
			if (member.getMemberId()==memberId) {
				return member;
			}
		}
		return null;
	}

	/**
	 * This supplies an iterator object of the list.
	 * @return Returns an iterator object for the memberlist.
	 */
	public Iterator getMembers() {
		//TODO
		return members.iterator();
	}
}
