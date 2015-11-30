package fibonacci;

import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.NumberFormatter;

/**
 * Das ist die einzige Klasse des Programms und steuert alle Ablaeufe der Fibonaccizahlenberechnung.
 * @author Lukas Schramm
 * @version 1.0
 */
public class Fibonacci {

	private NumberFormat format = NumberFormat.getInstance(); 
	private NumberFormatter formatter = new NumberFormatter(format);
	private long abfragezahl;
	
	public Fibonacci() {
		format.setGroupingUsed(false); 
		formatter.setAllowsInvalid(false);
		eingabe();
	}
	
	/**
	 * Diese Methode nimmt die Zahl in einem Eingabefeld an, die der Nutzer eingibt.<br>
	 * Wenn der Nutzer nichts oder unnatuerliche Zahlen eingibt, wird eine Fehlermeldung ausgegeben.<br>
	 * Die Methode leitet direkt zu ausgabe() weiter, wo ermittelt wird, was dem Nutzer ausgegeben wird.
	 */
	private void eingabe() {
		JFormattedTextField nummernfeld = new JFormattedTextField(formatter);
		Object[] zahlenfrage = {"Die wie vielte Fibonaccizahl möchtest Du wissen?", nummernfeld};
		JOptionPane pane = new JOptionPane(zahlenfrage, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION);
		pane.createDialog(null, "Eingabe").setVisible(true);
		String zahlStr = nummernfeld.getText();
		try {
			if(zahlStr.equals("")) {
				JOptionPane.showMessageDialog(null, "Bitte gib eine natürliche Zahl ein!", "Ungültige Eingabe", JOptionPane.ERROR_MESSAGE);
				eingabe();
			} else {
				abfragezahl = Long.parseLong(zahlStr);
				if(abfragezahl < 1) {
					JOptionPane.showMessageDialog(null, "Bitte gib eine natürliche Zahl ein!", "Ungültige Eingabe", JOptionPane.ERROR_MESSAGE);
					eingabe();
				} else {
					ausgabe();
				}
			}
		} catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Bitte gib eine natürliche Zahl ein!", "Ungültige Eingabe", JOptionPane.ERROR_MESSAGE);
			eingabe();
		}
	}
	
	/**
	 * Diese Methode berechnet die gegebene Fibonaccizahl.<br>
	 * Um Ueberlauf zu vermeiden bricht das Programm ab einer bestimmten Zahlengroesse ab und fragt nach kleineren Zahlen.
	 * @param zahl Nimmt die abgefragte Zahl entgegen.
	 * @return Gibt eine Fibonaccizahl als long aus.
	 */
	private long berechnung(long zahl) {
		long fibonacci=1, fibonacci1=1, fibonacci2=1;
		if(zahl == 1 || zahl == 2) {
			return 1;
		}
		for(int n=3;n<=zahl;n++) {
			fibonacci = fibonacci1 + fibonacci2;
			if(fibonacci > 100000000000000000L) {
				JOptionPane.showMessageDialog(null, abfragezahl+" ist zu groß, um verarbeitet zu werden.\nBitte wähle eine kleinere Zahl!", "Ungültige Eingabe", JOptionPane.PLAIN_MESSAGE);
				fibonacci=1;fibonacci1=1;fibonacci2=1;
				eingabe();
			}
			fibonacci2 = fibonacci1;
			fibonacci1 = fibonacci;
		}
		return fibonacci;
	}
	
	/**
	 * Diese Methode gibt dem Nutzer das Ergebnis der Fibonaccizahlenberechnung aus und leitet ihn anschliessend in die neustart()-Methode weiter.
	 */
	private void ausgabe() {
		JOptionPane.showMessageDialog(null, "Die "+abfragezahl+". Fibonaccizahl ist die "+berechnung(abfragezahl), "Fibonacci", JOptionPane.PLAIN_MESSAGE);
		neustart();
	}
	
	/**
	 * Diese Methode fragt den Nutzer, ob er eine weitere Zahl ueberpruefen moechte und startet je nach Nutzereingabe entweder eine neue Abfrage oder beendet das Programm.
	 */
	private void neustart() {
		int dialogneustart = JOptionPane.showConfirmDialog(null, "Möchtest Du eine weitere Fibonaccizahl wissen?", "Neue Abfrage?", JOptionPane.YES_NO_OPTION);
        if(dialogneustart == 0) {
     	   new Fibonacci();
        } else {
     	   System.exit(0);
        }
	}
	
	public static void main(String[] args) {
		new Fibonacci();
	}
}