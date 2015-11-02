/**
 * 
 */
package io.github.kazuki_aruga.speeda_wide2long;

import org.apache.commons.csv.CSVRecord;

/**
 * インデックスを指定して値を取得することをあらわすクラス。
 * 
 * @author kazuki aruga
 */
public class IndexedRelation implements Relation {

	/**
	 * 取得するインデックス。
	 */
	private int index;

	/**
	 * 
	 * @param index
	 */
	public IndexedRelation(int index) {

		this.index = index;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.github.kazuki_aruga.speeda_csv_wide2long.Correspond#getValue(org.
	 * apache.commons.csv.CSVRecord)
	 */
	@Override
	public String getValue(CSVRecord record) {

		return record.get(index);
	}

}
