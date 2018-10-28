package com.example.ruan.jogo_da_velha_excript;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //guarda a instância da view
    private View view;

    //const. da tag de cada botão
    //utilizamos essa const. para recuperar o botão através do método getQuadrado
    private final String QUADRADO  = "quadrado";
    //const. que vai ser impressa no text do botão
    private final String BOLA      = "O";
    //const. que vai ser impressa no text do botão
    private final String XIS       = "X";

    //define as condições para o jogo acabar
    //para o jogo acabar, estes botões devem
    //possuir o mesmo texto
    int[][] estadoFinal = new int[][]{

            //linhas
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},

            //colunas
            {1, 4, 7},
            {2, 5, 8},
            {3, 6, 9},

            //diagonais
            {1, 5, 9},
            {3, 5, 7}
    }; //Totaliza 8 possíveis finais de jogo

    //guardamos o último valor jogado
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
        setContentView(getView()); //Estamos passando a instância da view que guardamos para a Activity
    }

    public void clickQuadrado(View view){
        if (!isFim()){ //verifica se o jogo acabou
            if ( ((Button)view).getText().equals("") ){ // Verifica se o campo já foi "jogado"

                //abaixo é verificado qual foi o último "jogador"
                if (lastPlay.equals(XIS)){
                    ((Button)view).setText(BOLA);
                    lastPlay = BOLA;
                }else {
                    ((Button)view).setText(XIS);
                    lastPlay = XIS;
                }
//            isFim();
            }else {
                //imprime uma mensagem informando que esta posição já foi jogada
                Toast.makeText(getView().getContext(), "Opa! Escolha outro quadrado.", Toast.LENGTH_SHORT).show();
            }
            isFim();
        }
    }

    public Button getQuadrado(int tagNum){
        // obtemos os botões através de suas tags, utilizando a expressão
        // regular definido como constante no atributo tag dos botões no arquivo xml
        return (Button)getView().findViewWithTag(QUADRADO + tagNum);
        // em outras palavras, retorna o respectivo quadrado requerido pela variável tagNum
    }

    public void newGame(View view){

        setEnableQuadrado(true); //ativa os quadrados
        setColorTextBlack(); //pinta os quadrados de preto
        limpaCampos();//limpa os textos de todos os quadrados


        RadioButton rbX = (RadioButton)getView().findViewById(R.id.rbX);
        RadioButton rbO = (RadioButton)getView().findViewById(R.id.rbO);

        if (rbX.isChecked()){
            //Se queremos começar com X, temos que definir que o último a jogar foi a O, e vice versa
            //porque jogador que começará a jogar sempre será o oposto do lastPlay
            lastPlay = BOLA;
        }else if (rbO.isChecked()){
            lastPlay = XIS;
        }
    }

    public void limpaCampos(){
        for (int i = 1; i <= 9; i++){ //percorre todos os quadrados definidos
            if (getQuadrado(i) != null){
                getQuadrado(i).setText("");
            }
        }
    }

    public void setColorTextBlack(){
        for (int i=0; i <=9; ++i){ //percorre todos os quadrados definidos
            if (getQuadrado(i) != null){
                setColorQuadrado(i, R.color.black);
            }
        }
    }

    public void setEnableQuadrado(boolean b){
        for (int i = 1; i <= 9; i++){ //percorre todos os quadrados definidos
            if (getQuadrado(i) != null){
                getQuadrado(i).setEnabled(b);
            }
        }
    }

    public void setColorQuadrado(int btn, int colorX){
        //recebe o num. do botão pela var btn
        //passa a instancia da cor pela var colorX
        getQuadrado(btn).setTextColor(getResources().getColor(colorX));
        //recuperamos o botão e definimos o textColor com a variável passada em colorX
    }

    public boolean isFim(){
        for (int x=0; x <= 7; ++x){ // Testa todos os 8 possíveis finais de jogo

            //abaixo é recuperado o texto do botão de cada quadrado definido pela matrix estadoFinal
            //nas posições que define o final do jogo (linhas, colunas e diagonais)
            String s1 = getQuadrado( estadoFinal[x][0] ).getText().toString();
            String s2 = getQuadrado( estadoFinal[x][1] ).getText().toString();
            String s3 = getQuadrado( estadoFinal[x][2] ).getText().toString();

            //abaixo é verificado se o texto dos botões que define o final do jogo estão com
            // valores diferente de vazio, pois se todos os botões estiverem vazios, o jogo
            //acaba na primeira jogada
            if ( (!s1.equals("")) &&
                 (!s1.equals("")) &&
                 (!s1.equals("")) )
            {
                //abaixo é verificado se o texto dos botões que define o final do jogo estão preenchidos
                //com o mesmo símbulo para terminar o jogo
                if (s1.equals(s2) && s2.equals(s3)){
                    setColorQuadrado( estadoFinal[x][0] , R.color.red);
                    setColorQuadrado( estadoFinal[x][1] , R.color.red);
                    setColorQuadrado( estadoFinal[x][2] , R.color.red);

                    Toast.makeText(getView().getContext(), "Fim de jogo", Toast.LENGTH_LONG).show();

                    return true;
                }
            }
        }
        return false;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
