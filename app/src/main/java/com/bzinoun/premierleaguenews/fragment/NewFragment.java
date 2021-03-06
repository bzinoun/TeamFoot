package com.bzinoun.premierleaguenews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bzinoun.premierleaguenews.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by hungvu on 8/29/2017.
 * Project PremierLeagueNews
 * Package com.bzinoun.premierleaguenews.fragment
 */

public class NewFragment extends Fragment {
    @BindView(R.id.webView)
    WebView webView;
    private Unbinder bind;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new, container, false);
        bind = ButterKnife.bind(this, view);
        WebViewClient webViewClient = new WebViewClient();
        webView.setWebViewClient(webViewClient);
        webView.loadUrl("https://www.premierleague.com/");
        return view;
    }


    @Override
    public void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }
}
