package com.example.ruan.jogo_da_velha_excript;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private View view;

    //const. da tag de cada botão
    //utilizamos essa const. para recuperar o botão através do método getQuadrado
    private final String QUADRADO  = "quadrado";
    //const. que vai ser impressa no text do botão
    private final String BOLA      = "O";
    //const. que vai ser impressa no text do botão
    private final String XIS       = "X";

    private String lastPlay = XIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Não iremos utilizar este método abaixo nesta estrutuura pois precisaremos
        // guardar a instância da view que estamos criando
//        setContentView(R.layout.activity_main);
        setView(
                getLayoutInflater().inflate( R.layout.activity_main, null) //estamos 'inflando'
                // o arquivo xml activity_main e guardando a instância do xml dentro da variável view
        ); // em outras palavras, é chamado o arquivo xml e obtendo a instância dele, pois será
            // necessário utilizar esta instância várias vezes
        setContentView(getView()); //Estamos passando a instância da view que guardamos
    }

    public void clickQuadrado(View view){
        if (lastPlay.equals(XIS)){
            ((Button)view).setText(BOLA);
            lastPlay = BOLA;
        }else {
            ((Button)view).setText(XIS);
            lastPlay = XIS;
        }
        Toast.makeText(getView().getContext(), view.getTag().toString(), Toast.LENGTH_SHORT).show();
    }

    public Button getQuadrado(int tagNum){
        // obtemos os botões através de suas tags, utilizando a expressão
        // regular definido como constante no atributo tag dos botões no arquivo xml
        return (Button)getView().findViewWithTag(QUADRADO + tagNum);
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
