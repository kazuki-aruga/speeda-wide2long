/**
 * 
 */
package io.github.kazuki_aruga.speeda_wide2long;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

/**
 * SPEEDAからダウンロードしたWIDE形式のデータとLONG形式のデータに変換する。
 * 
 * @author kazuki aruga
 */
public class Main {

	/**
	 * プログラムのエントリーポイント。
	 * 
	 * @param args
	 *            プログラム引数。
	 *            <ol>
	 *            <li>CSVファイルのパス。</li>
	 *            </ol>
	 * @throws IOException
	 *             入出力でエラーが発生した。
	 */
	public static void main(String[] args) throws IOException {

		try (Reader reader = new FileReader(args[0])) {

			parse(reader, System.out);
		}
	}

	/**
	 * 
	 * @param reader
	 * @param out
	 * @throws IOException
	 */
	public static void parse(Reader reader, Appendable out) throws IOException {

		final CSVParser parser = new CSVParser(reader, CSVFormat.EXCEL);
		final CSVPrinter printer = new CSVPrinter(out, CSVFormat.EXCEL);

		try {

			CSVRecord csvheader = null;
			Mappings mappings = null;

			end: for (CSVRecord record : parser) {

				final long recordNumber = parser.getRecordNumber();

				// 1～6行と9行目は無視
				// 7,8行目がCSVのヘッダ
				// 7行目が出力項目
				// 8行目は年度
				// 10行目以降で空の行が存在したら処理を終了
				if (recordNumber < 7 || recordNumber == 9) {

					continue;

				} else if (recordNumber == 7) {

					csvheader = record;

				} else if (recordNumber == 8) {

					mappings = new Mappings(csvheader, record);

					printer.printRecord(mappings.getHeader());

				} else {

					if (isEmptyAll(record)) {

						break end;
					}

					printer.printRecords(mappings.getRecords(record));
				}
			}

		} finally {

			printer.flush();
		}
	}

	/**
	 * CSVの1レコードが空行かどうかを確認する。
	 * 
	 * @param record
	 * @return
	 */
	public static boolean isEmptyAll(CSVRecord record) {

		for (String item : record) {

			if (!item.isEmpty()) {

				return false;
			}
		}

		return true;
	}

}
