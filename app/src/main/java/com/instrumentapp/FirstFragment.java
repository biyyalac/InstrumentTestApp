package com.instrumentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class FirstFragment extends Fragment implements View.OnClickListener{

    private TextView textview;
    private EditText edittext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textview = (TextView)view.findViewById(R.id.textview);
        edittext = (EditText)view.findViewById(R.id.editTextUserInput);
        view.findViewById(R.id.changeTextBt).setOnClickListener(this);
        view.findViewById(R.id.activityChangeTextBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        // Get the text from the EditText view.
        final String text = edittext.getText().toString();

        final int changeTextBtId = R.id.changeTextBt;
        final int activityChangeTextBtnId = R.id.activityChangeTextBtn;

        if (view.getId() == changeTextBtId) {
            // First button's interaction: set a text in a text view.
            textview.setText(text);
        } else if (view.getId() == activityChangeTextBtnId) {
            // Second button's interaction: start an activity and send a message to it.
            Intent intent = new Intent(getActivity(),ShowTextActivity.class);
            intent.putExtra("MESSAGE","Message from Previous activity");
            startActivity(intent);
        }

    }
}
