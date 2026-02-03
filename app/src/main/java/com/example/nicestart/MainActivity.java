package com.example.nicestart;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity extends AppCompatActivity {

    private WebView miVisorWeb;
    private SwipeRefreshLayout swipeRLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //TextView mycontext = findViewById(R.id.mytext);
        WebView mycontext = findViewById(R.id.vistaweb);
        registerForContextMenu(mycontext);

        swipeRLayout=findViewById(R.id.myswype);
        swipeRLayout.setOnRefreshListener(monRefreshListener);

        miVisorWeb = (WebView) findViewById(R.id.vistaweb);

        String html = "<html>" +
                "<head><style>" +
                "html, body { margin:0; padding:0; height:100%; overflow:hidden; }" +
                "img { width:100%; height:100%; object-fit:cover; }" +   // ❤️ el equivalente a centerCrop
                "</style></head>" +
                "<body>" +
                "<img src='https://thispersondoesnotexist.com' />" +
                "</body></html>";

        miVisorWeb.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);


    }

    protected  SwipeRefreshLayout.OnRefreshListener
            monRefreshListener=new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            Toast toast0=Toast.makeText(MainActivity.this,"Hola, has recargado la pagina", Toast.LENGTH_LONG);
            toast0.show();
            miVisorWeb.reload();
            swipeRLayout.setRefreshing(false);

        }
    };

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item1) {
            Toast toast = Toast.makeText(this, "Item copied", Toast.LENGTH_LONG);
            toast.show();
        } else if (item.getItemId() == R.id.item2) {
            Toast toast2 = Toast.makeText(this, "Dowloading item..", Toast.LENGTH_LONG);
            toast2.show();
        }
        return false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_appbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if (id==R.id.item4){
            Intent intent= new Intent(this,MainBab.class);
            startActivity(intent);
        }
        if (id==R.id.item5){
            showAlertDialogButtonClicked(MainActivity.this);

        }
        return super.onOptionsItemSelected(item);
    }

    public void showAlertDialogButtonClicked(MainActivity mainActivity){
        //alert builder
        MaterialAlertDialogBuilder builder=new MaterialAlertDialogBuilder(this);
        //el dialogo
        builder.setTitle("Opciones!!");
        builder.setMessage("A donde quuieres ir");
        builder.setIcon(R.drawable.puntos_menu);
        builder.setCancelable(true);
        //añadir botones
        builder.setPositiveButton("Scrolling", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                Toast toast = Toast.makeText(MainActivity.this, "Scrolling...", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        builder.setNegativeButton("Do nothing", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNeutralButton("Other", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(MainActivity.this,Login.class);
                startActivity(intent);
                //System.exit(0);
            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }

}