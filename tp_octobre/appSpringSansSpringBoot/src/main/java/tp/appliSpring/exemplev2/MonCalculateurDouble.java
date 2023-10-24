package tp.appliSpring.exemplev2;

import org.springframework.stereotype.Component;

import tp.annot.LogExecutionTime;


@LogExecutionTime
public class MonCalculateurDouble implements MonCalculateur {

	@Override
	public double calculer(double x) {
		return 2*x;
	}

}
