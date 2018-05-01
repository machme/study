import java.util.Arrays;

public class KakaoProb6 {
	// http://tech.kakao.com/2017/09/27/kakao-blind-recruitment-round-1/
	// 카카오 신입공채 6번
	
    static char gap = 'a' - 'A';
    static int count = 0;
    public static void main(String[] args) {
        //높이m 폭 n
//        int m = 4;
//        int n = 5;
//        String exam = "[\"CCBDE\", \"AAADE\", \"AAABF\", \"CCBBF\"]";

        int m = 6;
        int n = 6;
        String exam = "[\"TTTANT\", \"RRFACC\", \"RRRFCC\", \"TRRRAA\", \"TTMMMF\", \"TMMTTJ\"]";

        //입력값 파싱
        String[] parse = exam.split(",");
        for (int i = 0, size = parse.length; i < size; i++) {
            String str = parse[i];
            parse[i] = str.substring(str.indexOf("\"") + 1, str.lastIndexOf("\""));
        }

        //char 배열로 변환
        char[][] input = new char[m][n];
        for (int i = 0, size = parse.length; i < size; i++) {
            String str = parse[i];
            input[i] = str.toCharArray();
        }


        while(true) {
            boolean stop = true;
            //지워지는 블록이 있는지 체크 하여 마킹
            for (int i = 0, iSize = m - 1; i < iSize; i++) {
                for (int j = 0, jSize = n - 1; j < jSize; j++) {
                    char c = toUpperChar(input[i][j]);

                    if(c==' ')break;
                    
                    if ( !( c == toUpperChar( input[i][j+1] ) ) ) continue;
                    if ( !( c == toUpperChar( input[i+1][j] ) ) ) continue;
                    if ( !( c == toUpperChar( input[i+1][j+1] ) ) ) continue;

                    c = toLowerChar(c);
                    input[i][j] = input[i][j + 1] = input[i + 1][j] = input[i + 1][j + 1] = c;
                    stop = false;
                }
            }
            //마킹한후 블록 출력
            for(int i=0 ; i<input.length ; i++){
                System.out.println(Arrays.toString(input[i]));
            }
            System.out.println("====================================");
            //지워질 블록이 없는경우 stop!!
            if(stop==true){
                break;
            }
            //마킹된 블록을 지우고 내림
            for (int i = 0; i < n; i++) {
                int[] position = new int[m];
                int max = 0, idx = 0;
                for (int j = m - 1; j >= 0; j--) {
                    if ('a' <= input[j][i] && input[j][i] <= 'z') {
                        position[max++] = j;
                        input[j][i] = ' ';
                        count++;
                    } else {
                        if (max == 0) {
                            continue;
                        } else {
                            input[position[idx++]][i] = input[j][i];
                            position[max++] = j;
                            input[j][i] = ' ';
                        }
                    }
                }
            }
        }//while
        System.out.println("result");
        for (int i = 0, size = input.length; i < size; i++) {
            System.out.println(Arrays.toString(input[i]));
        }
        System.out.println("총갯수: "+count);

    }
    private static char toUpperChar(char c){
        char result;
        if( 'a' <= c && c <= 'z'){
            result = (char)(c-gap);
        }else{
            result = c;
        }
        return result;
    }
    private static char toLowerChar(char c){
        char result;
        if( 'A' <= c && c <= 'Z'){
            result = (char)(c+gap);
        }else{
            result = c;
        }
        return result;
    }
}
