package sort;

public class BubbleSort1 {

	public static void main(String[] args) {
		char[] ca = {'5', '1', '3', '1', '7', '2', '8', '0', '9', '6'};
		
		BubbleSort(ca);
		
		PrintArray(ca);

	}
	
	static void BubbleSort(char[] ca) {
		boolean flag = true;
		while(flag) {
			flag = false;
			for(int i = 0; i < ca.length - 1; i++) {
				if(ca[i] > ca[i + 1]) {
//					char tmp = ca[i];
//					ca[i] = ca[i + 1];
//					ca[i + 1] = tmp;
					Swap(ca[i], ca[i + 1]);
					flag = true;
				}
			}
		}
		PrintArray(ca);
	}
	
	static void Swap(char a, char b) {
		char tmp = a;
		a = b;
		b = tmp;
	}
	
	static void PrintArray(char[] ca) {
		System.out.println("");
		for(int i = 0; i < ca.length; i++){
			System.out.print(String.valueOf(ca[i]) + " ");
		}
		System.out.println("");
	}

}
