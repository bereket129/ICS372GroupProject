import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
/**
 *
 *	This is the collection class for transactions
 *
 */
public class TransactionList implements Serializable {
	private LinkedList<Transaction> transactions = new LinkedList<Transaction>();
	private static TransactionList transactionList;
	/**
	 * Constructor for TransactionList
	 */
	private TransactionList() {
	}
	/**
	 * Retrieves instance of TransactionList
	 *
	 * @return
	 *        Returns the TransactionList
	 */
	public static TransactionList getInstance() {
		if(transactionList == null) {
			transactionList = new TransactionList();
		}
		return transactionList;
	}
	/**
	 * This adds a transaction to the list
	 * @param transaction
	 *                  Transaction being added
	 * @return
	 *        Returns true if it is added
	 */
	public boolean addTransaction(Transaction transaction) {
		return transactions.add(transaction);
	}
	/**
	 * Retrieves the transactions that occured during a given time period
	 * @param memberId
	 *                Member that is a part of the transaction
	 * @param startDate
	 *                 Beginning of the time period
	 * @param endDate
	 *               End of the time period
	 * @return
	 *        the iterator object containing the transactions
	 */
	public Iterator getTransactions(String memberId, Calendar startDate, Calendar endDate) {
		LinkedList<Transaction> transactionRange = new LinkedList<>();

		for (Transaction transaction: transactions) {
			if (!transaction.getMember().getMemberId().equals(memberId)) {
				continue;
			}

			Calendar date = transaction.getSaleDate();
			if (startDate.get(Calendar.YEAR) > date.get(Calendar.YEAR)
					&& startDate.get(Calendar.MONTH) > date.get(Calendar.MONTH)
					&& startDate.get(Calendar.DATE) > date.get(Calendar.DATE)) {
				continue;
			}

			if (endDate.get(Calendar.YEAR) < date.get(Calendar.YEAR)
					&& endDate.get(Calendar.MONTH) < date.get(Calendar.MONTH)
					&& endDate.get(Calendar.DATE) < date.get(Calendar.DATE)) {
				continue;
			}

			transactionRange.add(transaction);
		}

		return transactionRange.iterator();
	}
}