package com.example.playground.ViewModels;

import androidx.lifecycle.ViewModel;

public class ScoreViewModel extends ViewModel {

    /*
     *  creating getters and setters to better encapsulate the data is a good idea
     * */

    // Tracks the score for Team A
    public int scoreTeamA = 0;

    // Tracks the score for Team B
    public int scoreTeamB = 0;
}