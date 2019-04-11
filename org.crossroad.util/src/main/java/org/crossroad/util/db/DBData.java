/**
 * 
 */
package org.crossroad.util.db;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * @author e.soden
 *
 */
public class DBData implements IDBData {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name = null;
	private Object data = null;
	private DBType type = DBType.UNKNOWN;

	/**
	 * 
	 */
	public DBData() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.tw.gk.archiving.db.document.IDBData#getData()
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.tw.gk.archiving.db.document.IDBData#getType()
	 */
	public DBType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(DBType type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public String getValueAsString() {
		return (this.data != null) ? this.data.toString() : null;
	}

	@Override
	public synchronized void writeExternal(ObjectOutput out) throws IOException {
		out.writeUTF(name);
		out.writeObject(data);
		out.writeUTF(type.name());
	}

	@Override
	public synchronized void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		name = in.readUTF();
		data = in.readObject();		
		type = DBType.valueOf(in.readUTF());
		
	}

}
