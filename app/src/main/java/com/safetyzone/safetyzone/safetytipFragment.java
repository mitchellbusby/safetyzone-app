package com.safetyzone.safetyzone;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mitchellbusby on 2/10/2015.
 */
public class safetytipFragment extends Fragment {
    InputStream inputStream;
    //ArrayList<String> resultList = new ArrayList<String>();
    //String[] tips = new String[2000];

    public ArrayList<SafetyTipData> safetyTipList = new ArrayList<SafetyTipData>();
    int tipNumber = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tips, container, false);

        //ArrayList<String> values = new ArrayList<>();

        ArrayList<String> topic = new ArrayList<>();
        ArrayList<String> id = new ArrayList<>();
        ArrayList<String> tip = new ArrayList<>();
        ArrayList<String> source = new ArrayList<>();



        //ArrayList list = (ArrayList) read(); //reads file
        read();  //read file and save into safety tip data

//        TextView tipTitle = (TextView) view.findViewById(R.id.list_title);
//        TextView tipInfo = (TextView) view.findViewById(R.id.list_tip);
//        TextView tipSource = (TextView) view.findViewById(R.id.list_source);
//        tipTitle.setText(SafetyzoneApplication.getLatitudeText());
//        tipInfo.setText(SafetyzoneApplication.getLongitudeText());
//        tipSource.setText(SafetyzoneApplication.getLongitudeText());

        for (SafetyTipData safetyTip: safetyTipList){
            topic.add(safetyTip.getTopic());
            id.add(safetyTip.getId());
            tip.add(safetyTip.getTip());
            source.add(safetyTip.getSource());
        }

        populateTipsList(view, topic, id, tip, source);
        return view;
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

    public void populateTipsList(View view, ArrayList<String> topic, ArrayList<String> id, ArrayList<String> tip, ArrayList<String> source) {
        final ListView listView = (ListView) view.findViewById(R.id.tips_list);
        /*String[] values = new String[] {
                "Blah"
        };*/
        SafetyTipsAdapter arrayAdapter = new SafetyTipsAdapter(view.getContext(), R.layout.list_tip_row, topic, id, tip, source);
        listView.setAdapter(arrayAdapter);
        addListenerToListModifierButton(view, arrayAdapter);
    }

    public void CSVFile(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void read() {
        List resultList = new ArrayList();
        BufferedReader reader = null;
        try {
            inputStream = getResources().getAssets().open("safety-tips.csv");
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                //row tips = csvLine.split(",");
                //resultList.add(csvLine);
                handleLine(csvLine);
            }
        } catch (IOException ex) {

        } finally {
            try {
                if(reader != null)
                    reader.close();
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                //throw new RuntimeException("Error while closing input stream: " + e);
            }
        }

        //return resultList;


    }

    public void handleLine(String line) {
        String[] tips =  line.split(",");
        SafetyTipData safetyTip = new SafetyTipData(tips[0], tips[1], tips[2], tips[3]);
        safetyTipList.add(safetyTip);
    }


}

