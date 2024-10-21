/*
 *@author:Gustavo Rodrigues Santos Silva
 * RA: 1110481922011
 */
package br.com.gustavorssbr.compraringresso;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import br.com.gustavorssbr.compraringresso.model.Ingresso;

public class MainActivity extends AppCompatActivity {

    private EditText etIngresso;
    private EditText etValor;
    private EditText etTaxa;
    private CheckBox cbVip;
    private Button btnComprar;
    private Ingresso ingresso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        etIngresso = findViewById(R.id.etIngresso);
        etValor = findViewById(R.id.etValor);
        etTaxa = findViewById(R.id.etTaxa);
        cbVip = findViewById(R.id.cbVip);
        cbVip.setChecked(false);
        btnComprar = findViewById(R.id.btnComprar);

        btnComprar.setOnClickListener(op -> comprar());
    }

    private void comprar() {
        String codigo = etIngresso.getText().toString();
        Float valor = Float.valueOf(etValor.getText().toString());
        Float taxa = Float.parseFloat(etTaxa.getText().toString());

        if(cbVip.isChecked()){
            ingresso = new Ingresso(codigo, valor, true);
        } else{
            ingresso = new Ingresso(codigo, valor, false);
        }

        Float valorFinal = ingresso.valorFinal(taxa);

        Bundle bundle = new Bundle();

        bundle.putString("codigo", "Código do ingresso: " + codigo);
        bundle.putString("valor", "Valor do ingresso: " + valor);
        bundle.putString("valorFinal","Valor Total: " + valorFinal);
        bundle.putString("taxa","Taxa de conveniência: " + taxa);
        bundle.putString("vip", "O ingreso é vip: " + cbVip.isChecked());

        troca(bundle);

    }

    private void troca(Bundle bundle) {
        Intent i = new Intent(this, SaidaActivity.class);
        i.putExtras(bundle);
        this.startActivity(i);
        this.finish();
    }
}