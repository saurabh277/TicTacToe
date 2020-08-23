package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var PLAYER=true
    var TURN_COUNT=0
    var boardStatus=Array(3){IntArray(3)}

    lateinit var board:Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        board= arrayOf(
            arrayOf(btn1,btn2,btn3),
            arrayOf(btn4,btn5,btn6),
            arrayOf(btn7,btn8,btn9)
        )
        for(i in board){
            for(button in i){
                button.setOnClickListener (this)
            }
        }
        initializeBoardStatus()
        reset.setOnClickListener {
            PLAYER=true
            TURN_COUNT=0
            initializeBoardStatus()
        }

    }

    private fun initializeBoardStatus() {
        for(i in 0..2){
            for(j in 0..2){
                boardStatus[i][j]=-1
            }
        }
        for(i in board){
            for(button in i){
                button.isEnabled=true
                button.text=""
            }
        }
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.btn1->{
                updateValue(row=0,col=0,player=PLAYER)
            }
            R.id.btn2->{
                updateValue(row=0,col=1,player=PLAYER)
            }
            R.id.btn3->{
                updateValue(row=0,col=2,player=PLAYER)
            }
            R.id.btn4->{
                updateValue(row=1,col=0,player=PLAYER)
            }
            R.id.btn5->{
                updateValue(row=1,col=1,player=PLAYER)
            }
            R.id.btn6->{
                updateValue(row=1,col=2,player=PLAYER)
            }
            R.id.btn7->{
                updateValue(row=2,col=0,player=PLAYER)
            }
            R.id.btn8->{
                updateValue(row=2,col=1,player=PLAYER)
            }
            R.id.btn9->{
                updateValue(row=2,col=2,player=PLAYER)
            }
        }
        TURN_COUNT++
        PLAYER=!PLAYER
        if(PLAYER){
            updateText("Player X turn")
        }
        else{
            updateText("Player O turn")
        }
        if(TURN_COUNT==9){
            updateText("Game Draw")
        }
        checkWinner()

    }

    private fun checkWinner() {
        //horizontal rows
        for(i in 0..2){
            if(boardStatus[i][0]==boardStatus[i][1] && boardStatus[i][1]==boardStatus[i][2]){
                if(boardStatus[i][0]==1){
                    updateText("Player X Winner")
                    break
                }
                else  if(boardStatus[i][0]==0){
                    updateText("Player O Winner")
                    break

                }
            }
        }
        //vertical column
        for(i in 0..2){
            if(boardStatus[0][i]==boardStatus[1][i] && boardStatus[1][i]==boardStatus[2][i]){
                if(boardStatus[0][i]==1){
                    updateText("Player X Winner")
                    break
                }
                else  if(boardStatus[i][0]==0){
                    updateText("Player O Winner")
                    break

                }
            }
        }
        //for first diagonal
        if(boardStatus[0][0]==boardStatus[1][1] && boardStatus[0][0]==boardStatus[2][2]){
            if(boardStatus[0][0]==1){
                updateText("Player X Winner")

            }
            else  if(boardStatus[0][0]==0){
                updateText("Player O Winner")


            }
        }
        //for second diagonal
        if(boardStatus[0][2]==boardStatus[1][1] && boardStatus[0][2]==boardStatus[0][3]){
            if(boardStatus[0][2]==1){
                updateText("Player X Winner")

            }
            else  if(boardStatus[0][0]==0){
                updateText("Player O Winner")


            }
        }

    }

    private fun updateText(s: String) {
     tview.text=s
        if(s.contains("Winner")){
            disableButton()
        }
    }
    private fun disableButton()
    {
        for(i in board){
            for(button in i){
                button.isEnabled=false
            }
        }
    }
    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text=if(player) "X" else "O"
        val value=if(player) 1 else 0
        board[row][col].apply {
         isEnabled=false
            setText(text)

     }
        boardStatus[row][col]=value
    }

}
