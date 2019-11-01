import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;

public class TransactionList implements Serializable {
	private LinkedList<Transaction> transactions = new LinkedList<Transaction>();
	private static TransactionList transactionList;

	private TransactionList() {
	}

	public static TransactionList getInstance() {
		if(transactionList == null) {
			transactionList = new TransactionList();
		}

		return transactionList;
	}

	public boolean addTransaction(Transaction transaction) {
		return transactions.add(transaction);
	}

	public Iterator getTransactions(String memberId, Calendar startDate, Calendar endDate) {
		LinkedList<Transaction> transactionRange = new LinkedList<>();

		for (Transaction transaction: transactions) {
			if (!transaction.getMember().getMemberId().equals(memberId)) {
				break;
			}

			Calendar date = transaction.getSaleDate();
			if (startDate.get(Calendar.YEAR) > date.get(Calendar.YEAR)
					&& startDate.get(Calendar.MONTH) > date.get(Calendar.MONTH)
					&& startDate.get(Calendar.DATE) > date.get(Calendar.DATE)) {
				break;
			}

			if (endDate.get(Calendar.YEAR) < date.get(Calendar.YEAR)
					&& endDate.get(Calendar.MONTH) < date.get(Calendar.MONTH)
					&& endDate.get(Calendar.DATE) < date.get(Calendar.DATE)) {
				break;
			}

			transactionRange.add(transaction);
		}

		return transactionRange.iterator();
	}
}