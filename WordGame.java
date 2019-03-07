package sound;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.UnsupportedAudioFileException;

class Player{
	Scanner input = new Scanner(System.in);
	public String name; // 사용자 이름
	public String tossWord;// 전달받은 단어
	
	public String sayWord() { // 사용자로부터 단어를 입력받는 메소드
        tossWord = input.next(); 
        return tossWord;
    }
    
    public boolean succeed(char lastChar) {  
        if (lastChar == tossWord.charAt(0)) 
        	//charAt 함수 설명
        	//charAt은 index로 주어진 값에 해당하는 문자를 리턴한다.
        	//인덱스는 0부터 시작하기 때문에 index로 들어갈 수 있는 가장 큰 수는 (문자열.legnth-1)이다.
        	//존재하지 않는 index를 인자로 전달하면 공백이 출력된다.
        	return true;
        else 
        	return false;
    }
}

public class WordGame{
	public static Mixer mixer;
	public static Clip clip;
	
	public static void main(String[] args) throws InterruptedException{// <- sleep 함수 사용 
		
		//자바 sound api사용
		Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
		mixer = AudioSystem.getMixer(mixInfos[0]);
		  
		  DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
		  try { clip = (Clip)mixer.getLine(dataInfo); }
		  catch(LineUnavailableException lue) { lue.printStackTrace(); }
		  
		  try
		  {
			  URL soundURL = ClipTest.class.getResource("/sound/fall in love.wav");
			  AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
			  clip.open(audioStream);
		  }
		  catch(LineUnavailableException lue) { lue.printStackTrace(); }
		  catch(UnsupportedAudioFileException uafe) { uafe.printStackTrace(); }
		  catch(IOException ioe) { ioe.printStackTrace(); }
		  
		  clip.start();
		  //자바 sound api사용

		Scanner sc = new Scanner(System.in);
		System.out.print("끝말잇기를 시작합니다.");
		Thread.sleep(1000); // 1초 지연
		System.out.print(" 짝!");
		Thread.sleep(1000); // 1초 지연
		System.out.print(" 짝!");
		Thread.sleep(1000); // 1초 지연
		System.out.println(" 짝!");
		Thread.sleep(3000); //3초 지연
		
		System.out.print("게임에 참가하는 인원은 몇명입니까? >> ");
        int number = sc.nextInt();
        
        Player[] player = new Player[number]; //플레이어 객체 생성
        
        for(int i=0; i<number; i++) {
            System.out.print("참가자의 이름을 입력하세요 >> ");
            player[i] = new Player(); 
            player[i].name = sc.next();
            //입력받은 이름을 Player 배열의 이름 필드에 각각 저장하는 코드
        }
		System.out.print("첫번째 단어를 입력해주세요 : ");
		String word = sc.next();
		System.out.println("시작하는 단어는 "+word+"입니다.");
		
		 int i = 0, j = 0;
	        while(true) {
	            j = i % number; //끝말잇기가 한 텀에 끝나지 않을 수도 있음
	            int lastIndex = word.length()-1; //마지막 문자에 대한 인덱스
	            char lastChar = word.charAt(lastIndex); //마지막 문자
	            
	            System.out.print(player[j].name + " >> ");
	            player[j].sayWord(); // 사용자의 단어 입력 받기
	            boolean continuing = player[j].succeed(lastChar);
	            if(continuing == false) {
	                System.out.println(player[j].name + "이(가) 졌습니다.");
	                System.out.print("프로그램이 종료됩니다");
	                Thread.sleep(1000);
	                System.out.print(".");
	                Thread.sleep(1000);
	                System.out.print(".");
	                Thread.sleep(1000);
	                System.out.print(".");
	                Thread.sleep(1000);
	                break;
	            }
	            word = player[j].tossWord;
	            i++;
	        }
	}

}
