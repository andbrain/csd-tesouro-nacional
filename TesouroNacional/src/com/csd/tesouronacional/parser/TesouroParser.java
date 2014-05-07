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

	public static List<Titulo> parseTesouroURL() throws IOException,
			ParseException {
		String content = getURLContent(TESOURO_URL);
		return parseContent(content);
	}

	private static List<Titulo> parseContent(String content)
			throws ParseException {
		List<Titulo> titulos = new ArrayList<Titulo>();
		int startTitulo = 0;
		int endTitulo;
		for (int i = 0; i < 24; i++) {
			try {
				Titulo t = new Titulo();
				String tituloKey = "<TD  class=\"listing0\" align=left>";
				startTitulo = content.indexOf(tituloKey, startTitulo);
				endTitulo = content.indexOf("</TD>", startTitulo);
				t.setLetra(content.substring(startTitulo + tituloKey.length(),
						endTitulo));

				String vencimentoKey = "<TD";
				int startVencimento = content.indexOf(vencimentoKey, endTitulo);
				int endVencimento = content.indexOf("</TD>", startVencimento);
				startVencimento = content.indexOf('>', startVencimento) + 1;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				t.setVencimento(sdf.parse(content.substring(startVencimento,
						endVencimento)));

				String taxaCompraKey = "<TD";
				int startTaxaCompra = content.indexOf(taxaCompraKey,
						endVencimento);
				int endTaxaCompra = content.indexOf("</TD>", startTaxaCompra);
				startTaxaCompra = content.indexOf('>', startTaxaCompra) + 1;
				String strTaxaCompra = content.substring(startTaxaCompra,
						endTaxaCompra - 1);
				strTaxaCompra = strTaxaCompra.replace(',', '.');
				t.setTaxaCompra(Double.parseDouble(strTaxaCompra));

				String taxaVendaKey = "<TD";
				int startTaxaVenda = content.indexOf(taxaVendaKey,
						endTaxaCompra);
				int endTaxaVenda = content.indexOf("</TD>", startTaxaVenda);
				startTaxaVenda = content.indexOf('>', startTaxaVenda) + 1;

				String valorCompraKey = "<TD";
				int startValorCompra = content.indexOf(valorCompraKey,
						endTaxaVenda);
				int endValorCompra = content.indexOf("</TD>", startValorCompra);
				startValorCompra = content.indexOf('>', startValorCompra) + 1;
				String strValorCompra = content.substring(startValorCompra + 3,
						endValorCompra);
				strValorCompra = strValorCompra.replace(".", "").replace(',',
						'.');
				t.setPrecoCompra(Double.parseDouble(strValorCompra));

				Log.e("Conteudo::", t.toString());

				titulos.add(t);
			} catch (NumberFormatException e) {
				startTitulo++;
				continue;
			}
			startTitulo++;
		}
		return titulos;
	}

	private static String getURLContent(String url) throws IOException {
		URL yahoo = new URL(url);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				yahoo.openStream()));

		StringBuilder sb = new StringBuilder();
		String inputLine;
		while ((inputLine = in.readLine()) != null)
			sb.append(inputLine);
		in.close();

		System.out.println("Arquivo::" + sb.length());

		return sb.toString();
	}

}
