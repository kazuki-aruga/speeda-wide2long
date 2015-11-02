/**
 * 
 */
package io.github.kazuki_aruga.speeda_wide2long;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import org.junit.Test;

/**
 * @author kazuki
 *
 */
public class MainTest {

	/**
	 * Test method for
	 * {@link io.github.kazuki_aruga.speeda_csv_wide2long.Main#parse(java.io.Reader)}
	 * .
	 * 
	 * @throws IOException
	 */
	@Test
	public void testParse1() throws IOException {

		try (Reader reader = new InputStreamReader(ClassLoader.getSystemResourceAsStream("testdata1.csv"),
				Charset.forName("MS932"))) {

			Main.parse(reader, System.out);
		}
	}

	/**
	 * 
	 * @throws IOException
	 */
	@Test
	public void testParse2() throws IOException {

		try (Reader reader = new InputStreamReader(ClassLoader.getSystemResourceAsStream("testdata2.csv"),
				Charset.forName("MS932"))) {

			Main.parse(reader, System.out);
		}
	}

}
