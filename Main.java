class Main  {

    static long factorial(int a){
        if (a == 1){
            return 1;
        } else return a * factorial(a-1);
    }

    static void swap(int a[], int i, int j){
        int num = a[i];
        a[i] = a[j];
        a[j] = num;
    }

    //Bubble sort
    static void bubbleSort(int a[]){
        for (int i = 0; i < a.length; i++){
            for (int j = 0; j < a.length; j++){
                if (a[j] > a[i]){
                    swap(a, i, j);
                }
            }
        }
    }

    //Selection sort
    static void selectionSort(int a[]){

        for (int i = 0; i < a.length; i++) {
            int max = a[0];
            int indMax = 0;
            for (int j = 0; j < a.length - i; j++) {
                if (max < a[j]){
                    max = a[j];
                    indMax = j;
                }
            }
            swap(a, a.length - i - 1, indMax);
        }
    }

    //Quick Sort
    static void quickSort(int a[], int start, int end){

        if (start >= end){
            return;
        }

        int mid = (start + end)/2;
        int i = start;
        int j = end;

        while (i <= j){
            while (a[i] < a[mid]){
                i++;
            }
            while (a[j] > a[mid]){
                j--;
            }
            if (i <= j){
                swap(a, i, j);
                i++;
                j--;
            }

            if (start < j){
                quickSort(a, start, j);
            }

            if (end > i){
                quickSort(a, i, end);
            }

        }

    }

    //Binary search
    static int binarySearch(int a[], int value){
        int first = 0;
        int last = a.length - 1;
        int flag = -1;

        while (first <= last) {
            int mid = (first + last) / 2;
            if (a[mid] == value) {
                return mid;
            } else if (a[mid] > value) {
                last = mid - 1;
            } else {
                first = mid + 1;
            }
        }
        System.out.println("There is not this number in your array");
        return flag;
    }

    public static void main(String[] args) {
        int a[] = new int[]{4, 3, 5, 9, 7, 2, 1};

        System.out.println(factorial(6));

//        bubbleSort(a);
//        selectionSort(a);

        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }

//        quickSort(a, 0, a.length - 1);
//        System.out.println(binarySearch(a, 7));

    }
}

