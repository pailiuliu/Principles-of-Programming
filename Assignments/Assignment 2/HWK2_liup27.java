
/*
Name: Pai Liu
MacID: liup27
Student Number: 1419475
Description: This is my program for HWK2. The naming convention is used correctly and submitted in a ZIP file.
*/

import java.text.DecimalFormat; //class used to round values in matrix to 2 decimal places

public class HWK2_liup27 {
	
//Below is the main program used to solve for the HWK2 requirements. The methods used in the main is written below.
	
	public static void main(String[] args){ //the input arguments for the program, stated in order of the HWK2
		int N = Integer.parseInt(args[0]); //creates variable representing the number of matrices
		int[] rows = new int[N]; //array containing total number of rows
		int[] columns = new int[N]; //array containing total number of columns
		for(int i = 0; i < N; i++){ //loop through all the arguments given
			rows[i] = Integer.parseInt(args[1+2*i]); //inputs the number of the rows in each individual matrix
			columns[i] = Integer.parseInt(args[2+2*i]); //inputs the number of the columns in each individual matrix
		}
		double[] array[][] = new double[N][][];//creates new array, contains N number of 2D arrays
		int count = 0;	 //creates counter variable, with a value of zero (will be increased)
		for(int i =0; i < N; i++){ //loops through the total number of matrices
			double[][] temporaryArray = new double[rows[i]][columns[i]]; //creates arrays with correct number of rows and columns for each individual matrix
			for (int j = 0; j < rows[i]; j++){ //loops through each row
				for(int k = 0; k < columns[i]; k++){ //loops through each column
					temporaryArray[j][k] = Integer.parseInt(args[1+count+N*2]); //inputs correct values for the elements for specific matrix array
					count += 1; //increments counter variable by 1, move on to next matrix
				}
			}
			array[i] = temporaryArray; //places the specific created matrix into a 1D matrix
		}
		double[][] product = multiplyAllMatrix(N, array); //use method to create a matrix which is the product of ALL the matrices
		if(product.length != product[0].length || Determinant(product,product.length)== 0){ //if matrix is NOT a square matrix OR if determinant is 0...
			System.out.println("Matrix is NOT invertible."); //print to user that the matrix is NOT invertible
		}else{ //otherwise, if matrix IS invertible then...
			double inverse[][] = invertMatrix(product); //invert the matrix using the invert matrix method
			System.out.println("The inverse of the matrix is: "); //prints to user
			for(int i = 0; i<inverse.length; i++){ //loops through for each rows
				for(int j=0; j<inverse[i].length; j++){ //loops through for each column
					System.out.print(new DecimalFormat("#.##").format(inverse[i][j]) + " "); //prints out a row of the matrix elements rounded to two decimal place seperated by space.
				} 
				System.out.println("\n"); //move to print on next line for next column of matrix
			}//iterates through nested for loops to create the next columnes
		}
	}//final inverted matrix is printed out.
	
//The following are methods that will be used in the main program.
	
