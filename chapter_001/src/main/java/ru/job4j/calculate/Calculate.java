package ru.job4j.calculate;

/**
* Print specified word three times.
*
 * @author Andreev Aleksandr (andreev.aleksandr.spb@gmail.com)
 * @version 1
 * @since 21.05.2019
*/
public class Calculate {
	
	/**
	* Main method.
	* @param args arguments to run the program.
	*/
    public static void main(String[] args) {
		Calculate calc = new Calculate();
		System.out.println(calc.echo("hey"));
    }
	
	/**
	* Сreate a new string based on input argument.
	* @param value string for further transformations.
	* @return string consisting of repeated three times the value of the argument.  
	*/
	public String echo(String value) {
			return String.format("%s %s %s", value, value, value);
	}	
}