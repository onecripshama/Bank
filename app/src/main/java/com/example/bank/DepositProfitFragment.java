package com.example.bank;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class DepositProfitFragment extends Fragment {

    private EditText depositAmountEditText;
    private EditText interestRateEditText;
    private TextView profitTextView;

    private EditText termAmountEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_deposit_profit, container, false);

        depositAmountEditText = view.findViewById(R.id.depositAmountEditText);
        interestRateEditText = view.findViewById(R.id.interestRateEditText);
        Button calculateButton = view.findViewById(R.id.calculateButton);
        profitTextView = view.findViewById(R.id.profitTextView);
        termAmountEditText = view.findViewById(R.id.termAmount_et);

        calculateButton.setOnClickListener(v -> calculateProfit());

        return view;
    }

    @SuppressLint("DefaultLocale")
    private void calculateProfit() {
        String depositAmountString = depositAmountEditText.getText().toString();
        String interestRateString = interestRateEditText.getText().toString();
        String termAmountString = termAmountEditText.getText().toString();

        if (!depositAmountString.isEmpty() && !interestRateString.isEmpty()) {
            double depositAmount = Double.parseDouble(depositAmountString);
            double interestRate = Double.parseDouble(interestRateString);
            int term = Integer.parseInt(termAmountString);

            double profit = depositAmount * (interestRate / 100)/365 * term;

            profitTextView.setText(String.format("Прибыль: %.2f", profit));
        } else {
            profitTextView.setText("Введите сумму вклада и процентную ставку");
        }
    }
}