package com.example.knightmove.Model;

public class QuestionSquare  extends Square{

    public QuestionSquare(int x, int y) {
        super(x, y);
        this.type="Question";
    }


  @Override
  public String toString() {
    String status;
    if(this.occupied) status = "Occupied";
    else status = "Not occupied";
//        return "Square" + this.x + this.y + " - " + status;
    return "Question";
  }
}
