import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    //    static List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u');
    static long max = 0;
    static long min = Long.MAX_VALUE;
    static int v, n, m, k;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
//        System.out.println(Integer.MAX_VALUE);
        v = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        int[] arr = new int[v];
        int[] arr2 = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < v; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        Arrays.sort(arr2);

        int cnt = 0;
        for (int i = 0; i < v; i++) {
            int idx = Arrays.binarySearch(arr2, arr[i]);
            if (idx < 0 ) {
                sb.append(arr[i] + " ");
                continue;
            }
            cnt++;
        }

//        sb.append(left - 1);
        System.out.println(arr.length - cnt);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

//    static int lowercase(long[] arr, long key) {
//        int lo = 0;
//        int hi = arr.length;
//
//        // lo가 hi랑 같아질 때 까지 반복
//        while (lo < hi) {
//
//            int mid = (lo + hi) / 2; // 중간위치를 구한다.
//
//            /*
//             *  key 값이 중간 위치의 값보다 작거나 같을 경우
//             *
//             *  (중복 원소에 대해 왼쪽으로 탐색하도록 상계를 내린다.)
//             */
//            if (key <= arr[mid]) {
//                hi = mid;
//            } else {
//                lo = mid + 1;
//            }
//
//        }
//        return lo;
//    }
//
//    private static int uppercase(long[] arr, long key) {
//        int lo = 0;
//        int hi = arr.length;
//
//        // lo가 hi랑 같아질 때 까지 반복
//        while (lo < hi) {
//
//            int mid = (lo + hi) / 2; // 중간위치를 구한다.
//
//            // key값이 중간 위치의 값보다 작을 경우
//            if (key < arr[mid]) {
//                hi = mid;
//            }
//            // 중복원소의 경우 else에서 처리된다.
//            else {
//                lo = mid + 1;
//            }
//        }
//        return lo;
//    }

    //
    static int lowercase(long[] arr, long target) {
        int lo = 0;
        int hi = arr.length;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] >= target) {
                hi = mid;
            } else lo = mid + 1;
        }

        return hi;
    }

    static int uppercase(long[] arr, long target) {
        int lo = 0;
        int hi = arr.length;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] <= target) {
                lo = mid;
            } else hi = mid - 1;
        }
        return lo;
    }


    static int dfs(int a, int p, int[] arr, int[] ans, int[] visited, int num, int cnt) throws IOException {
//        if (set.contains(a)) return;
        if (num == n) {
            min = Math.min(min, cnt);
            return 3;
        }
//        if (visited[arr[num]] == 1) return 0;

//        int chk = 0;
//        for (int i : ans)
//            if (i == arr[num]) chk = 1;
        if (visited[arr[num]] == 1) {
            if (num + 1 == n) {
                int val = dfs(a, p, arr, ans, visited, num + 1, cnt);
                return 2;
            }
            for (int k = 0; k < v; k++) {
                int i = ans[k];
                if (i == -1) continue;
                int val = dfs(i, k, arr, ans, visited, num + 1, cnt);
                if (val == 2) continue;
//                set.remove(i);
//                set2.add(i);
//                set.add(arr[num + 1]);
//                set2.remove(arr[num + 1]);
//            int pos = -1;
//                for (int k = 0; k < v; k++) {
//                    if (ans[k] == arr[num + 1]) {
////                ans[i] = -1;
//                        ans[k] = i;
//                        break;
//                    }
//                }
                ans[k] = i;
                visited[i] = 1;
                visited[arr[num + 1]] = 0;
            }
            return 2;
        }
//        set.add(a);
//        set2.remove(a);
//        int pos = -1;
//        for (int i = 0; i < v; i++) {
//            if (ans[i] == a) {
////                ans[i] = -1;
//                pos = i;
//                break;
//            }
//        }
//        set.remove(arr[num]);
//        set2.add(arr[num]);
        visited[a] = 0;
        ans[p] = arr[num];
        visited[arr[num]] = 1;

        for (int k = 0; k < v; k++) {
            int i = ans[k];
            if (num + 1 == n) {
                int val = dfs(i, k, arr, ans, visited, num + 1, cnt + 1);
                break;
            }
//            if (set.contains(i)) continue;
            int val = dfs(i, k, arr, ans, visited, num + 1, cnt + 1);
            if (val == 2) continue;
//            else if (val > max) max = val;
//            set.remove(i);
//            set2.add(i);
//            set.add(arr[num + 1]);
//            set2.remove(arr[num + 1]);
//            int pos = -1;
//            for (int k = 0; k < v; k++) {
//                if (ans[k] == arr[num + 1]) {
////                ans[i] = -1;
//                    ans[k] = i;
//                    break;
//                }
//            }
            visited[i] = 1;
            ans[k] = i;
            visited[arr[num + 1]] = 0;
        }
        return 1;
    }
}
