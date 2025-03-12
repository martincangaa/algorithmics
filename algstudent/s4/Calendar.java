package algstudent.s3;

import java.io.*;
import java.util.*;

public class Calendar {
	
	private List<String> originalParticipants;
	private List<List<Match>> schedule;
	
	
	public Calendar() {
		
	}
	
	public Calendar(String[] originalParticipants) {
		this.originalParticipants = Arrays.asList(originalParticipants);
	}
	
    // A simple class to represent a match between two players.
    static class Match {
        String player1;
        String player2;
        public Match(String player1, String player2) {
            this.player1 = player1;
            this.player2 = player2;
        }
    }
    
    public void loadNamesFromFile(String filename) {
        
        List<String> participants = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            int num = Integer.parseInt(br.readLine().trim());
            for(int i = 0; i < num; i++){
                String name = br.readLine();
                if(name != null)
                    participants.add(name.trim());
            }
        } catch(IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }
        
        originalParticipants = participants;
    }
    
    
    public void schedule() {
    	
    	this.schedule = generateSchedule(originalParticipants);
    	
    }
    
    private List<List<Match>> generateSchedule(List<String> players) {
        
        int n = players.size();
        
        // only 2 players
        if (n == 2) {
            List<List<Match>> sched = new ArrayList<>();
            List<Match> round = new ArrayList<>();
            round.add(new Match(players.get(0), players.get(1)));
            sched.add(round);
            return sched;
        }
        
        int half = n / 2;
        
        List<String> groupA = new ArrayList<>(players.subList(0, half));
        List<String> groupB = new ArrayList<>(players.subList(half, n));
        
        // Recursively generate schedules for each group.
        List<List<Match>> schedA = generateSchedule(groupA);
        List<List<Match>> schedB = generateSchedule(groupB);
        int roundsSub = schedA.size(); 
        List<List<Match>> schedule = new ArrayList<>();
        
        // Combine the two schedule
        for (int r = 0; r < roundsSub; r++) {
            List<Match> roundMatches = new ArrayList<>();
            roundMatches.addAll(schedA.get(r));
            roundMatches.addAll(schedB.get(r));
            schedule.add(roundMatches);
        }
        
        // Mix the two schedules so we can get all the posible combinations
        List<Match> crossRound = new ArrayList<>();
        for (int i = 0; i < half; i++) {
            crossRound.add(new Match(groupA.get(i), groupB.get(i)));
        }
        schedule.add(crossRound);
        
        // Add the matches that 
        int remainingRounds = (n - 1) - (roundsSub + 1);
        for (int r = 0; r < remainingRounds; r++) {
            List<Match> roundMatches = new ArrayList<>();
            for (int i = 0; i < half; i++) {
                int opponentIndex = (i + r + 1) % half;
                roundMatches.add(new Match(groupA.get(i), groupB.get(opponentIndex)));
            }
            schedule.add(roundMatches);
        }
        
        return schedule;
    }
    
    public void printBoardWithNames() {
    	
    	int totalRounds = schedule.size();
    	
    	Map<String, List<String>> opponentsMap = new LinkedHashMap<>();
        for(String p : originalParticipants) {
        	
        	List<String> opponentList = new ArrayList<>();
        	for (int i = 0; i < totalRounds; i++) {
        	    opponentList.add("");
        	}

            opponentsMap.put(p, opponentList);
        }
        
        // For each day, create the matched for the oponent
        for (int r = 0; r < totalRounds; r++) {
            List<Match> roundMatches = schedule.get(r);
            for (Match m : roundMatches) {
                if(opponentsMap.containsKey(m.player1)) {
                    opponentsMap.get(m.player1).set(r, m.player2);
                }
                if(opponentsMap.containsKey(m.player2)) {
                    opponentsMap.get(m.player2).set(r, m.player1);
                }
            }
        }
        
        System.out.println();
     	System.out.printf("%-17s", "PLAYER/OPPONENT"); // Bendita justificacion Xd
     	for (int r = 1; r <= totalRounds; r++) {
    	 	System.out.printf("%-10s", "DAY " + r);
     	}
     	System.out.println();

     	for (String player : originalParticipants) {
         	System.out.printf("%-17s", player);
         	for (String opp : opponentsMap.get(player)) {
        	 	System.out.printf("%-10s", opp);
         	}
         	System.out.println();
     	}
     	System.out.println();
    	
    }
}
