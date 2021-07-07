/* Created by Adam Jost May 2020 */ 

package grayscale.converter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ColorToGrayscale {
	
	public static void main (String[] args) throws IOException {
		//Input and output streams
		FileInputStream fin = new FileInputStream("colorImage.ppm");
		FileOutputStream fout = new FileOutputStream("grayscaleImage.ppm");
		Scanner scanner = new Scanner(fin);
		PrintWriter writer = new PrintWriter(fout);
		
		// Image details
		
		// P3 - This signifies that it is a PPM 
		// (Portable PixMap) image.
		final String MAGIC_NUMBER = scanner.nextLine();
		// The number of pixels per row in the image.
		int pixelPerRow = scanner.nextInt();
		// The number of rows in the image.
		int rows = scanner.nextInt();
		// Color depth (e.g. 255)
		final int COLOR_DEPTH = scanner.nextInt();
				
		// Used for the conversion.
		int red, green, blue;	
		
		// Write the start of the grayscale image file.
		writer.printf("%s\n%d %d\n%d\n", MAGIC_NUMBER, pixelPerRow, 
				rows, COLOR_DEPTH);
		
		//Until the end of file is reached
		while (scanner.hasNextInt()) {
			// Read in individual color depth
			// values of each color from the image.
			red = scanner.nextInt();
			green = scanner.nextInt();
			blue = scanner.nextInt();
			//Perform color to gray scale conversion 
			red = green = blue = (int) (0.3 * red + 0.59 * green + 0.11 * blue);
			//Write the following converted values to the new .ppm file
			writer.printf("%d\n%d\n%d\n", red, green, blue);
		}
		
		//Close streams
		scanner.close();
		writer.close();
		fin.close();
		fout.close();
		
	}
}
