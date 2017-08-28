package ribeiro.elber.unimedagendamentoonline;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public WebView webView;
    private PublisherAdView mAdView;
   private boolean isWebViewLoadingFirstPage = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAdView = (PublisherAdView) findViewById(R.id.ad_view);


// Initialize the Mobile Ads SDK.

        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder().build();

        // Start loading the ad in the background.
        mAdView.loadAd(adRequest);
       // LinearLayout ll = (LinearLayout) findViewById(R.id.LinearLayout1);

       /* WebView wv = new WebView(this); //(WebView) findViewById(R.id.webView1);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(DrawerLayout.LayoutParams.WRAP_CONTENT, DrawerLayout.LayoutParams.WRAP_CONTENT);
        wv.setLayoutParams(lp);
        int default_zoom_level=80;
        Point Scroll=new Point(0,0);
        wv.setInitialScale(default_zoom_level);
        WebSettings ws = wv.getSettings();
        ws.setJavaScriptEnabled(true);
        wv.getSettings().setBuiltInZoomControls(true);

        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl("http://agendaonline.unimedgoiania.coop.br/GUI/PrincipalAOL.aspx");




        ll.addView(wv);*/

        final WebView webView = (WebView)this.findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        int default_zoom_level=75;
        Point Scroll=new Point(0,0);
        webView.setInitialScale(default_zoom_level);
        WebSettings ws = webView.getSettings();
        ws.setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                view.loadUrl(url);
                return true;
            }





        });
        webView.loadUrl("http://agendaonline.unimedgoiania.coop.br/GUI/PrincipalAOL.aspx");







        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        webView.setWebViewClient(new WebViewClient() {
          //  ProgressBar mProgressBar = (ProgressBar) findViewById(R.id.progressBar3);

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
              //  mProgressBar.setVisibility(ProgressBar.VISIBLE);
                view.loadUrl("javascript:(function() { " +
                        "document.getElementById('ctl00_cphPrincipal_btoLoginCooperado')[0].style.display='none'; " +
                        "})()");
                webView.setVisibility(View.INVISIBLE);
            }

            public void onPageFinished(WebView view, String url)
            {
                view.loadUrl("javascript:(function() { " +
                        "document.getElementById('ctl00_cphPrincipal_btoLoginCooperado')[0].style.display='none'; " +
                        "})()");
            }
            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);
             //   mProgressBar.setVisibility(ProgressBar.GONE);
                webView.setVisibility(View.VISIBLE);
                isWebViewLoadingFirstPage = false;
            }
        });

    }


@Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            //EditText campoTelefone = (EditText) findViewById(R.id.edTelefone);

            String telefone = "6232168000";

            Uri uri = Uri.parse("tel:"+telefone);
            Intent intent = new Intent(Intent.ACTION_DIAL,uri);

            startActivity(intent);


            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

            System.exit(0);

        }else if (id == R.id.Inicio){
            WebView webView = (WebView)this.findViewById(R.id.webView1);
            webView.getSettings().setJavaScriptEnabled(true);
            int default_zoom_level=65;
            Point Scroll=new Point(0,0);
            webView.setInitialScale(default_zoom_level);
            WebSettings ws = webView.getSettings();
            ws.setJavaScriptEnabled(true);
            webView.getSettings().setBuiltInZoomControls(true);
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url)
                {
                    view.loadUrl(url);
                    return true;
                }
            });

            webView.loadUrl("http://agendaonline.unimedgoiania.coop.br/GUI/PrincipalAOL.aspx");

        }else if(id == R.id.MinhaArea){
            WebView webView = (WebView)this.findViewById(R.id.webView1);
            webView.getSettings().setJavaScriptEnabled(true);
            int default_zoom_level=65;
            Point Scroll=new Point(0,0);
            webView.setInitialScale(default_zoom_level);
            WebSettings ws = webView.getSettings();
            ws.setJavaScriptEnabled(true);
            webView.getSettings().setBuiltInZoomControls(true);
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url)
                {
                    view.loadUrl(url);
                    return true;
                }
            });

            webView.loadUrl("http://agendaonline.unimedgoiania.coop.br/GUI/MinhaAreaPessoa.aspx");
        }else if (id == R.id.Pesquisa){
            WebView webView = (WebView)this.findViewById(R.id.webView1);
            webView.getSettings().setJavaScriptEnabled(true);
            int default_zoom_level=75;
            Point Scroll=new Point(0,0);
            webView.setInitialScale(default_zoom_level);
            WebSettings ws = webView.getSettings();
            ws.setJavaScriptEnabled(true);
            webView.getSettings().setBuiltInZoomControls(true);
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url)
                {
                    view.loadUrl(url);
                    return true;
                }
            });

            webView.loadUrl("http://agendaonline.unimedgoiania.coop.br/GUI/Principal.aspx");
        }else if (id == R.id.Agenda) {

            ArrayList<HashMap<String,Object>> items =new ArrayList<HashMap<String,Object>>();
            final PackageManager pm = getPackageManager();
            List<PackageInfo> packs = pm.getInstalledPackages(0);
            for (PackageInfo pi : packs) {
                if( pi.packageName.toString().toLowerCase().contains("com.android.calendar")){
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("appName", pi.applicationInfo.loadLabel(pm));
                    map.put("packageName", pi.packageName);
                    items.add(map);
                }
            }

            if(items.size()>=1){
                String packageName = (String) items.get(0).get("packageName");
                Intent i = pm.getLaunchIntentForPackage(packageName);
                if (i != null) {
                    startActivity(i);
                } else{
                    // Aplicativo não encontrado
                }
            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }

}
