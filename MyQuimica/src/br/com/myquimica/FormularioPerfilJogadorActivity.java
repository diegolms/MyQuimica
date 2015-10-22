package br.com.myquimica;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import br.com.myquimica.core.FController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class FormularioPerfilJogadorActivity extends Activity {

    private List<RadioGroup> listRadioGroup;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Map<Integer, String> mapaQuestoes;
    private Map<String, Integer> mapaAtiRef;
    private Map<String, Integer> mapaSenInt;
    private Map<String, Integer> mapaVisVer;
    private Map<String, Integer> mapaSeqGlo;
    static List<String> resultPerfil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formularioactivity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        
        criarListRadioGroups();

    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.send_formulario){
            //verificar_questoes();
        	Intent i = new Intent(this, MyQuimicaActivity.class);
            startActivity(i);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void criarListRadioGroups(){
        listRadioGroup = new LinkedList<RadioGroup>();
        mapaQuestoes = new HashMap<Integer, String>();
        for (int i = 1; i<= 20; i++){
            //String id = "R.id.radioQuestao"+i;
            int resId = getResources().getIdentifier("radioQuestao" + i, "id", getPackageName());
            radioGroup = (RadioGroup) findViewById(resId);
            listRadioGroup.add(radioGroup);
        }


    }

    private void verificar_questoes() {
        int numQuestao = 1;
        boolean tdsAsQuestoesOk = true;
        for (RadioGroup rg : listRadioGroup) {

            int selectedIdQuestao = rg.getCheckedRadioButtonId();

            radioButton = (RadioButton) findViewById(selectedIdQuestao);

            if (radioButton == null) {
                Toast.makeText(FormularioPerfilJogadorActivity.this,
                        "A quest������o "+numQuestao +" est������ vazia", Toast.LENGTH_SHORT).show();
                tdsAsQuestoesOk = false;
                break;
            }
            else{
                mapaQuestoes.put(numQuestao, rg.indexOfChild(radioButton) == 1 ? "a" : "b");
            }

            numQuestao++;
        }
        if (tdsAsQuestoesOk){
            definirPerfil();
        }
    }

    private void iniciar_mapas(){
        mapaAtiRef = new HashMap<String, Integer>();
        mapaAtiRef.put("a", 0);
        mapaAtiRef.put("b", 0);
        mapaSenInt = new HashMap<String, Integer>();
        mapaSenInt.put("a", 0);
        mapaSenInt.put("b", 0);
        mapaVisVer = new HashMap<String, Integer>();
        mapaVisVer.put("a", 0);
        mapaVisVer.put("b", 0);
        mapaSeqGlo = new HashMap<String, Integer>();
        mapaSeqGlo.put("a", 0);
        mapaSeqGlo.put("b", 0);
        resultPerfil = new LinkedList<String>();
    }

    private void definirPerfil(){
        iniciar_mapas();
        for (Entry<Integer, String> entry : mapaQuestoes.entrySet()) {
            //ati/ref
            String key = "";
            if((entry.getKey() == 1) || (entry.getKey() == 5) || (entry.getKey() == 9) || (entry.getKey() == 13) || (entry.getKey() == 17)) {
                key = entry.getValue().toString();

                int value = mapaAtiRef.get(key);
                value++;
                mapaAtiRef.put(key, value);

            }
            //sen/int
            else if((entry.getKey() == 2) || (entry.getKey() == 6) || (entry.getKey() == 10) || (entry.getKey() == 14) || (entry.getKey() == 18)) {
                key = entry.getValue().toString();

                int value = mapaSenInt.get(key);
                value++;
                mapaSenInt.put(key, value);
            }
            //vis/ver
            else if((entry.getKey() == 3) || (entry.getKey() == 7) || (entry.getKey() == 11) || (entry.getKey() == 15) || (entry.getKey() == 19)) {
                key = entry.getValue().toString();

                int value = mapaVisVer.get(key);
                value++;
                mapaVisVer.put(key, value);
            }
            //seq/glo
            else if((entry.getKey() == 4) || (entry.getKey() == 8) || (entry.getKey() == 12) || (entry.getKey() == 16) || (entry.getKey() == 20)) {
                key = entry.getValue().toString();

                int value = mapaSeqGlo.get(key);
                value++;
                mapaSeqGlo.put(key, value);
            }
        }

        resultPerfil.add(result_escores(mapaAtiRef));
        resultPerfil.add(result_escores(mapaSenInt));
        resultPerfil.add(result_escores(mapaVisVer));
        resultPerfil.add(result_escores(mapaSeqGlo));

        FController.getInstance().salvarPerfil(resultPerfil, FController.jogador.getId(), getApplicationContext());
        
        Intent i = new Intent(this, MyQuimicaActivity.class);
        startActivity(i);
    }

    private String result_escores(Map<String, Integer> map) {
        int a = map.get("a");
        int b = map.get("b");
        String letra = "";
        int result;
        if(a > b){
            result = a-b;
            letra = "a";
        }
        else{
            result = b-a;
            letra = "b";
        }

        letra = String.valueOf(result)+letra;
        return letra;
    }
}
