package org.crossroad.util.job;

import java.util.Map;


import org.crossroad.util.ITimer;
import org.crossroad.util.Status;

public interface ITask extends ITimer{

	String getId();

	Status getStatus();

	Object getContent();
	
	
	


}