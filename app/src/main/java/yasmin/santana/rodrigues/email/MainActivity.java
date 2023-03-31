package yasmin.santana.rodrigues.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnEnviar = (Button) findViewById(R.id.btnEnviar); //conectando botao e colocando a funcao que ele deve fazer
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etEmail = (EditText) findViewById(R.id.etEmail); //conectando com a interface, pegando o texto e transformando em string
                String email = etEmail.getText().toString();

                EditText etAssunto = (EditText) findViewById(R.id.etAssunto);
                String assunto = etAssunto.getText().toString();

                EditText etTexto = (EditText) findViewById(R.id.etTexto);
                String texto = etTexto.getText().toString();

                Intent i = new Intent(Intent.ACTION_SENDTO); //cria uma intent para enviar
                i.setData(Uri.parse("mailto:")); //pega apps que utilizam do email

                String[] emails = new String[]{email}; //preenchendo intent
                i.putExtra(Intent.EXTRA_EMAIL, emails);
                i.putExtra(Intent.EXTRA_SUBJECT, assunto);
                i.putExtra(Intent.EXTRA_TEXT, texto);

                try {
                    startActivity(Intent.createChooser(i, "Escolha o APP")); //usuario escolhe o app de email que eles quiserem, por ex: hotmail
                }
                catch (ActivityNotFoundException e){ //se nn tiver nenhum app de email no aparelho mostra erro
                    Toast.makeText(MainActivity.this, "Não há nenhum app que posso realizar essa operação", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}