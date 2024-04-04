package org.example;

import java.util.ArrayList;
import java.util.Random;

public class Point {
	private ArrayList<Point> neighbors;
	private int currentState;
	private int nextState;
	private int numStates = 6;
	
	public Point() {
		currentState = 0;
		nextState = 0;
		neighbors = new ArrayList<Point>();
	}

	public void clicked() {
		currentState=(++currentState)%numStates;	
	}
	
	public int getState() {
		return currentState;
	}

	public void setState(int s) {
		currentState = s;
	}

	public void calculateNewState() {
//		nextState=willBeAlive("23/3",countAliveNeighbors());//basic
		nextState=willBeAlive("2345/45678",countAliveNeighbors());//cities
//		nextState=willBeAlive("45678/3",countAliveNeighbors());//coral
//		nextState=willBeAlive("45/23",countAliveNeighbors());//custom
//		nextState=willBeAlive("2137/34",countAliveNeighbors());//custom
	}
	public void changeState() {
		currentState = nextState;
	}
	public void addNeighbor(Point nei) {
		neighbors.add(nei);
	}
	private int countAliveNeighbors(){
		int sum=0;
		for(Point neighbor: neighbors){
			sum+=neighbor.currentState;
		}
		return sum;
	}
	private int willBeAlive(String rules, int numOfNeighbors){
		boolean secondRuleFlag=false;

		for(int i=0; i<rules.length();i++){
			if(rules.charAt(i)=='/'){
				secondRuleFlag=true;
			}
			if( Character.getNumericValue(rules.charAt(i))==numOfNeighbors  && !secondRuleFlag && currentState==1){
				return 1;

			}
			if( Character.getNumericValue(rules.charAt(i))==numOfNeighbors  && secondRuleFlag && currentState==0){
				return 1;

			}

		}
		return 0;
	}
	//rain
	public void addRainNeighbor(Point nei) {
		neighbors.add(nei);
	}

	public void drop(){
		Random rand = new Random();
		if(rand.nextInt(100)<4) {
			currentState = 6;
		}
	}
	public void calculateNewRainState() {
		if(currentState>0){
			nextState=currentState-1;
		}
		if(!neighbors.isEmpty() &&currentState==0 && neighbors.get(0).currentState>0){
			nextState=6;
		}
	}
}
