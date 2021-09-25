package com.betharley.rodrigues.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView expression;
    TextView resultHolder;
    private String[] BODMAS_RULES = {"-", "\\+", "×", "÷"};
    private int CURRENT_RULE = 0;
    private String historico = "";

    private String operacaoEscolhida = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //VISOR
        expression = findViewById(R.id.expression);
        resultHolder = findViewById(R.id.resultHolter);

        //OPERAÇÕES
        final Button cBtn = findViewById(R.id.cBtn);
        final Button raizBtn = findViewById(R.id.raizBtn);
        final Button modulusBtn = findViewById(R.id.modulusBtn);
        final Button divideBtn = findViewById(R.id.divisionBtn);
        final Button multiplicationBtn = findViewById(R.id.multiplicationBtn);
        final Button substractionBtn = findViewById(R.id.substrationBtn);
        final Button dotBtn = findViewById(R.id.dotBtn);
        final Button delBtn = findViewById(R.id.delBtn);
        final Button egualsBtn = findViewById(R.id.egualsBtn);
        final Button addtionBtn = findViewById(R.id.additionBtn);

        //NUMEROS
        final Button nineBtn = findViewById(R.id.nineBtn);
        final Button eightBtn = findViewById(R.id.eightBtn);
        final Button sevenBtn = findViewById(R.id.sevenBtn);
        final Button sixBtn = findViewById(R.id.sixBtn);
        final Button fiveBtn = findViewById(R.id.fiveBtn);
        final Button fourBtn = findViewById(R.id.fourBtn);
        final Button threeBtn = findViewById(R.id.threeBtn);
        final Button twoBtn = findViewById(R.id.twoBtn);
        final Button oneBtn = findViewById(R.id.oneBtn);
        final Button zeroBtn = findViewById(R.id.zeroBtn);

        //ADICIONANDO EVENTO NOS BOTOES DE NUMEROS
        zeroBtn.setOnClickListener(this);
        oneBtn.setOnClickListener(this);
        twoBtn.setOnClickListener(this);
        threeBtn.setOnClickListener(this);
        fourBtn.setOnClickListener(this);
        fiveBtn.setOnClickListener(this);
        sixBtn.setOnClickListener(this);
        sevenBtn.setOnClickListener(this);
        eightBtn.setOnClickListener(this);
        nineBtn.setOnClickListener(this);

        //LIMPAR
        cBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression.setText(""); //empty expression
                resultHolder.setText("");
                historico = "";
            }
        });
        //RAIZ QUADRADA
        raizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String expressionText = expression.getText().toString();

                if ( expressionText.isEmpty() ){
                    //expression.setText("0+");
                }else{
                    setHistorico(expressionText+"√");

                    double raiz = Double.parseDouble(expressionText);
                    raiz = Math.sqrt(raiz);
                    String[] arrayDados = new String[2];
                    arrayDados[0] = "";
                    arrayDados[1] = "";

                    arrayDados = String.valueOf(raiz).split("\\.");

                    if( Double.parseDouble(arrayDados[1]) > 0.0 ){
                        if(arrayDados[1].length() == 1 ){
                            arrayDados[1] = arrayDados[1].substring(0, 1);
                        }else{
                            arrayDados[1] = arrayDados[1].substring(0, 2);
                        }
                        String resultado = String.valueOf(arrayDados[0]+"."+arrayDados[1]);
                        expression.setText( resultado );
                    }else{
                        expression.setText(String.valueOf(arrayDados[0]));
                    }
                }
            }
        });
        //SOMA
        addtionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacaoEscolhida = "+";
                final String expressionText = expression.getText().toString();
                if ( expressionText.isEmpty() ){
                    expression.setText("0+");
                }else{
                    final char getLastCharaster = expressionText.charAt(expressionText.length()-1);

                    if ( getLastCharaster == '÷' || getLastCharaster == '×' || getLastCharaster == '+' || getLastCharaster == '-'){
                        expression.setText(expressionText.substring(0, expressionText.length()-1) + "+");
                    }else{
                        expression.setText(expressionText + "+");
                    }
                }
            }
        });
        //PORCENTAGEM
        modulusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacaoEscolhida = "%";
                final String expressionText = expression.getText().toString();
                if ( expressionText.isEmpty() ){
                    //expression.setText("0%");
                }else{
                    final char getLastCharaster = expressionText.charAt(expressionText.length()-1);

                    if ( getLastCharaster == '%' && !expressionText.contains("%")){
                        expression.setText(expressionText.substring(0, expressionText.length()-1) + "%");
                    }else{
                        expression.setText(expressionText + "%");
                    }
                    //historico = historico + resultHolder.getText().toString();
                    //resultHolder.setText(historico+"=");
                }
            }
        });
        //DIVISÃO
        divideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacaoEscolhida = "÷";
                final String expressionText = expression.getText().toString();
                if ( expressionText.isEmpty() ){
                    expression.setText("0÷");
                }else{
                    final char getLastCharaster = expressionText.charAt(expressionText.length()-1);

                    if ( getLastCharaster == '÷' || getLastCharaster == '×' || getLastCharaster == '+' || getLastCharaster == '-'){
                        expression.setText(expressionText.substring(0, expressionText.length()-1) + "÷");
                    }else{
                        expression.setText(expressionText + "÷");
                    }
                }
            }
        });
        //MULTIPLICAÇÃO
        multiplicationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacaoEscolhida = "×";
                final String expressionText = expression.getText().toString();
                if ( expressionText.isEmpty() ){
                    expression.setText("0×");
                }else{
                    final char getLastCharaster = expressionText.charAt(expressionText.length()-1);

                    if ( getLastCharaster == '÷' || getLastCharaster == '×' || getLastCharaster == '+' || getLastCharaster == '-'){
                        expression.setText(expressionText.substring(0, expressionText.length()-1) + "×");
                    }else{
                        expression.setText(expressionText + "×");
                    }
                }
            }
        });
        //SUBTRAÇÃO
        substractionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacaoEscolhida = "-";
                final String expressionText = expression.getText().toString();
                if ( expressionText.isEmpty() ){
                    expression.setText("0-");
                }else{
                    final char getLastCharaster = expressionText.charAt(expressionText.length()-1);

                    if ( getLastCharaster == '÷' || getLastCharaster == '×' || getLastCharaster == '+' || getLastCharaster == '-'){
                        expression.setText(expressionText.substring(0, expressionText.length()-1) + "-");
                    }else{
                        expression.setText(expressionText + "-");
                    }
                }
            }
        });
        //PONTO
        dotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String expressionText = expression.getText().toString();
