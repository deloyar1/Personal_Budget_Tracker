import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.*;

import java.time.LocalDate;

public class DashboardController {

    @FXML private TextField amountField;
    @FXML private ComboBox<String> typeCombo, categoryCombo;
    @FXML private DatePicker datePicker;
    @FXML private TableView<Transaction> transactionTable;
    @FXML private TableColumn<Transaction, String> colType, colCategory, colDate;
    @FXML private TableColumn<Transaction, Double> colAmount;
    @FXML private Label incomeLabel, expenseLabel, balanceLabel;

    private BudgetManager manager = new BudgetManager();

    @FXML
    public void initialize() {
        typeCombo.getItems().addAll("Income", "Expense");
        categoryCombo.getItems().addAll("Food", "Transport", "Rent", "Salary", "Other");
        datePicker.setValue(LocalDate.now());

        colType.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getType()));
        colCategory.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getCategory()));
        colDate.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getDate()));
        colAmount.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getAmount()));

        updateUI();
    }

    @FXML
    private void onAddTransaction() {
        String type = typeCombo.getValue();
        String category = categoryCombo.getValue();
        String date = datePicker.getValue().toString();
        double amount;

        try {
            amount = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) {
            return;
        }

        Transaction t = new Transaction(type, amount, category, date);
        manager.add(t);
        updateUI();
        clearForm();
    }

    private void updateUI() {
        ObservableList<Transaction> items = FXCollections.observableArrayList(manager.getList());
        transactionTable.setItems(items);

        incomeLabel.setText("Income: $" + manager.getIncome());
        expenseLabel.setText("Expenses: $" + manager.getExpense());
        balanceLabel.setText("Balance: $" + manager.getBalance());
    }

    private void clearForm() {
        amountField.clear();
        typeCombo.setValue(null);
        categoryCombo.setValue(null);
        datePicker.setValue(LocalDate.now());
    }
}