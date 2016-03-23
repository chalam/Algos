public class AddInts { 
   public static void main(String[] args) { 
      int N = Integer.parseInt(args[0]); 
      int sum = 0; 
      for (int i = 0; i < N; i++) 
         sum += StdIn.readInt(); 
      StdOut.println("Sum is " + sum); 
   } 
} 