package com.example.bank;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class CreditPaymentFragment extends Fragment {

    private EditText creditAmountEditText;
    private EditText interestRateEditText;
    private EditText loanTermEditText;
    private TextView monthlyPaymentTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_credit_payment, container, false);

        creditAmountEditText = view.findViewById(R.id.creditAmountEditText);
        interestRateEditText = view.findViewById(R.id.interestRateEditText);
        loanTermEditText = view.findViewById(R.id.loanTermEditText);
        Button calculateButton = view.findViewById(R.id.calculateButton);
        monthlyPaymentTextView = view.findViewById(R.id.monthlyPaymentTextView);

        calculateButton.setOnClickListener(v -> calculateMonthlyPayment());

        return view;
    }

    private void calculateMonthlyPayment() {
        String creditAmountString = creditAmountEditText.getText().toString();
        String interestRateString = interestRateEditText.getText().toString();
        String loanTermString = loanTermEditText.getText().toString();

        if (!creditAmountString.isEmpty() && !interestRateString.isEmpty() && !loanTermString.isEmpty()) {
            double creditAmount = Double.parseDouble(creditAmountString);
            double interestRate = Double.parseDouble(interestRateString);
            int loanTerm = Integer.parseInt(loanTermString);

            double monthlyInterestRate = interestRate / 100 / 12;
            int numberOfPayments = loanTerm * 12;

            double monthlyPayment = (creditAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -numberOfPayments));

            monthlyPaymentTextView.setText(String.format("Ежемесячный платеж: %.2f", monthlyPayment));
        } else {
            monthlyPaymentTextView.setText("Введите сумму кредита, процентную ставку и срок кредита");
        }
    }
}