/**
 * 
 */
package io.github.kazuki_aruga.speeda_wide2long;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.csv.CSVRecord;

/**
 * CSVの対応関係を保持するクラス。
 * 
 * @author kazuki
 *
 */
public class Mappings {

	private List<String> header = new ArrayList<>();

	private List<OutputSchema> outputs = new ArrayList<>();

	public Mappings(CSVRecord schemaRecord, CSVRecord periodRecord) {

		// 共通部のテンプレートを保持する
		final OutputSchema commonSchema = new OutputSchema();
		// 期と出力スキーマの対応関係を保持する
		final Map<String, OutputSchema> schemaMap = new TreeMap<>();

		int phase = 0; // 0:共通部取得フェーズ, 1:期増殖フェーズ, 2:項目読み込みフェーズ

		for (int i = 0; i < schemaRecord.size(); i++) {

			final String schema = schemaRecord.get(i);
			final String period = periodRecord.get(i);

			switch (phase) {
			case 0:

				if (!schema.isEmpty() && !period.isEmpty()) {

					header.add("期");
					header.add(schema);
					createNextPeriodSchema(commonSchema, schemaMap, period, i);

					phase++;

				} else {

					header.add(schema);
					commonSchema.addCorrespond(i);
				}

				break;

			case 1:

				if (!schema.isEmpty() && !period.isEmpty()) {

					header.add(schema);
					addCorrespond(schemaMap, period, i);

					phase++;

				} else {

					createNextPeriodSchema(commonSchema, schemaMap, period, i);
				}

				break;

			case 2:

				if (!schema.isEmpty() && !period.isEmpty()) {

					header.add(schema);

				}

				addCorrespond(schemaMap, period, i);

				break;
			}
		}
	}

	public void addCorrespond(Map<String, OutputSchema> schemaMap, String period, int i) {

		if (!period.contains("変則決算")) {

			schemaMap.get(period).addCorrespond(i);
		}
	}

	public void createNextPeriodSchema(OutputSchema commonSchema, Map<String, OutputSchema> schemaMap, String period,
			int i) {

		if (!period.contains("変則決算")) {

			final OutputSchema os = commonSchema.clone();
			outputs.add(os);
			schemaMap.put(period, os);

			os.addCorrespond(period);
			os.addCorrespond(i);
		}
	}

	public List<String> getHeader() {

		return header;
	}

	/**
	 * 
	 * @param record
	 * @return
	 */
	public List<List<String>> getRecords(CSVRecord record) {

		final List<List<String>> result = new ArrayList<>();

		for (OutputSchema out : outputs) {

			result.add(out.getRecord(record));
		}

		return result;
	}

}
