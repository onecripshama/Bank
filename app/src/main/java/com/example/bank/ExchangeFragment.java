package com.example.bank;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ExchangeFragment extends Fragment {
    private List<ListItemClass> arrayList;
    private CustomArrayAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exchange, container, false);
        init(view, inflater);
        return view;
    }

    private void init(View view, LayoutInflater inflater) {
        ListView listView = view.findViewById(R.id.listView);
        arrayList = new ArrayList<>();
        adapter = new CustomArrayAdapter(requireActivity(), R.layout.list_item_1, arrayList, inflater);
        listView.setAdapter(adapter);
        new Thread(() -> getWeb()).start();
    }

    private void getWeb() {
        try {
            Document doc = Jsoup.connect("https://cbr.ru/currency_base/daily/").get();
            Elements tables = doc.getElementsByTag("tbody");
            Element our_table = tables.get(0);
            for (int i = 0; i < our_table.childrenSize(); i++) {
                ListItemClass items = new ListItemClass();
                items.setData_1(our_table.children().get(i).child(1).text());
                items.setData_2(our_table.children().get(i).child(4).text());
                items.setData_3(our_table.children().get(i).child(2).text());
                arrayList.add(items);
            }
            requireActivity().runOnUiThread(() -> adapter.notifyDataSetChanged());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}