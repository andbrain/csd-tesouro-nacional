package com.csd.tesouronacional.parser;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import android.test.ActivityTestCase;

import com.csd.tesouronacional.model.Titulo;

public class TesteParser extends ActivityTestCase {

	public void testParsing() throws IOException, ParseException {
		List<Titulo> titulos = TesouroParser.parseTesouroURL();
		assertNotNull(titulos);
		assertNotSame(titulos.size(), 0);

	}

}
