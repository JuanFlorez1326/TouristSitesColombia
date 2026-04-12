package com.politecnico.TouristSitesColombia;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.politecnico.TouristSitesColombia.databinding.FragmentRecomendacionesBinding;


public class RecomendacionesFragment extends Fragment {

    private FragmentRecomendacionesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRecomendacionesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });

        binding.etBuscadorUrl.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_DONE) {
                String url = binding.etBuscadorUrl.getText().toString().trim();
                if (!url.isEmpty()) {
                    if (!url.startsWith("http://") && !url.startsWith("https://")) {
                        url = "https://" + url;
                    }
                    abrirNavegador(url, url);
                }

                InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                return true;
            }
            return false;
        });

        binding.tvLink1.setOnClickListener(v -> abrirNavegador("https://colombiaturismo.com.co/tolu.html", "Colombia Turismo"));
        binding.tvLink2.setOnClickListener(v -> abrirNavegador("https://pajoytours.com/tolu/", "Pajoy Tours"));
        binding.tvLink3.setOnClickListener(v -> abrirNavegador("https://www.colombia.com/turismo/sitios-turisticos/tolu/", "Colombia.com"));

        binding.btnCerrarWeb.setOnClickListener(v -> {
            binding.llWebView.setVisibility(View.GONE);
            binding.llContent.setVisibility(View.VISIBLE);
            binding.webView.loadUrl("about:blank");
        });

        return root;
    }

    private void abrirNavegador(String url, String titulo) {
        binding.tvWebTitle.setText(titulo);

        binding.llContent.setVisibility(View.GONE);
        binding.llWebView.setVisibility(View.VISIBLE);

        binding.webView.loadUrl(url);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}