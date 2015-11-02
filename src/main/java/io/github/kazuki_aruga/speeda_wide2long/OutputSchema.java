package io.github.kazuki_aruga.speeda_wide2long;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

public class OutputSchema implements Cloneable {

	private List<Relation> corresponds = new ArrayList<>();

	public void addCorrespond(int index) {

		corresponds.add(new IndexedRelation(index));
	}

	public void addCorrespond(String value) {

		corresponds.add(new FixedRelation(value));
	}

	public List<String> getRecord(CSVRecord record) {

		final List<String> result = new ArrayList<>(corresponds.size());

		for (Relation cor : corresponds) {

			result.add(cor.getValue(record));
		}

		return result;
	}

	public OutputSchema clone() {

		try {

			final OutputSchema obj = (OutputSchema) super.clone();

			obj.corresponds = new ArrayList<>(corresponds);

			return obj;

		} catch (CloneNotSupportedException e) {

			return null;
		}
	}

}
