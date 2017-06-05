package be.formath.formathmobile.control;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import be.formath.formathmobile.R;

public class TutorialActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        WebView webView = (WebView) findViewById(R.id.display_tutorial);
        //webView.setText(Html.fromHtml("tutorial_text"));
        webView.loadData(getString(R.string.tutorial_text), "text/html", null);
    }
}
