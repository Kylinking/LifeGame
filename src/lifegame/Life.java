package lifegame;

import java.util.ArrayList;

public class Life {
	private boolean live = false;
	private boolean[] oldNeighbours;
	private ArrayList<Life> neighbours;
	
	public boolean isLive(){
		return live;
	}
	
	public void setLive(){
		live = true;
	}
	
	public void cleanLive(){
		live = false;
	}
	
	public void rotateLive(){
		live = !live;
	}
		
	public void CheckRule(){
		byte liveNeighbours = 0;
		for (int i=0; i!=oldNeighbours.length;i++){
			if (oldNeighbours[i]){
				liveNeighbours ++;
			}
		}
		if (live){
			if (liveNeighbours < 2 || liveNeighbours > 3){
				live = false;
			}
		}else{
			if(liveNeighbours == 3){
				live = true;
			}
		}
	}
	
	public void RefreshNeighbours(){
		for(int i=0; i!=neighbours.size();i++){
			oldNeighbours[i] = neighbours.get(i).live;
		}
	}
	
	public void setNeighbour(ArrayList<Life> _neighbours){
		neighbours = _neighbours;
		oldNeighbours = new boolean[neighbours.size()];
		for (int i=0;i!=neighbours.size();i++){
			oldNeighbours[i] = neighbours.get(i).live;
		}
	}
}