	public static double[][] multiply(double[][] A1, double[][] A2){ //defining method with two matrices as parameters, used for A x B
		int A1Rows = A1.length; //create variable, number of rows in matrix A
		int A1Columns = A1[0].length;//create variable, number of columns in matrix A
		int A2Rows = A2.length;//create variable, number of rows in matrix B
		int A2Columns = A2[0].length;//create variable, number of columns in matrix B
		
		if(A1Columns != A2Rows){ //checks to see if number of columns in matrix A is equal to number of rows in matrix B, in this case, it does NOT
			throw new IllegalArgumentException("Matrices can NOT be mulitplied."); //prints error, stops program
		}
		double[][]A3 = new double[A1Rows][A2Columns]; //creates product matrix C
		
		for(int i = 0; i < A1Rows; i++){ //iterates for each row in product matrix C
			for(int j =0; j < A2Columns; j++){ //iterates for each column in product matrix C
				for(int k = 0; k < A1Columns; k++){ //iterates for each column in matrix A and each row in matrix B
					A3[i][j] += A1[i][k]*A2[k][j]; //multiplies the matrix, inputs new values for the product matrix C
				}
			}
		}
		return A3; //return product to main program
	}
	public static double[][] multiplyAllMatrix(int N, double[]array [][]){ //method that multiplies all matrices together using the multiply matrix method above
		double[][] product = multiply(array[0], array[0+1]); //create local temporary array to store product of first two matrix
		array[0+1] = product; //finalize and put aside product of first two matrices
		for(int i = 1; i < N-1; i++){ //repeat multiply method for the remaining matrices by iterating through them
			product = multiply(array[i], array[i+1]); //multiplies remaining matrices together
			array[i+1] = product; //resulting product when ALL the matrices are multiplied together
		}
		return product; //return final product to main program
	}
	public static void gaussianMatrix(double B[][], int index[]){ //method used to apply the Gaussian method to invert the matrix
		int n = index.length; //creates variable for length of the index
		double P[] = new double[n]; //creates new array
		
		for(int i = 0; i < n; i++){ //initializes the index by iteration
			index[i] = i;
		}
		for(int i=0; i < n; i++){ //nested for loop to rescale the factors of the matrix, one per row
			double A1 = 0;
			for(int j=0; j < n; j++){
				double A0 = Math.abs(B[j][j]);
				if (A0 > A1){
					A1 = A0;
				}
			P[j] = A1; 
			}
		}
		int k = 0; //nested for loop to iterate through matrix, search for the pivoting element
		for(int i = 0; i < n; i++){
			double C1 = 0;
			for (int j = i; j < n; j++){
				double C0 = Math.abs(B[index[j]][i]);
				C0 /= P[index[j]];
				if(C0 > C1){
					C0 = C1;
					k = j;
				}
			}
			// interchange the rows of the matrix according to pivoting order above
			int temp = index[i];
			index[i] = index[k];
			index[k] = temp;
			for (int z = i + 1; z < n; z++){
				double h = B[index[z]][i]/B[index[i]][i];
				B[index[z]][i] = h; //record the pivoting points in a array
				for(int m = i+1; m<n; ++m){ //iterates through matrix and modifies each value/element
					B[index[z]][m] -= h*B[index[i]][m];
				}
			}
		}
		
	}
	public static double[][] invertMatrix(double array[][]){ //method used to invert matrix, using the Gaussian method
		int arrayLength = array.length; //creates variable to record length of the array, for rows and columns
		double matrixA[][] = new double[arrayLength][arrayLength];
		double matrixB[][] = new double[arrayLength][arrayLength]; //creates two temporary square matrices
		int index[] = new int[arrayLength]; 
		for(int j=0; j<arrayLength; j++){ //iterates through matrix and creates identidy matrix
			matrixB[j][j] = 1;
		}
		gaussianMatrix(array, index); //applies the Gaussian matrix method above
		for(int e=0; e < arrayLength - 1; e++){ //nested for loop to updated the values for temporary matrix B
			for (int f = e+1; f<arrayLength; f++){
				for(int g = 0; g < arrayLength; g++){
					matrixB[index[f]][g] -= array[index[f]][e]*matrixB[index[e]][g];
				}
			}
		}
		for(int i = 0; i < arrayLength; i++){ //nested for loops to perform backwards substitution
			matrixA[arrayLength - 1][i] = matrixB[index[arrayLength-1]][i]/array[index[arrayLength-1]][arrayLength-1];
			for(int j = arrayLength-2; j >= 0; j--){
				matrixA[j][i] = matrixB[index[j]][i];
				for(int k = j+1; k < arrayLength; k++){
					matrixA[j][i] -= array[index[j]][k]*matrixA[k][i];
				}
				matrixA[j][i] /= array[index[j]][j];
			}
		}
		return matrixA; //returns the inverted matrix to main program
	}
	public static double Determinant(double array[][], int matrixSize){ //method used to find the determinant of a matrix
		double determinant = 0; //set variable for the value of the determinant
		if(matrixSize == 1){ //if it is a square matrix of size 1...
			determinant = array[0][0]; //determinant equals the only value
		}else if(matrixSize ==2){ //if square matrix is size of 2 rows 2 columns...
			determinant = array[0][0]*array[1][1] - array[1][0]*array[0][1]; //determinant formula is applied to find determinant
		}else{ //if matrix has more than 2 rows and 2 columns...
			determinant = 0; //determinant is first set to zero
			for(int i = 0; i<matrixSize; i++){ //nested for loop is iterate through a row in the matrix to find determinant
				double[][]m = new double[matrixSize-1][];
				for(int j=0; j < (matrixSize-1); j++){
					m[j] = new double[matrixSize-1];
				}
				for(int k = 1; k < matrixSize; k++){
					int z = 0;
					for(int h = 0; h <matrixSize; h++){
						if(h == i){
							continue;
						}
						m[k-1][z] = array[k][h];
						z++;
					}
				}
				determinant += Math.pow(-1.0, 1.0+i+1.0)*array[0][i]*Determinant(m,matrixSize-1); 
			}
		}
		return determinant; //returns the determinant value to the main program
	}
	


	
}
