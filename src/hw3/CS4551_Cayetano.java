package hw3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CS4551_Cayetano {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String menu = "\n\nMain Menu\n" + "---------------------------------------------\r\n"
				+ "1. Block-Based Motion Compensation \r\n" + "2. Half-Pixel Block-Based Motion Compensation \r\n"
				+ "3. Removing Moving Objects\r\n" + "4. Quit\r\n"
				+ "----------------------------------------------\r\n" + "";

		Scanner in = new Scanner(System.in);
		int choice = -1;
		do {
			System.out.println(menu);
			choice = in.nextInt();

			switch (choice) {
			case 1:
				int macroblock = -1;
				int p = -1;
				int target = -1;
				String targetS = "";
				int ref = -1;
				String refS = "";

				do {
					System.out.println("\n\nPass in target image frame in form of: 1 - 200\n For example: '54'");
					target = in.nextInt();
					if (target > 0 && target <= 9) {
						targetS = "Walk_00" + target + ".ppm";

					}
					if (target >= 10 && target <= 99) {
						targetS = "Walk_0" + target + ".ppm";
					}
					if (target >= 100 && target <= 200) {
						targetS = "Walk_" + target + ".ppm";
					}

				} while (target < 1 || target > 200);

				do {
					System.out.println("\n\nPass in reference image frame in form of: 1 - 200\n For example: '54'");
					ref = in.nextInt();
					if (ref > 0 && ref <= 9) {
						refS = "Walk_00" + ref + ".ppm";

					}
					if (ref >= 10 && ref <= 99) {
						refS = "Walk_0" + ref + ".ppm";
					}
					if (ref >= 100 && ref <= 200) {
						refS = "Walk_" + ref + ".ppm";
					}

				} while (ref < 1 || ref > 200);

				do {
					System.out.println("\n\nPass in MacroBlock size (8, 16, 24): ");
					macroblock = in.nextInt();
				} while (macroblock != 8 && macroblock != 16 && macroblock != 24);

				do {
					System.out.println("\nPass in p value (2, 4, 8, 12, 16): ");
					p = in.nextInt();
				} while (p != 2 && p != 4 && p != 8 && p != 12 && p != 16);
				MImage currentImage = new MImage(targetS);
				MImage refImage = new MImage(refS);

				MImage curGrayImage = colorToGray(currentImage);
				MImage refGrayImage = colorToGray(refImage);

				motionVector(curGrayImage, refGrayImage, macroblock, p, currentImage, refImage);

				break;
			case 2:
				int macroblock1 = -1;
				int p1 = -1;
				int target1 = -1;
				String targetS1 = "";
				int ref1 = -1;
				String refS1 = "";

				do {
					System.out.println("\n\nPass in target image frame in form of: 1 - 200\n For example: '54'");
					target1 = in.nextInt();
					if (target1 > 0 && target1 <= 9) {
						targetS1 = "Walk_00" + target1 + ".ppm";

					}
					if (target1 >= 10 && target1 <= 99) {
						targetS1 = "Walk_0" + target1 + ".ppm";
					}
					if (target1 >= 100 && target1 <= 200) {
						targetS1 = "Walk_" + target1 + ".ppm";
					}

				} while (target1 < 1 || target1 > 200);

				do {
					System.out.println("\n\nPass in reference image frame in form of: 1 - 200\n For example: '54'");
					ref1 = in.nextInt();
					if (ref1 > 0 && ref1 <= 9) {
						refS1 = "Walk_00" + ref1 + ".ppm";

					}
					if (ref1 >= 10 && ref1 <= 99) {
						refS1 = "Walk_0" + ref1 + ".ppm";
					}
					if (ref1 >= 100 && ref1 <= 200) {
						refS1 = "Walk_" + ref1 + ".ppm";
					}

				} while (ref1 < 1 || ref1 > 200);

				MImage currentImage1 = new MImage(targetS1);
				MImage refImage1 = new MImage(refS1);

				MImage curGrayImage1 = colorToGray(currentImage1);
				MImage refGrayImage1 = colorToGray(refImage1);

				do {
					System.out.println("\n\nPass in MacroBlock size (8, 16, 24): ");
					macroblock1 = in.nextInt();
				} while (macroblock1 != 8 && macroblock1 != 16 && macroblock1 != 24);

				do {
					System.out.println("Pass in p value (2, 4, 8, 12, 16): ");
					p1 = in.nextInt();
				} while (p1 != 2 && p1 != 4 && p1 != 8 && p1 != 12 && p1 != 16);
				halfMotionVector(curGrayImage1, refGrayImage1, macroblock1, p1, currentImage1, refImage1);

				break;
			case 3:
				int macroblock2 = -1;
				int p2 = -1;
				int target2 = -1;
				String targetS2 = "";
				int ref2 = -1;
				String refS2 = "";

				do {
					System.out.println("\n\nPass in target image frame in form of: 1 - 200\n For example: '1'");
					target2 = in.nextInt();
					if (target2 > 0 && target2 <= 9) {
						targetS2 = "Walk_00" + target2 + ".ppm";

					}
					if (target2 >= 10 && target2 <= 99) {
						targetS2 = "Walk_0" + target2 + ".ppm";
					}
					if (target2 >= 100 && target2 <= 200) {
						targetS2 = "Walk_" + target2 + ".ppm";
					}

				} while (target2 < 1 || target2 > 200);

				ref2 = target2 - 2;

				if (ref2 > 0 && ref2 <= 9) {
					refS2 = "Walk_00" + ref2 + ".ppm";
				}
				if (ref2 >= 10 && ref2 <= 99) {
					refS2 = "Walk_0" + ref2 + ".ppm";
				}
				if (ref2 >= 100 && ref2 <= 200) {
					refS2 = "Walk_" + ref2 + ".ppm";
				}

				MImage currentImage2 = new MImage(targetS2);
				MImage refImage2 = new MImage(refS2);

				MImage curGrayImage2 = colorToGray(currentImage2);
				MImage refGrayImage2 = colorToGray(refImage2);

				do {
					System.out.println("\n\nPass in MacroBlock size (8, 16, 24): ");
					macroblock2 = in.nextInt();
				} while (macroblock2 != 8 && macroblock2 != 16 && macroblock2 != 24);

				do {
					System.out.println("\nPass in p value (2, 4, 8, 12, 16): ");
					p2 = in.nextInt();
				} while (p2 != 2 && p2 != 4 && p2 != 8 && p2 != 12 && p2 != 16);

				System.out.println("Target: " + targetS2 + "\nRef: " + refS2);

				replace5th(curGrayImage2, refGrayImage2, macroblock2, p2, currentImage2, refImage2);// function2REPLACE
				break;
			case 4:
				System.exit(1);
				break;
			default:
				break;
			}

		} while (choice > 0 || choice < 5);
	}

	// color to grayscale
	public static MImage colorToGray(MImage img) {
		MImage image = img;
		int[] rgb = new int[3]; // rgb of pixel
		int[] grayRGB = new int[3]; // rgb in grayscale

		// loop through every pixel should eventually get to 250 x 273 (H x W)
		for (int height = 0; height < image.getH(); height++) { // increment the height
			for (int width = 0; width < image.getW(); width++) { // increment width
				// System.out.println("Ducky.ppm coords: " + "(" + width + " , " + height +
				// ")");
				image.getPixel(width, height, rgb);
				// System.out.println("R: " + rgb[0] + "G: " + rgb[1] + "B: " + rgb[2]);
				int gray = (int) Math.floor(0.299 * rgb[0] + 0.587 * rgb[1] + 0.114 * rgb[2]);
				grayRGB[0] = gray;
				grayRGB[1] = gray;
				grayRGB[2] = gray;

				image.setPixel(width, height, grayRGB);
			}
		}
		String grayFile = img.getName();
		grayFile = new StringBuffer(grayFile).insert(grayFile.length() - 4, "-gray").toString();
		image.write2PPM(grayFile);
		return image;
	}

	// Calculate motion vector & produce residual img
	public static void motionVector(MImage currentImage, MImage refImage, int macroblock, int p, MImage curr,
			MImage ref) {
		ArrayList<int[]> errorP = new ArrayList<int[]>();
		int[] errorXY = new int[2];
		// p=2;
		int randomBlock = (0 + (int) (Math.random() * ((4 - 0) + 1))); // random block chosen
		// int p = 2; //window search size
		MImage error = new MImage(currentImage.getW(), currentImage.getH());

		ArrayList<Double> motionVectors = new ArrayList<Double>();
		String header = "# Name: Miguel Cayetano\r\n" + "# Target Image name: " + currentImage.getName() + "\r\n"
				+ "# Reference Image name: " + refImage.getName() + "\r\n" + "# Macroblock size: " + macroblock + "\r\n"
				+ "# P(window) size: " + p + "\r\n\n" + "\n";
		String mv = header;

		// loop image
		int count = 0;
		int count2 = 0;

		// CHANGE Appropriate **************************HERE
		// **************************************
		for (int y = 0; y < currentImage.getH(); y += macroblock) {
			for (int x = 0; x < currentImage.getW(); x += macroblock) {
				double MSD = 10000; // reset after offsetY/X are done to find new MSD
				double mvX = -1;
				double mvY = -1;

				// set up the macroblock block. this sets the offset ready for ref img. for
				// current pixel do offset+x/y
				// Offset for Y(height)
				for (int offsetY = y - p; offsetY <= y + p; offsetY++) {
					double result = 0; // Reset after MSD is calculated

					if ((offsetY) < 0) {
						continue;
					}
					// offset for X(width)
					for (int offsetX = x - p; offsetX <= x + p; offsetX++) {
						double tempMSD = -1;

						if ((offsetX) < 0) {
							continue;
						}
						if ((offsetY) < 0) {
							continue;
						}

						// Do MSD here
						for (int individualY = 0; individualY < macroblock; individualY++) {
							for (int individualX = 0; individualX < macroblock; individualX++) {
								int currRGB[] = new int[3];
								currentImage.getPixel(x + individualX, y + individualY, currRGB);

								int refRGB[] = new int[3];
								refImage.getPixel(offsetX + individualX, offsetY + individualY, refRGB);

								result += Math.pow((currRGB[0] - refRGB[0]), 2);
								tempMSD = result;

							}
						}
						result = result / (Math.pow(macroblock, 2));
						// System.out.println("MSD: " + result);
						tempMSD = result;
						if (MSD > tempMSD) {
							MSD = tempMSD;
							mvX = x - offsetX;
							mvY = y - offsetY;
							// ERROR: e = | pixel_in_target_block – corresponding_pixel_in_the matched_block |
							errorXY[0] = (int) mvX;
							errorXY[1] = (int) mvY;

							errorP.add(errorXY);
							for (int individualY = 0; individualY < macroblock; individualY++) {
								for (int individualX = 0; individualX < macroblock; individualX++) {
									int currRGB[] = new int[3];
									currentImage.getPixel(x + individualX, y + individualY, currRGB);

									int refRGB[] = new int[3];
									refImage.getPixel(offsetX + individualX, offsetY + individualY, refRGB);

									int errorPixel = Math.abs(currRGB[0] - refRGB[0]);
									error.setPixel(x + individualX, y + individualY,
											new int[] { errorPixel, errorPixel, errorPixel });
								}
							}
						}
						count++;
					}
				}
				System.out.print(" [" + mvX + ", " + mvY + "] ");
				mv += "[" + mvX + ", " + mvY + "] ";
				count = 0;
				count2++;
			}
			System.out.println("");
			mv += "\r\n";
		}
		System.out.println("TOTAL COMPARISONS IN END: " + count2);
		error.write2PPM("ErrorBlock_" + curr.getName());

		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter("mv.txt"));
			writer.write(mv);

		} catch (IOException e) {
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
			}
		}
	}

	// Calculate motion vector & produce residual img
	public static void replace5th(MImage currentImage, MImage refImage, int macroblock, int p, MImage curr,
			MImage ref) {
		ArrayList<int[]> errorP = new ArrayList<int[]>();
		int[] errorXY = new int[2];
		MImage fifth = new MImage("Walk_005.ppm");
		MImage gray5 = colorToGray(fifth);
		MImage replacedImg = new MImage(currentImage.getW(), currentImage.getH());
		MImage staticSearch = new MImage(currentImage.getW(), currentImage.getH());
		int[] rgb5 = new int[3];
		// p=2;
		int randomBlock = (0 + (int) (Math.random() * ((4 - 0) + 1))); // random block chosen
		// int p = 2; //window search size
		MImage error = new MImage(currentImage.getW(), currentImage.getH());
		MImage replaced = new MImage(currentImage.getW(), currentImage.getH());

		ArrayList<Double> motionVectors = new ArrayList<Double>();
		String header = "# Name: Miguel Cayetano\r\n" + "# Target Image name: " + currentImage.getName() + "\r\n"
				+ "# Reference Image name: " + refImage.getName() + "\r\n" + "# Macroblock size: " + macroblock + "\r\n"
				+ "# P(window) size: " + p + "\r\n\n" + "\n";
		String mv = header;

		// loop image
		int count = 0;
		int count2 = 0;
		for (int y = 0; y < currentImage.getH(); y += macroblock) {
			for (int x = 0; x < currentImage.getW(); x += macroblock) {
				double MSD = 10000; // reset after offsetY/X are done to find new MSD
				double mvX = -1;
				double mvY = -1;

				//set up the macroblock block. this sets the offset ready for ref img. for
				// current pixel do offset+x/y
				// Offset for Y(height)
				for (int offsetY = y - p; offsetY <= y + p; offsetY++) {
					double result = 0; // Reset after MSD is calculated
					if ((offsetY) < 0) {
						continue;
					}
					// offset for X(width)  
					
					for (int offsetX = x - p; offsetX <= x + p; offsetX++) {
						double tempMSD = -1;

						if ((offsetX) < 0) {
							continue;
						}
						if ((offsetY) < 0) {
							continue;
						}

						// Do MSD here, create loops here i believe
						for (int individualY = 0; individualY < macroblock; individualY++) {
							for (int individualX = 0; individualX < macroblock; individualX++) {
								int currRGB[] = new int[3];
								currentImage.getPixel(x + individualX, y + individualY, currRGB);

								int refRGB[] = new int[3];
								refImage.getPixel(offsetX + individualX, offsetY + individualY, refRGB);

								result += Math.pow((currRGB[0] - refRGB[0]), 2);
								tempMSD = result;

							}
						}
						result = result / (Math.pow(macroblock, 2));
						// System.out.println("MSD: " + result);
						tempMSD = result;
						if (MSD > tempMSD) {
							MSD = tempMSD;
							mvX = x - offsetX;
							mvY = y - offsetY;
							// ERROR: e = | pixel_in_target_block – corresponding_pixel_in_the matched_block
							// |
							errorXY[0] = (int) mvX;
							errorXY[1] = (int) mvY;

							errorP.add(errorXY);
							for (int individualY = 0; individualY < macroblock; individualY++) {
								for (int individualX = 0; individualX < macroblock; individualX++) {
									int currRGB[] = new int[3];
									currentImage.getPixel(x + individualX, y + individualY, currRGB);

									int refRGB[] = new int[3];
									refImage.getPixel(offsetX + individualX, offsetY + individualY, refRGB);

									int errorPixel = Math.abs(currRGB[0] - refRGB[0]);
									// SEE IF MV ARE [0,0]
									if (mvX == 0 && mvY == 0) {
										gray5.getPixel(x + individualX, y + individualY, rgb5);
										error.setPixel(x + individualX, y + individualY, currRGB);
										replacedImg.setPixel(x + individualX, y + individualY, currRGB);
									} else {
										gray5.getPixel(x + individualX, y + individualY, rgb5);
										error.setPixel(x + individualX, y + individualY,
												new int[] { 255, currRGB[1], currRGB[2] });
										replacedImg.setPixel(x + individualX, y + individualY, rgb5);

									}
								}
							}
						}
						count++;
					}

				}
				System.out.print(" [" + mvX + ", " + mvY + "] ");
				mv += "[" + mvX + ", " + mvY + "] ";
				count = 0;
				count2++;
				// LOOK FOR STATIC ONCE REACHED A DYNAMIC
				for (int individualY = 0; individualY < macroblock; individualY++) {
					for (int individualX = 0; individualX < macroblock; individualX++) {
						int currRGB[] = new int[3];
						int refRGB[] = new int[3];
						error.getPixel(individualX + x, individualY + y, currRGB);
						int errorPixel = Math.abs(currRGB[0] - refRGB[0]);
						// SEE IF MV ARE [0,0], if so, then no changes

						if (mvX != 0 || mvY != 0) {
							int xC = 2;
							int yC = 1;
							int xPixel = x + individualX;
							int yPixel = y + individualY;
							while (true) {
								error.getPixel(xPixel, yPixel, rgb5);
								if (rgb5[0] != 255) {
									staticSearch.setPixel(x + individualX, y + individualY, rgb5);
									break;
								}

								if (xC % 2 == 0) {
									xPixel -= macroblock;
									yC++;
									xC++;
								} else if (yC % 2 == 0) {
									yPixel -= macroblock;
									yC++;
									xC++;
								}
							}
						}
						// else change
						else {
							error.getPixel(x + individualX, y + individualY, currRGB);
							staticSearch.setPixel(x + individualX, y + individualY, currRGB);
						}
					}
				}
			}
			System.out.println("");
			mv += "\r\n";
		}
		System.out.println("TOTAL COMPARISONS IN END: " + count2);
		error.write2PPM("red_" + curr.getName());
		replacedImg.write2PPM("replaced_" + curr.getName());
		staticSearch.write2PPM("staticS.ppm");
	}

	// Half Pixel. **NOTE** Not working as intended. Believe math error in
	// calculatin half pixel avg
	public static void halfMotionVector(MImage currentImage, MImage refImage, int macroblock, int p, MImage curr, MImage ref) {
		ArrayList<int[]> errorP = new ArrayList<int[]>();
		int[] errorXY = new int[2];
		MImage error = new MImage(currentImage.getW(), currentImage.getH());

		// loop image
		int count = 0;
		int count2 = 0;
		int counterTot = 0;

		// CHANGE Appropriate **************************HERE
		// **************************************
		for (int y = 0; y < currentImage.getH(); y += macroblock) {
			for (int x = 0; x < currentImage.getW(); x += macroblock) {
				double MSD = 10000; // reset after offsetY/X are done to find new MSD
				double mvX = -1;
				double mvY = -1;
				double offsetPixel = 0;

				// set up the macroblock block. this sets the offset ready for ref img. for
				// current pixel do offset+x/y
				// Offset for Y(height)
				for (double offsetY = y - p; offsetY <= y + p; offsetY += 0.5) {
					double result = 0; // Reset after MSD is calculated

					if ((offsetY < 0)) {
						// System.out.println("THIS IS <0 : Y:" + (offsetY+y));
						continue;
					}
					// offset for X(width)
					for (double offsetX = x - p; offsetX <= x + p; offsetX += 0.5) {
						double tempMSD = -1;

						if ((offsetX) < 0) {
							// System.out.println("THIS IS <0 : X:" + (offsetX+x));
							continue;
						}

						if ((offsetY < 0)) {
							// System.out.println("THIS IS <0 : Y:" + (offsetY+y));
							continue;
						}
						// Do MSD here, create loops here i believe
						// ITERATE BY HALF HERE TOO????????????????????
						for (double individualY = 0; individualY < macroblock; individualY += 0.5) {
							// Conditional if cant get the half pixel between
							if ((individualY + y + 0.5) >= currentImage.getH()) {
								continue;
							}
							for (double individualX = 0; individualX < macroblock; individualX += 0.5) {

								if ((individualX + x) >= currentImage.getW()
										|| (individualX + x + 0.5) >= currentImage.getW()) {
									continue;
								}
								int currRGB[] = new int[3];
								int one[] = new int[3];
								int two[] = new int[3];
								int three[] = new int[3];
								int four[] = new int[3];
								int d[] = new int[3];

								// first when x and y individual are 0 both
								if (/* offsetX==0 && offsetY==0 && */ x + individualX == 0 && y + individualY == 0) { // first
																														// iteration
																														// so
																														// do
																														// 0
																														// and
																														// 0.5
									currentImage.getPixel((int) (x + individualX), (int) (y + individualY), currRGB);

									int refRGB[] = new int[3];
									refImage.getPixel((int) (offsetX + individualX), (int) (offsetY + individualY),
											refRGB);

									offsetPixel = refRGB[0];

									result += Math.pow((currRGB[0] - refRGB[0]), 2);
									tempMSD = result;
									counterTot++;
								}
								// when x is 0.5multiple while y is 0
								else if ((x + individualX) % 0.5 == 0 && (x + individualX != 0)
										&& (y + individualY == 0)) {
									currentImage.getPixel(((int) (x + individualX - 0.5)), (int) (y + individualY),
											one);
									currentImage.getPixel(((int) (x + individualX + 0.5)), (int) (y + individualY),
											two);
									counterTot++;
//									currRGB[] = new int[3];
									currentImage.getPixel((int) (x + individualX), (int) (y + individualY), currRGB);

									double avg = (one[0] + two[0]) / 2;

									int refRGB[] = new int[3];
									refRGB[0] = (int) avg;
									refRGB[1] = (int) avg;
									refRGB[2] = (int) avg;
									// refImage.getPixel((int)(offsetX+individualX), (int)(offsetY+individualY),
									// refRGB);
									offsetPixel = refRGB[0];

									result += Math.pow((currRGB[0] - refRGB[0]), 2);
									tempMSD = result;
								}
								// when x is a whole num and y is 0
								else if (/* offsetX==0 && offsetY==0 && */ (x + individualX) % 0.5 != 0
										&& (individualX + x) > 0 && (y + individualY) == 0) { // first iteration so do 0
																								// and 0.5
									currentImage.getPixel((int) (x + individualX), (int) (y + individualY), currRGB);
									counterTot++;
									int refRGB[] = new int[3];
									refImage.getPixel((int) (offsetX + individualX), (int) (offsetY + individualY),
											refRGB);
									offsetPixel = refRGB[0];

									result += Math.pow((currRGB[0] - refRGB[0]), 2);
									tempMSD = result;
								}

								// when y is a multiple of 0.5 and x is 0
								else if ((x + individualX) == 0 && (y + individualY) != 0
										&& (y + individualY) % 0.5 == 0) {
									currentImage.getPixel(((int) (x + individualX)), (int) (y + individualY - 0.5),
											one);
									currentImage.getPixel(((int) (x + individualX)), (int) (y + individualY + 0.5),
											two);
									counterTot++;
//									currRGB[] = new int[3];
									currentImage.getPixel((int) (x + individualX), (int) (y + individualY), currRGB);

									double avg = (one[0] + two[0]) / 2;

									int refRGB[] = new int[3];
									refRGB[0] = (int) avg;
									refRGB[1] = (int) avg;
									refRGB[2] = (int) avg;
									// refImage.getPixel((int)(offsetX+individualX), (int)(offsetY+individualY),
									// refRGB);

									offsetPixel = refRGB[0];
									result += Math.pow((currRGB[0] - refRGB[0]), 2);
									tempMSD = result;
								}

								// when y is a multiple of 0.5 and x is a whole num > 0
								else if ((x + individualX) != 0 && (x + individualX) % 0.5 != 0
										&& (y + individualY) != 0 && (y + individualY) % 0.5 == 0) {
									currentImage.getPixel(((int) (x + individualX)), (int) (y + individualY - 0.5),
											one);
									currentImage.getPixel(((int) (x + individualX)), (int) (y + individualY + 0.5),
											two);
									counterTot++;
//									currRGB[] = new int[3];
									currentImage.getPixel((int) (x + individualX), (int) (y + individualY), currRGB);

									double avg = (one[0] + two[0]) / 2;

									int refRGB[] = new int[3];
									refRGB[0] = (int) avg;
									refRGB[1] = (int) avg;
									refRGB[2] = (int) avg;
									// refImage.getPixel((int)(offsetX+individualX), (int)(offsetY+individualY),
									// refRGB);

									offsetPixel = refRGB[0];
									result += Math.pow((currRGB[0] - refRGB[0]), 2);
									tempMSD = result;
								}

								// when x & y is a whole num > 0
								else if ((x + individualX) != 0 && (x + individualX) % 0.5 != 0
										&& (y + individualY) != 0 && (y + individualY) % 0.5 != 0) {
									currentImage.getPixel((int) (x + individualX), (int) (y + individualY), currRGB);
									counterTot++;
									int refRGB[] = new int[3];
									refImage.getPixel((int) (offsetX + individualX), (int) (offsetY + individualY),
											refRGB);

									offsetPixel = refRGB[0];
									result += Math.pow((currRGB[0] - refRGB[0]), 2);
									tempMSD = result;
								}

								// when x & y is a multiple of 0.5
								else if ((x + individualX) != 0 && (x + individualX) % 0.5 == 0
										&& (y + individualY) != 0 && (y + individualY) % 0.5 == 0) {
									currentImage.getPixel(((int) (x + individualX - 0.5)),
											(int) (y + individualY - 0.5), one);
									currentImage.getPixel(((int) (x + individualX - 0.5)),
											(int) (y + individualY + 0.5), two);
									currentImage.getPixel(((int) (x + individualX + 0.5)),
											(int) (y + individualY - 0.5), three);
									currentImage.getPixel(((int) (x + individualX + 0.5)),
											(int) (y + individualY + 0.5), four);
									counterTot++;
//									currRGB[] = new int[3];
									currentImage.getPixel((int) (x + individualX), (int) (y + individualY), currRGB);

									double avg = (one[0] + two[0] + three[0] + four[0]) / 4;

									int refRGB[] = new int[3];
									refRGB[0] = (int) avg;
									refRGB[1] = (int) avg;
									refRGB[2] = (int) avg;
									// refImage.getPixel((int)(offsetX+individualX), (int)(offsetY+individualY),
									// refRGB);

									offsetPixel = refRGB[0];
									result += Math.pow((currRGB[0] - refRGB[0]), 2);
									tempMSD = result;
								}
								// when x & y is a whole num and NOT 0
								else if (/* offsetX==0 && offsetY==0 && */ (x + individualX) % 0.5 != 0
										&& (x + individualX) > 0 && (y + individualY) != 0
										&& (y + individualY) % 0.5 != 0) { // first iteration so do 0 and 0.5
									currentImage.getPixel((int) (x + individualX), (int) (y + individualY), currRGB);

									int refRGB[] = new int[3];
									refImage.getPixel((int) (offsetX + individualX), (int) (offsetY + individualY),
											refRGB);
									counterTot++;
									offsetPixel = refRGB[0];
									result += Math.pow((currRGB[0] - refRGB[0]), 2);
									tempMSD = result;
								}

//								int currRGB[] = new int[3];
//								currentImage.getPixel((int)(x+individualX), (int)(y+individualY), currRGB); 
//								
//								
//								int refRGB[] = new int[3];
//								refImage.getPixel((int)(offsetX+individualX), (int)(offsetY+individualY), refRGB); 
//								
//								
//								result+=Math.pow((currRGB[0]-refRGB[0]), 2);	
//								tempMSD = result;

							}
						}
						result = result / (Math.pow(macroblock, 2));
						// System.out.println("MSD: " + result);
						tempMSD = result;
						if (MSD > tempMSD) {
							MSD = tempMSD;
							mvX = x - offsetX;
							mvY = y - offsetY;

							// ERROR: e = | pixel_in_target_block – corresponding_pixel_in_the matched_block
							// |
							errorXY[0] = (int) mvX;
							errorXY[1] = (int) mvY;

							errorP.add(errorXY);
							for (double individualY = 0; individualY < macroblock; individualY += 0.5) {
								for (double individualX = 0; individualX < macroblock; individualX += 0.5) {
									int currRGB[] = new int[3];
									currentImage.getPixel((int) Math.ceil(x + individualX),
											(int) Math.ceil(y + individualY), currRGB);

									int refRGB[] = new int[3];
									int bREF[] = new int[3];
									int cREF[] = new int[3];
									int dREF[] = new int[3];

									int errorPixel = (int) Math.abs(currRGB[0] - offsetPixel);

									error.setPixel((int) Math.ceil(x + individualX), (int) Math.ceil(y + individualY),
											new int[] { errorPixel, errorPixel, errorPixel });
								}
							}

						}

						count++;
					}
				}
				System.out.print(" [" + mvX + ", " + mvY + "] ");
				count = 0;
				count2++;

			}
			System.out.println("");
		}
		System.out.println("TOTAL COMPARISONS IN END: " + counterTot);
		error.write2PPM("halfErrorBlock_" + curr.getName());
	}

	// Calculate the MSD of Curr Img and Ref Img
	public static void meanSquaredDiff(int targetY, int targetX, int refY, int refX, MImage currentImg, MImage refImg,
			int p) {
		/*
		 * Pseudocode on how to calculate MSD target(x,y) MSD = infinity;
		 * 
		 * for(offset = x-p; offsetx <= x+p; offsetx++){ for(offset = y-p; offsety <=
		 * x+p; offsety++){ TempMSD = (T(x,y), Reference(ox,oy)
		 * 
		 * if(MSD > TempMSD)?{ MSD = TempMSD MVx = x-offsetx Mvy = y-offsety } } }
		 */
		double MSD = Double.MAX_VALUE;
		double result = 0;

		for (int height = 0; height < 16; height++) {
			for (int width = 0; width < 16; width++) {
				int currRGB[] = new int[3];
				currentImg.getPixel(targetX + width, targetY + height, currRGB);
				int refRGB[] = new int[3];
				refImg.getPixel(refX + width, refY + height, refRGB);
				result += Math.pow((currRGB[0] - refRGB[0]), 2);
			}
		}
		result = result / (16 * 16);
	}

}
