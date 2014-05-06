package com.csd.tesouronacional.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.csd.tesouronacional.model.Titulo;

public class TesouroParser {

    private static final String TESOURO_URL = "http://www3.tesouro.gov.br/tesouro_direto/consulta_titulos_novosite/consultatitulos.asp";

    public static List<Titulo> parseTesouroURL() throws IOException, ParseException {
        String content = getURLContent(TESOURO_URL);
        return parseContent(content);
    }

    private static List<Titulo> parseContent(String content) throws ParseException {
        List<Titulo> titulos = new ArrayList<Titulo>();
        int startTitulo = 0;
        int endTitulo;
        for (int i = 0; i < 10; i++) {
            Titulo t = new Titulo();

            String tituloKey = "<TD  class=\"listing0\" align=left>";
            startTitulo = content.indexOf(tituloKey, startTitulo);
            endTitulo = content.indexOf("</TD>", startTitulo);
            t.setLetra(content.substring(startTitulo + tituloKey.length(), endTitulo));

            String vencimentoKey = "<TD  class=\"listing\" align=right>";
            int startVencimento = content.indexOf(vencimentoKey, endTitulo);
            int endVencimento = content.indexOf("</TD>", startVencimento);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            t.setVencimento(sdf.parse(content.substring(startVencimento + vencimentoKey.length(), endVencimento)));

            String taxaKey = "<TD  class=\"listing\" align=right>";
            int startTaxa = content.indexOf(taxaKey, endVencimento);
            int endTaxa = content.indexOf("</TD>", startTaxa);
            String strTaxa = content.substring(startTaxa + taxaKey.length(), endTaxa - 1);
            strTaxa = strTaxa.replace(',', '.');
            t.setTaxaCompra(Double.parseDouble(strTaxa));

            String valorKey = "<TD class=\"listing\" align=right>";
            int startValor = content.indexOf(valorKey, endTaxa);
            int endValor = content.indexOf("</TD>", startValor);
            String strValor = content.substring(startValor + 3 + valorKey.length(), endValor);
            strValor = strValor.replace(".", "").replace(',', '.');
            t.setPrecoCompra(Double.parseDouble(strValor));

            Log.d("tesouro", t.toString());
            startTitulo++;
        }
        return titulos;
    }

    private static String getURLContent(String url) throws IOException {
        URL yahoo = new URL(url);
        BufferedReader in = new BufferedReader(new InputStreamReader(yahoo.openStream()));

        StringBuilder sb = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            sb.append(inputLine);
        in.close();

        return sb.toString();
    }

}
