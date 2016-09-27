package com.catnbear.fxml;

import com.catnbear.database.Budget;
import com.catnbear.database.DataModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.SQLException;

/**
 * Created by przemek on 05.09.16.
 */
public class addEntryBoxController {
    @FXML private TextField dateTextField;
    @FXML private TextField counterPartyTextField;
    @FXML private TextField categoryTextField;
    @FXML private TextField subcategoryTextField;
    @FXML private TextField descriptionTextField;
    @FXML private TextField typeTextField;
    @FXML private TextField amountTextField;
    @FXML private Button addBudgetButton;
    @FXML private DatePicker datePicker;

    @FXML private void addBudgetButtonClicked() {
        Budget budget = new Budget();
//        budget.setDate(dateTextField.getText());
        budget.setDate(datePicker.getValue().toString());
        budget.setCounterParty(counterPartyTextField.getText());
        budget.setCategory(categoryTextField.getText());
        budget.setSubcategory(subcategoryTextField.getText());
        budget.setDescription(descriptionTextField.getText());
        budget.setType(typeTextField.getText());
        budget.setAmount(amountTextField.getText());
        System.out.println(budget.toQuery());
        budget.queryUpdate();
        try {
            DataModel.getInstance().updateData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
