/**
 * 
 */
package io.github.kazuki_aruga.speeda_wide2long;

import org.apache.commons.csv.CSVRecord;

/**
 * 固定値を取得することをあらわすクラス。
 * 
 * @author kazuki
 */
public class FixedRelation implements Relation {

	/**
	 * 値。
	 */
	private final String value;

	/**
	 * 固定値からインスタンスを生成する。
	 * 
	 * @param value
	 */
	public FixedRelation(String value) {

		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.github.kazuki_aruga.speeda_csv_wide2long.Correspond#getValue(org.
	 * apache.commons.csv.CSVRecord)
	 */
	@Override
	public String getValue(CSVRecord record) {

		return value;
	}

}
