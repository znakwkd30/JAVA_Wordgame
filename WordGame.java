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
	public String name; // ����� �̸�
	public String tossWord;// ���޹��� �ܾ�
	
	public String sayWord() { // ����ڷκ��� �ܾ �Է¹޴� �޼ҵ�
        tossWord = input.next(); 
        return tossWord;
    }
    
    public boolean succeed(char lastChar) {  
        if (lastChar == tossWord.charAt(0)) 
        	//charAt �Լ� ����
        	//charAt�� index�� �־��� ���� �ش��ϴ� ���ڸ� �����Ѵ�.
        	//�ε����� 0���� �����ϱ� ������ index�� �� �� �ִ� ���� ū ���� (���ڿ�.legnth-1)�̴�.
        	//�������� �ʴ� index�� ���ڷ� �����ϸ� ������ ��µȴ�.
        	return true;
        else 
        	return false;
    }
}

public class WordGame{
	public static Mixer mixer;
	public static Clip clip;
	
	public static void main(String[] args) throws InterruptedException{// <- sleep �Լ� ��� 
		
		//�ڹ� sound api���
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
		  //�ڹ� sound api���

		Scanner sc = new Scanner(System.in);
		System.out.print("�����ձ⸦ �����մϴ�.");
		Thread.sleep(1000); // 1�� ����
		System.out.print(" ¦!");
		Thread.sleep(1000); // 1�� ����
		System.out.print(" ¦!");
		Thread.sleep(1000); // 1�� ����
		System.out.println(" ¦!");
		Thread.sleep(3000); //3�� ����
		
		System.out.print("���ӿ� �����ϴ� �ο��� ����Դϱ�? >> ");
        int number = sc.nextInt();
        
        Player[] player = new Player[number]; //�÷��̾� ��ü ����
        
        for(int i=0; i<number; i++) {
            System.out.print("�������� �̸��� �Է��ϼ��� >> ");
            player[i] = new Player(); 
            player[i].name = sc.next();
            //�Է¹��� �̸��� Player �迭�� �̸� �ʵ忡 ���� �����ϴ� �ڵ�
        }
		System.out.print("ù��° �ܾ �Է����ּ��� : ");
		String word = sc.next();
		System.out.println("�����ϴ� �ܾ�� "+word+"�Դϴ�.");
		
		 int i = 0, j = 0;
	        while(true) {
	            j = i % number; //�����ձⰡ �� �ҿ� ������ ���� ���� ����
	            int lastIndex = word.length()-1; //������ ���ڿ� ���� �ε���
	            char lastChar = word.charAt(lastIndex); //������ ����
	            
	            System.out.print(player[j].name + " >> ");
	            player[j].sayWord(); // ������� �ܾ� �Է� �ޱ�
	            boolean continuing = player[j].succeed(lastChar);
	            if(continuing == false) {
	                System.out.println(player[j].name + "��(��) �����ϴ�.");
	                System.out.print("���α׷��� ����˴ϴ�");
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
