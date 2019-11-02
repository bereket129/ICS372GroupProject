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
	public boolean removeMember(String memberId) {
		return members.remove(findMemberByID(memberId));

	}


	/**
	 * This finds a member in the list
	 * @param memberName The name of the member that is being looked for.
	 * @return Returns list of members that match the name.
	 */
	public Iterator<Member> findMember(String memberName) {
		ArrayList<Member> arrayList = new ArrayList<>();
		//TODO
		for (Member member : members) {
			if (member.getName().equals(memberName)) {
				arrayList.add(member);
			}
		}
		return arrayList.iterator();
	}

	/**
	 * This finds a member in the list
	 * @param memberId The id of the member that is being looked for.
	 * @return Returns the member being looked for.
	 */
	public Member findMemberByID(String memberId) {
		//TODO
		for (Member member : members) {
			if (member.getMemberId().equals(memberId)) {
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