//                String[] arrayDados = new String[10];
//
//                if( expressionText.contains("+") || expressionText.contains("\\+")){//SOMA
//                    arrayDados = expressionText.split("\\+");
//                    operacaoEscolhida = "\\+";
//                }else
//                if( expressionText.contains("-") ){//SUBTRAÇÃO
//                    arrayDados = expressionText.split("-");
//                    operacaoEscolhida = "-";
//                }else
//                if( expressionText.contains("÷") ){//DIVISÃO
//                    arrayDados = expressionText.split("÷");
//                    operacaoEscolhida = "÷";
//                }else
//                if( expressionText.contains("×") ){//MULTIPLICAÇÃO
//                    arrayDados = expressionText.split("×");
//                    operacaoEscolhida = "×";
//                }
//                String primeiro = arrayDados[1];


                if ( expressionText.isEmpty() ){
                    expression.setText("0.");
                }else{
                    final char getLastCharaster = expressionText.charAt(expressionText.length()-1);

                    if ( !expressionText.contains(".") && getLastCharaster != '÷' && getLastCharaster != '%' && getLastCharaster != '×' && getLastCharaster != '-' && getLastCharaster != '+' ){
                        expression.setText(expressionText.substring(0, expressionText.length()) + ".");
                        Log.i("PONTO 1", expressionText);
                    }else{
                        Log.i("PONTO 2", expression.getText().toString());
                        expression.setText(expression.getText().toString());
                    }
                }
            }
        });
        //DELETAR
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String expressionText = expression.getText().toString();
                if( !expressionText.isEmpty() ){
                    expression.setText(expressionText.substring(0, expressionText.length()-1));
                }
            }
        });
        //IGUALDADE - RESULTADO
        egualsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String exppressionText = expression.getText().toString();

                if(exppressionText.contains("%")){
                    calularPorcentagem( exppressionText);
                    return;
                }
                //AS QUATRO OPERAÇÕES
                calcularOperacao(exppressionText);
                //historico = historico + resultHolder.getText().toString();
                //resultHolder.setText(historico);
            }
        });

    }
    //AS QUATRO OPERAÇÕES
    private void calcularOperacao(String exppressionText){
        if( exppressionText.contains("÷") || exppressionText.contains("×") || exppressionText.contains("-") || exppressionText.contains("+") ){
            final char getLastCharacter = exppressionText.charAt(exppressionText.length() - 1);

            if( getLastCharacter != '÷' || getLastCharacter != '×' || getLastCharacter != '-' || getLastCharacter != '+'){
                //calcularResult(expression, resultHolder, exppressionText);
                String guardarHistorico = expression.getText().toString();
                Log.i("guardarHistorico 1", String.valueOf(guardarHistorico));

                String[] arrayDados = new String[10];

                if( exppressionText.contains("+") || exppressionText.contains("\\+")){//SOMA
                    arrayDados = exppressionText.split("\\+");
                    operacaoEscolhida = "\\+";
                }else
                if( exppressionText.contains("-") ){//SUBTRAÇÃO
                    arrayDados = exppressionText.split("-");
                    operacaoEscolhida = "-";
                }else
                if( exppressionText.contains("÷") ){//DIVISÃO
                    arrayDados = exppressionText.split("÷");
                    operacaoEscolhida = "÷";
                }else
                if( exppressionText.contains("×") ){//MULTIPLICAÇÃO
                    arrayDados = exppressionText.split("×");
                    operacaoEscolhida = "×";
                }

                Log.i("finalResultArray 1", String.valueOf(arrayDados[0]));
                Log.i("finalResultArray 2", String.valueOf(arrayDados[1]));
                Log.i("finalResultArray S", String.valueOf(operacaoEscolhida));

                double results = 0;

                String primeiro = arrayDados[0];
                String segundo = arrayDados[1];
                arrayDados[0] = "";
                arrayDados[1] = "";

                switch (operacaoEscolhida){
                    case "÷":
                        results = Double.parseDouble(primeiro.toString()) / Double.parseDouble(segundo.toString());
                        break;
                    case "×":
                        results = Double.parseDouble(primeiro.toString()) * Double.parseDouble(segundo.toString());
                        break;
                    case "+":
                    case "\\+":
                        results = Double.parseDouble(primeiro.toString()) + Double.parseDouble(segundo.toString());
                        break;
                    case "-":
                        results = Double.parseDouble(primeiro.toString()) - Double.parseDouble(segundo.toString());
                        break;
                }
                arrayDados = String.valueOf(results).split("\\.");

                primeiro = arrayDados[0];
                segundo = arrayDados[1];
                Log.i("finalResultArray 4", String.valueOf(arrayDados[0]));
                Log.i("finalResultArray 5", String.valueOf(arrayDados[1]));

                if( Double.parseDouble(segundo) > 0.0 ){
                    if(segundo.length() == 1 ){
                        segundo = segundo.substring(0, 1);
                    }else{
                        segundo = segundo.substring(0, 2);
                    }
                    String resultado = String.valueOf(primeiro+"."+segundo);
                    expression.setText( resultado );
                    guardarHistorico = guardarHistorico + "=" + resultado;
                }else{
                    expression.setText(String.valueOf(primeiro));
                    guardarHistorico = guardarHistorico + "=" + primeiro;

                }

                setHistorico(guardarHistorico);
            }
        }
    }

    //REALIZA A OPERAÇÃO DE PORCENTAGEM
    private void calularPorcentagem(String exppressionText){

        if( exppressionText.contains("%") || exppressionText.contains("×") || exppressionText.contains("-") || exppressionText.contains("+") ){
            final char getLastCharacter = exppressionText.charAt(exppressionText.length() - 1);

            if( getLastCharacter != '%' || getLastCharacter != '÷' || getLastCharacter != '×' || getLastCharacter != '-' || getLastCharacter != '+'){
                String guardarHistorico = expression.getText().toString();
                double results = 0;

                String[] arrayDados = new String[10];
                String primeiro = arrayDados[0];
                String segundo = arrayDados[1];

                arrayDados[0] = "";
                arrayDados[1] = "";

                arrayDados = exppressionText.split("%");

                Log.i("finalResultArray 1", String.valueOf(arrayDados[0]));
                Log.i("finalResultArray 2", String.valueOf(arrayDados[1]));

                primeiro = arrayDados[0];
                segundo = arrayDados[1];

                results = (Double.parseDouble(primeiro)/100) * Double.parseDouble(segundo);
                arrayDados = String.valueOf(results).split("\\.");

                Log.i("finalResultArray R", String.valueOf(results));
                Log.i("finalResultArray 3", String.valueOf(arrayDados[0]));
                Log.i("finalResultArray 4", String.valueOf(arrayDados[1]));

                if( Double.parseDouble(arrayDados[1]) > 0.0 ){
                    if(arrayDados[1].length() == 1 ){
                        arrayDados[1] = arrayDados[1].substring(0, 1);
                    }else{
                        arrayDados[1] = arrayDados[1].substring(0, 2);
                    }
                    String resultado = String.valueOf(arrayDados[0]+"."+arrayDados[1]);
                    expression.setText( resultado );
                    guardarHistorico = guardarHistorico + "=" + resultado;
                }else{
                    expression.setText(String.valueOf(arrayDados[0]));
                    guardarHistorico = guardarHistorico + "=" + arrayDados[0];
                }

                setHistorico(guardarHistorico);

            }
        }
    }


    private void calcularResult(TextView expression, TextView resultHolder, String expressionText) {
        String expressionText2 = expressionText;

        while (true){

            if( CURRENT_RULE == 2 && !expressionText2.contains("+")){
                CURRENT_RULE++;
                continue;
            }else
            if ( CURRENT_RULE != 2 && !expressionText2.contains(BODMAS_RULES[CURRENT_RULE])){
                if(CURRENT_RULE == 3){
                    break;
                }else{
                    CURRENT_RULE++;
                    continue;
                }
            }

            String[] expressionArray = expressionText2.split(BODMAS_RULES[CURRENT_RULE]);

            if( expressionArray.length == 1 || expressionArray[0].isEmpty() ){
                break;
            }

            StringBuilder firstValue = new StringBuilder();
            StringBuilder secondtValue = new StringBuilder();

            for( int i = expressionArray[0].length()-1; i>= 0; i-- ){
                if( expressionArray[0].charAt(i) == '÷' || expressionArray[0].charAt(i) == '×' || expressionArray[0].charAt(i) == '+' || expressionArray[0].charAt(i) == '-' ){
                    break;
                }else {
                    firstValue.insert(0, expressionArray[0].charAt(i));
                }
            }

            for(int i =0; i < expressionArray[1].length(); i++){
                if( expressionArray[1].charAt(i) == '÷' || expressionArray[1].charAt(i) == '×' || expressionArray[1].charAt(i) == '+' || expressionArray[1].charAt(i) == '-' ){
                    break;
                }else {
                    secondtValue.append(expressionArray[1].charAt(i));
                }
            }

            double results;

            switch (BODMAS_RULES[CURRENT_RULE]){
                case "÷":
                    results = Double.parseDouble(firstValue.toString()) / Double.parseDouble(secondtValue.toString());
                    break;
                case "×":
                    results = Double.parseDouble(firstValue.toString()) * Double.parseDouble(secondtValue.toString());
                    break;
                case "+":
                case "\\+":
                    results = Double.parseDouble(firstValue.toString()) + Double.parseDouble(secondtValue.toString());
                    break;
                case "-":
                    results = Double.parseDouble(firstValue.toString()) - Double.parseDouble(secondtValue.toString());
                    break;
                default:
                    results = 0;
            }

            expressionText2 = expressionText2.replaceFirst(firstValue + BODMAS_RULES[CURRENT_RULE] + secondtValue, String.valueOf(results));
        }
        CURRENT_RULE = 0;
        resultHolder.setText(expressionText);

        String[] finalResultArray = expressionText2.split("\\.");


        Log.i("finalResultArray ", String.valueOf(finalResultArray));
        Log.i("finalResultArray 1 ", String.valueOf(finalResultArray[1]));
        Log.i("finalResultArray 0 ", String.valueOf(finalResultArray[0]));
//        if( finalResultArray[1].length() == 1 && finalResultArray[1].equals("0") ){
//            expression.setText(finalResultArray[0]);
//        }else {
//            expression.setText(expressionText2);
//        }

    }

    @Override
    public void onClick(View v) {
        int codigo = v.getId();
        String numero = "";

        switch ( codigo ){
            case R.id.zeroBtn:
                numero = "0";
                break;
            case R.id.oneBtn:
                numero = "1";
                break;
            case R.id.twoBtn:
                numero = "2";
                break;
            case R.id.threeBtn:
                numero = "3";
                break;
            case R.id.fourBtn:
                numero = "4";
                break;
            case R.id.fiveBtn:
                numero = "5";
                break;
            case R.id.sixBtn:
                numero = "6";
                break;
            case R.id.sevenBtn:
                numero = "7";
                break;
            case R.id.eightBtn:
                numero = "8";
                break;
            case R.id.nineBtn:
                numero = "9";
                break;
        }
        final String expressionText = expression.getText().toString();
        expression.setText( expressionText + numero);
        //Toast.makeText(getApplicationContext(), numero, Toast.LENGTH_SHORT).show();
    }

    private void setHistorico(String conteudo){

        resultHolder.setText(resultHolder.getText().toString() + conteudo + "  ");

        if( resultHolder.getText().toString().length() > 50 ){
            resultHolder.setTextSize(15);
            resultHolder.setMaxLines(3);
        }else{
            resultHolder.setTextSize(20);
            resultHolder.setMaxLines(2);
        }

    }

}