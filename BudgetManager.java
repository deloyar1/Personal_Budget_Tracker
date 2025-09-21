import java.util.*;

public class BudgetManager {
    private List<Transaction> list;

    public BudgetManager() {
        list = FileHandler.loadTransactions();
    }

    public void add(Transaction t) {
        list.add(t);
        FileHandler.saveTransaction(t);
    }

    public List<Transaction> getList() {
        return list;
    }

    public double getIncome() {
        return list.stream().filter(t -> t.getType().equals("Income")).mapToDouble(Transaction::getAmount).sum();
    }

    public double getExpense() {
        return list.stream().filter(t -> t.getType().equals("Expense")).mapToDouble(Transaction::getAmount).sum();
    }

    public double getBalance() {
        return getIncome() - getExpense();
    }
}