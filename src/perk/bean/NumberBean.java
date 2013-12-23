package perk.bean;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

//import javax.faces.bean.ViewScoped;

/**
 * @author mperk
 * 
 */
@ManagedBean(name = "numberBean")
@RequestScoped
public class NumberBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private double output;
	private String repeat;
	BigInteger number2 = new BigInteger("0");
	ArrayList<Integer> digList = new ArrayList<Integer>();

	public void Send() {

		output = 1 / number2.doubleValue();
		String tempStr = Double.toString(output);
		if (tempStr.length() > 17) {
			tempStr = tempStr.substring(2, 18);
			digList = MyCycle(new BigInteger(tempStr), digList);
			StringBuilder builder = new StringBuilder();

			for (int i = 0; i < digList.size(); i++) {
				builder.append(" ").append(digList.get(i)).append(" ");
				// builder.replace();
			}

			repeat = builder.toString();
		}

	}

	private static ArrayList<Integer> MyCycle(BigInteger number,
			ArrayList<Integer> digList) {

		if (digList.size() == 0) {

			digList.add(number.mod(BigInteger.valueOf(10)).intValue());
			// number'ýn sonundaki rakamý aldým.
			number = number.divide(BigInteger.valueOf(10));
			MyCycle(number, digList);

		} else {
			int lastValue = number.mod(BigInteger.valueOf(10)).intValue();
			if (lastValue == digList.get(0)) {

				int ex = (int) Math.pow(10, digList.size());
				int temp = number.mod(BigInteger.valueOf(ex)).intValue();
				// number'ýn bana lazým olan rakamlarýný yakalýyorum.
				int i = 0;

				while (i < digList.size()) {
					if (digList.get(i) == temp % 10) {

						temp = temp / 10;
						if (digList.size() - 1 == i) {
							// tekrar bulundu.
							Collections.reverse(digList);

							// System.out.println("Tekrar edilen kýsým:");
							// System.out.println(digList);
							return digList;
						}
						i++;
					} else {
						digList.add(number.mod(BigInteger.valueOf(10))
								.intValue());
						number = number.divide(BigInteger.valueOf(10));
						MyCycle(number, digList);
						return digList;// bunun sebebi kod recursive yapýdan tam
						// çýkmýyor.
						// number'ýn son rakamý diziye ekleniyor ve
						// number'dan o rakam atýlýyor.
					}
				}

			} else {
				digList.add(number.mod(BigInteger.valueOf(10)).intValue());
				number = number.divide(BigInteger.valueOf(10));
				if (number.intValue() == 0) { // tekrar yoksa
					digList.clear();
				} else {
					MyCycle(number, digList);
				}
				// number'ýn son rakamý diziye ekleniyor ve
				// number'dan o rakam atýlýyor.
			}
		}
		return digList;
	}

	public BigInteger getNumber2() {
		return number2;
	}

	public void setNumber2(BigInteger number2) {
		this.number2 = number2;
	}

	public ArrayList<Integer> getDigList() {
		return digList;
	}

	public void setDigList(ArrayList<Integer> digList) {
		this.digList = digList;
	}

	public double getOutput() {
		return output;
	}

	public String getRepeat() {
		return repeat;
	}

	public void setRepeat(String repeat) {
		this.repeat = repeat;
	}

}
