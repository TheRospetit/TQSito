package com.example.demo;
import java.util.ArrayList;


public class Deck {
  /*Atributtes*/
  Boolean notMoreCards;
  int numCards;
  ArrayList<CardClass> playableCards = new ArrayList<>();
  ArrayList<CardClass> playedCards = new ArrayList<>();


  public Deck(){}
  public Deck(Boolean thereIsCards, int nCards, ArrayList<CardClass> myCards, ArrayList<CardClass> lastCard){
    notMoreCards = thereIsCards;
    numCards= nCards;
    playableCards = myCards;
    playedCards = lastCard;
  }

  public Boolean getNotMoreCards() {return notMoreCards;}
  public ArrayList<CardClass> getPlayableCards() {return playableCards;}
  public Integer getNumCards() {return numCards;}
  public ArrayList<CardClass> getPlayedCards() {return playedCards;}
}
