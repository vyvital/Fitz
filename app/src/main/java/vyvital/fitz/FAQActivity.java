package vyvital.fitz;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vyvital.fitz.data.QaAdapter;
import vyvital.fitz.data.models.QA;

public class FAQActivity extends BaseActivity {

    RecyclerView list;
    String[] questions;
    String[] answers;
    List<QA> qaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle(getResources().getString(R.string.faq));
        list = findViewById(R.id.list);
        final Resources res = getResources();
        questions = res.getStringArray(R.array.Q);
        answers = res.getStringArray(R.array.A);
        qaList = new ArrayList<>();
        int i = 0;
        while (i < 7) {
            QA qa = new QA(questions[i], answers[i]);
            qaList.add(qa);
            i++;
        }
        QaAdapter mQA = new QaAdapter(qaList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        list.setLayoutManager(mLayoutManager);
        list.setItemAnimator(new DefaultItemAnimator());
        list.setAdapter(mQA);
    }

    public void mail() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + "FitzHelp247@gmail.com"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Issue - ");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello Fitz!");
        startActivity(Intent.createChooser(emailIntent, "Chooser EMail app"));
    }
}