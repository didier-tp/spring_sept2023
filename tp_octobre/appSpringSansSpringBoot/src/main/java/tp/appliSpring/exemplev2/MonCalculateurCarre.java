package tp.appliSpring.exemplev2;

import org.springframework.stereotype.Component;

import tp.annot.LogExecutionTime;

@LogExecutionTime
public class MonCalculateurCarre implements MonCalculateur {

	@Override
	public double calculer(double x) {
		//if(x<10) throw new RuntimeException("x trop petit");
		return x*x;
	}

}
