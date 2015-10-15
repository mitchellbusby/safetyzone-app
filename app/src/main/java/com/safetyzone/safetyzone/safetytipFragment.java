package com.safetyzone.safetyzone;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by mitchellbusby on 2/10/2015.
 */
public class safetytipFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tips, container, false);
        ArrayList<String> values = new ArrayList<>();
        values.add("Tip1");
        values.add("Tip2");
        values.add("Tip3");
        populateTipsList(view, values);
        return view;
    }

    public void addContact(SafetyTipsAdapter tipAdapter) {

    }
    public void addListenerToListModifierButton(View view, final SafetyTipsAdapter tipAdapter) {
//        final Button button = (Button) view.findViewById(R.id.add_to_list_button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addContact(tipAdapter);
//            }
//        });
    }
    public void populateTipsList(View view, ArrayList<String> values) {
        final ListView listView = (ListView) view.findViewById(R.id.tips_list);
        /*String[] values = new String[] {
                "Blah"
        };*/
        SafetyTipsAdapter arrayAdapter = new SafetyTipsAdapter(view.getContext(), R.layout.list_tip_row, R.id.list_tip,values);
        listView.setAdapter(arrayAdapter);
        addListenerToListModifierButton(view, arrayAdapter);
    }


}
