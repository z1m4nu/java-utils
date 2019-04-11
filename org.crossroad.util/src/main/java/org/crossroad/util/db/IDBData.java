package org.crossroad.util.db;

import java.io.Externalizable;

public interface IDBData extends Externalizable {

	/**
	 * @return the data
	 */
	Object getData();

	/**
	 * @return the type
	 */
	DBType getType();

	/**
	 * 
	 * @return
	 */
	String getName();

	String getValueAsString();
}