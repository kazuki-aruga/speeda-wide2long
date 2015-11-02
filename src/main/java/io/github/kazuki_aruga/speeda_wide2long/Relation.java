package io.github.kazuki_aruga.speeda_wide2long;

import org.apache.commons.csv.CSVRecord;

/**
 * 
 * @author kazuki
 *
 */
public interface Relation {

	String getValue(CSVRecord record);
}
