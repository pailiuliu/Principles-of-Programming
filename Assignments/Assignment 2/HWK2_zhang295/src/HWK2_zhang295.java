import java.text.DecimalFormat;

/* 
Richard Zhang
Zhang295
1450206
homework assignment 2 2S03
 */

public class HWK2_zhang295 {

	public static void main(String[] args) {
		
		int N = Integer.parseInt(args[0]); // the number of matrices
		int[] rows = new int[N]; //array containing the number of rows in each matrix, so rows[0] would be the number of rows for matrix 1
		int[] columns = new int[N];
		
		for (int i = 0; i < N; i++){
			rows[i] = Integer.parseInt(args[2*i+1]);
			columns[i] = Integer.parseInt(args[2*i+2]);
		}
		
		double[]array[][] = new double[N][][];
		int counter = 0;
		
		for (int i = 0; i < N; i++){
			double[][] tempArray = new double[rows[i]][columns[i]];
				for (int j = 0; j < rows[i]; j++){
					for (int k = 0; k < columns[i]; k++){
						tempArray[j][k] = Integer.parseInt(args[N*2+1+counter]);
						counter +=1;
					}
			}
			array[i] = tempArray;
		}
		
		for (int i = 0; i < rows[1]; i++){
			for (int j = 0; j < columns[1]; j++){
				array[1][i][j] = Integer.parseInt(args[N*2+1+columns[0]*rows[0]+j+i*columns[1]]);
			}
		}
		
		double[][] result = multiply(N, array);
	
		System.out.println("The result of the multiplication is:  ");

		
		for (int i = 0; i < result.length; i++){
			for (int j = 0; j < result[i].length; j++){
				System.out.print(result[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.println();
        
        double inverse[][] = invert(result);
        
        System.out.println("The inverse is: ");
        
        
        for (int i = 0; i < inverse.length; i++){
			for (int j = 0; j < inverse[i].length; j++){
				
				System.out.print(new DecimalFormat("#.##").format(inverse[i][j]) + " ");
			}
			System.out.print("\n");
		}
	}
	
	public static double[][]multiply(int N, double []array[][] ){
		double [][]temp = multiplicar(array[0],array[0+1]);
		array[0+1] = temp;
		for (int i = 1; i < N-1 ; i++){
			temp = multiplicar(array[i],array[i+1]);
			array[i+1] = temp;
		}
		return temp;
	}
	
	public static double[][] multiplicar(double[][] A, double[][] B) {

        int aRows = A.length;
        int aColumns = A[0].length;
        int bRows = B.length;
        int bColumns = B[0].length;

        if (aColumns != bRows) {
            throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
        }

        double[][] C = new double[aRows][bColumns];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                C[i][j] = 0.00000;
            }
        }

        for (int i = 0; i < aRows; i++) { // aRow
            for (int j = 0; j < bColumns; j++) { // bColumn
                for (int k = 0; k < aColumns; k++) { // aColumn
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }
	
	 public static double[][] invert(double a[][]) 
	    {
	        int n = a.length;
	        double x[][] = new double[n][n];
	        double b[][] = new double[n][n];
	        int index[] = new int[n];
	        for (int i=0; i<n; ++i) 
	            b[i][i] = 1;
	 
	 // Transform the matrix into an upper triangle
	        gaussian(a, index);
	 
	 // Update the matrix b[i][j] with the ratios stored
	        for (int i=0; i<n-1; ++i)
	            for (int j=i+1; j<n; ++j)
	                for (int k=0; k<n; ++k)
	                    b[index[j]][k]
	                    	    -= a[index[j]][i]*b[index[i]][k];
	 
	 // Perform backward substitutions
	        for (int i=0; i<n; ++i) 
	        {
	            x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
	            for (int j=n-2; j>=0; --j) 
	            {
	                x[j][i] = b[index[j]][i];
	                for (int k=j+1; k<n; ++k) 
	                {
	                    x[j][i] -= a[index[j]][k]*x[k][i];
	                }
	                x[j][i] /= a[index[j]][j];
	            }
	        }
	        return x;
	    }
	 
	 public static void gaussian(double a[][], int index[]) 
	    {
	        int n = index.length;
	        double c[] = new double[n];
	 
	 // Initialize the index
	        for (int i=0; i<n; ++i) 
	            index[i] = i;
	 
	 // Find the rescaling factors, one from each row
	        for (int i=0; i<n; ++i) 
	        {
	            double c1 = 0;
	            for (int j=0; j<n; ++j) 
	            {
	                double c0 = Math.abs(a[i][j]);
	                if (c0 > c1) c1 = c0;
	            }
	            c[i] = c1;
	        }
	 
	 // Search the pivoting element from each column
	        int k = 0;
	        for (int j=0; j<n-1; ++j) 
	        {
	            double pi1 = 0;
	            for (int i=j; i<n; ++i) 
	            {
	                double pi0 = Math.abs(a[index[i]][j]);
	                pi0 /= c[index[i]];
	                if (pi0 > pi1) 
	                {
	                    pi1 = pi0;
	                    k = i;
	                }
	            }
	 
	   // Interchange rows according to the pivoting order
	            int itmp = index[j];
	            index[j] = index[k];
	            index[k] = itmp;
	            for (int i=j+1; i<n; ++i) 	
	            {
	                double pj = a[index[i]][j]/a[index[j]][j];
	 
	 // Record pivoting ratios below the diagonal
	                a[index[i]][j] = pj;
	 
	 // Modify other elements accordingly
	                for (int l=j+1; l<n; ++l)
	                    a[index[i]][l] -= pj*a[index[j]][l];
	            }
	        }
	    }
	 
	 
	
}
